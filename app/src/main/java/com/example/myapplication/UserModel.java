package com.example.myapplication;

import android.graphics.Bitmap;

public class UserModel {
    private String userName;
    private String password;
    private String image;
    private int ranking;

    public UserModel(String userName, String password, String image, int ranking){
        this.userName = userName;
        this.password = password;
        this.image = image;
        this.ranking = ranking;
    }


    public String getRanking() {
        return String.valueOf(ranking);
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
