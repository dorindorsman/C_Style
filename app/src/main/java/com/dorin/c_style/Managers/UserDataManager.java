package com.dorin.c_style.Managers;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.dorin.c_style.Firebase.FireBaseMyStorage;
import com.dorin.c_style.Firebase.FirebaseDB;
import com.dorin.c_style.Fregments.ClosetFragment;
import com.dorin.c_style.Objects.Item;
import com.dorin.c_style.Objects.Outfit;
import com.dorin.c_style.Objects.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.UUID;

public class UserDataManager {

    private static UserDataManager single_instance = null;
    private Context context;
    private FirebaseAuth myAuth;
    private FirebaseDB myDB;
    private FireBaseMyStorage myStorage;

    private User myUser;
    private ArrayList<Item> myItems;
    private ArrayList<Outfit> myOutfits;



    private UserDataManager(Context context) {

        myDB=FirebaseDB.getInstance();
        myDB.setCallback_loadUserData(callback_loadUserData);
        myAuth=FirebaseAuth.getInstance();
        myStorage=FireBaseMyStorage.getInstance();
        myStorage.setCallBack_uploadImg(callBack_uploadImg);
        this.context = context;
    }

    public static UserDataManager initHelper(Context context) {
        if (single_instance == null) {
            single_instance = new UserDataManager(context);
        }
        return single_instance;
    }

    public static UserDataManager getInstance() {
        return single_instance;
    }


    public User getMyUser() {
        return myUser;
    }

    public ArrayList<Item> getMyItems() {
        return myItems;
    }

    public ArrayList<Outfit> getMyOutfits() {
        return myOutfits;
    }

    public void setMyUser(String firstName, String lastName, String urlIMG) {
        myOutfits = new ArrayList<>();
        myItems = new ArrayList<>();
        myUser = new User();
        myUser.setUserFirstName(firstName);
        myUser.setUserLastName(lastName);
        myUser.setUserPhoneNumber(myAuth.getCurrentUser().getPhoneNumber());
        myUser.setUserPic(urlIMG);
        String uid = myAuth.getCurrentUser().getUid();
        myDB.createUser(uid, myUser);
    }

    public void addNewItem(Uri uriImg, String name, String category, String size, String color, boolean favorite, Activity activity){
        Item item=new Item();
        item.setName(name);
        item.setCategory(category);
        item.setSize(size);
        item.setColor(color);
        item.setFavorite(favorite);
        myItems.add(item);
        myStorage.uploadImageItem(uriImg,myAuth.getUid(), item.getId(), activity);
    }

    public void editItem(Item item){

        myDB.addItem(myAuth.getCurrentUser().getUid(),item.getId(),item);
    }

    public void removeItem(Item item){
        myItems.remove(item);
        myDB.removeItem(myAuth.getCurrentUser().getUid(),item.getId());
    }




    public void addNewOutfit(String name,String bagID,String coatId,String topID,String bottomID,String shoesID,String accessoryID){

        Outfit outfit=new Outfit();
        outfit.setName(name);
        outfit.setBagID(bagID);
        outfit.setCoatID(coatId);
        outfit.setTopID(topID);
        outfit.setBottomID(bottomID);
        outfit.setShoesID(shoesID);
        outfit.setAccessoryID(accessoryID);
        myOutfits.add(outfit);
        myDB.addOutfit(myAuth.getCurrentUser().getUid(),outfit.getId(),outfit);

    }

    public void editOutfit(Outfit outfit){
        myDB.addOutfit(myAuth.getCurrentUser().getUid(),outfit.getId(),outfit);
    }

    public void removeOutfit(Outfit outfit){
        myOutfits.remove(outfit);
        myDB.removeOutfit(myAuth.getCurrentUser().getUid(),outfit.getId());
    }


    FirebaseDB.Callback_loadUserData callback_loadUserData=new FirebaseDB.Callback_loadUserData() {
        @Override
        public void callback_loadUserData(User user) {
            if (user != null) {
                myUser = user;
            }
        }

        @Override
        public void callback_loadOutfits(ArrayList<Outfit> outfits) {
            myOutfits=outfits;
        }

        @Override
        public void callback_loadItems(ArrayList<Item> items) {
            myItems=items;
        }
    };

        FireBaseMyStorage.CallBack_UploadImg callBack_uploadImg=new FireBaseMyStorage.CallBack_UploadImg() {
            @Override
            public void urlReady(String url,Activity activity) {
                Log.d("tfff", "Here line 150");
                Item item = myItems.get(myItems.size() - 1);
               item.setPicture(url);
               myDB.addItem(myAuth.getCurrentUser().getUid(), item.getId(), item);
               activity.finish();
            }
        };

}
