package com.dorin.c_style.Objects;

import java.util.UUID;

public class Outfit {


    private String id;
    private String picture;
    private String bagID;
    private String shirtID;
    private String pantsID;
    private String shoesID;
    private String accessoryID;


    public Outfit(){
        this.id = UUID.randomUUID().toString();
    }

    public String getPicture() {
        return picture;
    }

    public Outfit setPicture(String picture) {
        this.picture = picture;
        return this;
    }


    public String getId() {
        return id;
    }

    public Outfit setId() {
        return this;
    }

    public String getBagID() {
        return bagID;
    }

    public Outfit setBagID(String bagID) {
        this.bagID = bagID;
        return this;
    }

    public String getShirtID() {
        return shirtID;
    }

    public Outfit setShirtID(String shirtID) {
        this.shirtID = shirtID;
        return this;
    }

    public String getPantsID() {
        return pantsID;
    }

    public Outfit setPantsID(String pantsID) {
        this.pantsID = pantsID;
        return this;
    }

    public String getShoesID() {
        return shoesID;
    }

    public Outfit setShoesID(String shoesID) {
        this.shoesID = shoesID;
        return this;
    }

    public String getAccessoryID() {
        return accessoryID;
    }

    public Outfit setAccessoryID(String accessoryID) {
        this.accessoryID = accessoryID;
        return this;
    }
}
