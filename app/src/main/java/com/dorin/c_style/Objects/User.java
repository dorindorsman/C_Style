package com.dorin.c_style.Objects;

public class User {


    private String userPic;
    private String userFirstName;
    private String userLastName;
    private String userPhoneNumber;


    public User(){}


    public String getUserPic() {
        return userPic;
    }

    public User setUserPic(String userPic) {
        this.userPic = userPic;
        return this;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public User setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
        return this;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public User setUserLastName(String userLastName) {
        this.userLastName = userLastName;
        return this;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public User setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
        return this;
    }
}
