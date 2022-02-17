package com.dorin.c_style.Objects;

import java.util.UUID;

public class Item {


    private String id;
    private String picture;
    private String name;
    private String category;
    private String size;
    private String color;
    private boolean favorite;

    public Item(){
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public Item setId() {
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public Item setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Item setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getSize() {
        return size;
    }

    public Item setSize(String size) {
        this.size = size;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Item setColor(String color) {
        this.color = color;
        return this;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public Item setFavorite(boolean favorite) {
        this.favorite = favorite;
        return this;
    }




}
