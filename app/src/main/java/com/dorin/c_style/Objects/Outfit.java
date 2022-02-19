package com.dorin.c_style.Objects;

import java.util.UUID;

public class Outfit {


    private String id;
    private String bagID;
    private String coatID;
    private String topID;
    private String bottomID;
    private String shoesID;
    private String accessoryID;


    public Outfit(){
        this.id = UUID.randomUUID().toString();
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

    public String getCoatID() {
        return coatID;
    }

    public Outfit setCoatID(String coatID) {
        this.coatID = coatID;
        return this;
    }

    public String getTopID() {
        return topID;
    }

    public Outfit setTopID(String topID) {
        this.topID = topID;
        return this;
    }

    public String getBottomID() {
        return bottomID;
    }

    public Outfit setBottomID(String bottomID) {
        this.bottomID = bottomID;
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
