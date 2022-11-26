package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Utils {
    public String getInformacao(String end){
        String json;
        json = NetworkUtils.getJSONFromAPI(end);

        return json;
    }

    public ArrayList<UserModel> getUserJsonList(String end){
        try {

            String json;
            json = NetworkUtils.getJSONFromAPI(end);

            ArrayList<UserModel> user = new ArrayList<UserModel>();

            JSONObject jsonObj = new JSONObject(json);
            JSONArray array = jsonObj.getJSONArray("users");

            for (int i=0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                user.add(new UserModel(
                        obj.getString("userName"),
                        obj.getString("password"),
                        obj.getString("image"),
                        Integer.parseInt(obj.getString("ranking"))));
            }
            return user;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public UserModel getUserJson(String end){
        try {

            String json;
            json = NetworkUtils.getJSONFromAPI(end);
            JSONObject jsonObj = new JSONObject(json);

            UserModel user = new UserModel(
                    jsonObj.getString("userName"),
                    jsonObj.getString("password"),
                    jsonObj.getString("image"),
                    Integer.parseInt(jsonObj.getString("ranking")));

            return user;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public GameCards getGameCards(String end){
        try {
            ArrayList<Card> user = new ArrayList <Card> ();
            ArrayList <Card> oponent = new ArrayList <Card> ();

            String json;
            json = NetworkUtils.getJSONFromAPI(end);
            JSONObject jsonObj = new JSONObject(json);

            JSONArray userJson = jsonObj.getJSONArray("user");

            for (int i=0; i < userJson.length(); i++) {

                JSONObject userItem = userJson.getJSONObject(i);
                JSONObject powerStats = userItem.getJSONObject("powerstats");
                JSONObject image = userItem.getJSONObject("image");
                JSONObject work = userItem.getJSONObject("work");

                user.add(
                        new Card(
                                userItem.getString("id"),
                                userItem.getString("name"),
                                powerStats.getString("intelligence"),
                                powerStats.getString("strength"),
                                powerStats.getString("speed"),
                                powerStats.getString("durability"),
                                powerStats.getString("power"),
                                powerStats.getString("combat"),
                                work.getString("occupation"),
                                image.getString("url")
                                ));

            }

            JSONArray oponentJson = jsonObj.getJSONArray("oponent");
            for (int i=0; i < oponentJson.length(); i++) {

                JSONObject userItem = oponentJson.getJSONObject(i);
                JSONObject powerStats = userItem.getJSONObject("powerstats");
                JSONObject image = userItem.getJSONObject("image");
                JSONObject work = userItem.getJSONObject("work");

                oponent.add(
                        new Card(
                                userItem.getString("id"),
                                userItem.getString("name"),
                                powerStats.getString("intelligence"),
                                powerStats.getString("strength"),
                                powerStats.getString("speed"),
                                powerStats.getString("durability"),
                                powerStats.getString("power"),
                                powerStats.getString("combat"),
                                work.getString("occupation"),
                                image.getString("url")
                        ));

            }

            return new GameCards(user, oponent);
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }



    private Bitmap baixarImagem(String url) {
        try{
            URL endereco;
            InputStream inputStream;
            Bitmap imagem; endereco = new URL(url);
            inputStream = endereco.openStream();
            imagem = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return imagem;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
