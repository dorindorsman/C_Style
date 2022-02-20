package com.dorin.c_style.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.dorin.c_style.Objects.Item;
import com.dorin.c_style.Objects.Outfit;
import com.dorin.c_style.Objects.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseDB {

    public static final String USERS = "Users", ITEMS = "Items", OUTFITS = "Outfits";

    public interface Callback_checkUserExistence {
        void profileExist();

        void makeProfile();
    }

    public interface Callback_loadUserData {
        void callback_loadUserData(User user);

        void callback_loadOutfits(ArrayList<Outfit> outfits);

        void callback_loadItems(ArrayList<Item> items);
    }

    private FirebaseDatabase database;
    private DatabaseReference users, items, outfits;
    private static FirebaseDB single_instance;

    private Callback_checkUserExistence callback_checkUserExistence;
    private Callback_loadUserData callback_loadUserData;

    private FirebaseDB() {
        database = FirebaseDatabase.getInstance();
        users = database.getReference(USERS);
        items = database.getReference(ITEMS);
        outfits = database.getReference(OUTFITS);
    }

    public static FirebaseDB getInstance() {
        if (single_instance == null) {
            single_instance = new FirebaseDB();
        }
        return single_instance;
    }

    public FirebaseDB setCallback_checkUserExistence(Callback_checkUserExistence callback_checkUserExistence) {
        this.callback_checkUserExistence = callback_checkUserExistence;
        return this;
    }

    public FirebaseDB setCallback_loadUserData(Callback_loadUserData callback_loadUserData) {
        this.callback_loadUserData = callback_loadUserData;
        return this;
    }

    public void createUser(String uid, User user) {
        if (user != null && uid != null) {
            users.child(uid).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                }
            });
        }
    }

    public void addItem(String uid, String itemId, Item item) {
        if (item != null && uid != null && itemId != null) {
            items.child(uid).child(itemId).setValue(item)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                        }
                    });
        }
    }

    public void removeItem(String uid, String itemId) {
        if ( uid != null && itemId != null)
            items.child(uid).child(itemId).removeValue();
    }


    public void addOutfit(String uid, String outfitId, Outfit outfit) {
        if (outfit != null && uid != null && outfitId != null) {
            outfits.child(uid).child(outfitId).setValue(outfit)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                        }
                    });
        }
    }

    public void removeOutfit(String uid, String outfitId) {
        if ( uid != null && outfitId != null)
            outfits.child(uid).child(outfitId).removeValue();
    }

    public void hasProfile(String userID) {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    if (callback_checkUserExistence != null)
                        callback_checkUserExistence.makeProfile();
                } else {
                    try {
                        User user = snapshot.getValue(User.class);
                        if (callback_loadUserData != null) {
                            callback_loadUserData.callback_loadUserData(user);
                            loadItems(userID);
                            loadOutfits(userID);
                        }
                    } catch (Exception ex) {
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d("tfff", error.getMessage());
            }
        };
        users.child(userID).addListenerForSingleValueEvent(eventListener);
    }

    private void loadItems(String userID) {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Item> items = new ArrayList<>();
                if (snapshot.exists()) {
                    for (DataSnapshot child : snapshot.getChildren()) {
                        try {
                            Item item = child.getValue(Item.class);
                            items.add(item);
                        } catch (Exception ex) {
                        }
                    }
                }
                if (callback_loadUserData != null) {
                    callback_loadUserData.callback_loadItems(items);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        items.child(userID).addListenerForSingleValueEvent(eventListener);
    }

    private void loadOutfits(String userID) {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Outfit> outfits = new ArrayList<>();
                if (snapshot.exists()) {
                    for (DataSnapshot child : snapshot.getChildren()) {
                        try {
                            Outfit outfit = child.getValue(Outfit.class);
                            outfits.add(outfit);
                        } catch (Exception ex) {
                        }
                    }
                }
                if (callback_loadUserData != null) {
                    callback_loadUserData.callback_loadOutfits(outfits);
                }
                if (callback_checkUserExistence != null){
                    callback_checkUserExistence.profileExist();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        outfits.child(userID).addListenerForSingleValueEvent(eventListener);
    }
}
























