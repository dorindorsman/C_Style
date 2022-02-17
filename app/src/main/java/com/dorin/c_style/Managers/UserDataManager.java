package com.dorin.c_style.Managers;

import android.content.Context;

import com.dorin.c_style.Firebase.FirebaseDB;
import com.dorin.c_style.Objects.Item;
import com.dorin.c_style.Objects.Outfit;
import com.dorin.c_style.Objects.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.UUID;

public class UserDataManager {

    private static UserDataManager single_instance = null;
    private Context context;
    private FirebaseAuth myAuth;
    private FirebaseDB myDB;

    private User myUser;
    private ArrayList<Item> myItems;
    private ArrayList<Outfit> myOutfits;

    private UserDataManager(Context context) {
        myDB=FirebaseDB.getInstance();
        myDB.setCallback_loadUserData(callback_loadUserData);
        myAuth=FirebaseAuth.getInstance();
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

    public void addNewItem(String urlImg,String name,String category,String size,String color,boolean favorite){

        Item item=new Item();
        item.setPicture(urlImg);
        item.setName(name);
        item.setCategory(category);
        item.setSize(size);
        item.setColor(color);
        item.setFavorite(favorite);
        myItems.add(item);
        myDB.addItem(myAuth.getCurrentUser().getUid(),item.getId(),item);

    }

    public void editItem(Item item){
        myDB.addItem(myAuth.getCurrentUser().getUid(),item.getId(),item);
    }

    public void removeItem(Item item){
        myItems.remove(item);
        myDB.removeItem(myAuth.getCurrentUser().getUid(),item.getId());
    }




    public void addNewOutfit(String urlImg,String bagID,String shirtID,String pantsID,String shoesID,String accessoryID){

        Outfit outfit=new Outfit();
        outfit.setPicture(urlImg);
        outfit.setBagID(bagID);
        outfit.setShirtID(shirtID);
        outfit.setPantsID(pantsID);
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


}
