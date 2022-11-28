package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileSystem;
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

            String json = "";
            //json = NetworkUtils.getJSONFromAPI(end);
            json = "{\"user\":[{\"response\":\"success\",\"id\":\"20\",\"name\":\"Amazo\",\"powerstats\":{\"intelligence\":\"63\",\"strength\":\"100\",\"speed\":\"83\",\"durability\":\"100\",\"power\":\"100\",\"combat\":\"100\"},\"biography\":{\"full-name\":\"\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"Professor Ivos Amazing Android\",\"Timazo\",\"Humazo\",\"Hourmazo\"],\"place-of-birth\":\"-\",\"first-appearance\":\"Brave and the Bold #30 (July, 1960)\",\"publisher\":\"DC Comics\",\"alignment\":\"bad\"},\"appearance\":{\"gender\":\"Male\",\"race\":\"Android\",\"height\":[\"8'5\",\"257 cm\"],\"weight\":[\"385 lb\",\"173 kg\"],\"eye-color\":\"Red\",\"hair-color\":\"-\"},\"work\":{\"occupation\":\"-\",\"base\":\"-\"},\"connections\":{\"group-affiliation\":\"Formerly the Secret Society of Super Villains\",\"relatives\":\"Professor Ivo (creator), Kid Amazo (cyborg offspring)\"},\"image\":{\"url\":\"https://www.superherodb.com/pictures2/portraits/10/100/1390.jpg\"}},{\"response\":\"success\",\"id\":\"53\",\"name\":\"Atom II\",\"powerstats\":{\"intelligence\":\"88\",\"strength\":\"10\",\"speed\":\"33\",\"durability\":\"45\",\"power\":\"40\",\"combat\":\"60\"},\"biography\":{\"full-name\":\"Ray Palmer\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"The Mighty Mite\",\"the Tiny Titan\",\"the World's Smallest Hero\"],\"place-of-birth\":\"-\",\"first-appearance\":\"Showcase #34\",\"publisher\":\"DC Comics\",\"alignment\":\"good\"},\"appearance\":{\"gender\":\"Male\",\"race\":\"Human\",\"height\":[\"6'0\",\"183 cm\"],\"weight\":[\"180 lb\",\"81 kg\"],\"eye-color\":\"Brown\",\"hair-color\":\"Auburn\"},\"work\":{\"occupation\":\"Physics Professor\",\"base\":\"Ivy Town\"},\"connections\":{\"group-affiliation\":\"Justice League of America, formerly; Suicide Squad, Teen Titans\",\"relatives\":\"Jean Loring (ex-wife, deceased), Princess Laethwen (wife, deceased), Danny Palmer (brother, deceased), David Palmer (father), Susan Palmer (mother, deceased), David Palmer (uncle)\"},\"image\":{\"url\":\"https://www.superherodb.com/pictures2/portraits/10/100/938.jpg\"}},{\"response\":\"success\",\"id\":\"44\",\"name\":\"Ariel\",\"powerstats\":{\"intelligence\":\"50\",\"strength\":\"10\",\"speed\":\"12\",\"durability\":\"14\",\"power\":\"94\",\"combat\":\"28\"},\"biography\":{\"full-name\":\"Ariel\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"-\"],\"place-of-birth\":\"-\",\"first-appearance\":\"Fallen Angels #1 (1987)\",\"publisher\":\"Marvel Comics\",\"alignment\":\"good\"},\"appearance\":{\"gender\":\"Female\",\"race\":\"null\",\"height\":[\"5'5\",\"165 cm\"],\"weight\":[\"130 lb\",\"59 kg\"],\"eye-color\":\"Purple\",\"hair-color\":\"Pink\"},\"work\":{\"occupation\":\"Leader, refugee alien; former alien mutant-hunter and hedonist\",\"base\":\"-\"},\"connections\":{\"group-affiliation\":\"X-Men, Formerly Fallen Angels\",\"relatives\":\"-\"},\"image\":{\"url\":\"https://www.superherodb.com/pictures2/portraits/10/100/1348.jpg\"}},{\"response\":\"success\",\"id\":\"1\",\"name\":\"A-Bomb\",\"powerstats\":{\"intelligence\":\"38\",\"strength\":\"100\",\"speed\":\"17\",\"durability\":\"80\",\"power\":\"24\",\"combat\":\"64\"},\"biography\":{\"full-name\":\"Richard Milhouse Jones\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"Rick Jones\"],\"place-of-birth\":\"Scarsdale, Arizona\",\"first-appearance\":\"Hulk Vol 2 #2 (April, 2008) (as A-Bomb)\",\"publisher\":\"Marvel Comics\",\"alignment\":\"good\"},\"appearance\":{\"gender\":\"Male\",\"race\":\"Human\",\"height\":[\"6'8\",\"203 cm\"],\"weight\":[\"980 lb\",\"441 kg\"],\"eye-color\":\"Yellow\",\"hair-color\":\"No Hair\"},\"work\":{\"occupation\":\"Musician, adventurer, author; formerly talk show host\",\"base\":\"-\"},\"connections\":{\"group-affiliation\":\"Hulk Family; Excelsior (sponsor), Avengers (honorary member); formerly partner of the Hulk, Captain America and Captain Marvel; Teen Brigade; ally of Rom\",\"relatives\":\"Marlo Chandler-Jones (wife); Polly (aunt); Mrs. Chandler (mother-in-law); Keith Chandler, Ray Chandler, three unidentified others (brothers-in-law); unidentified father (deceased); Jackie Shorr (alleged mother; unconfirmed)\"},\"image\":{\"url\":\"https://www.superherodb.com/pictures2/portraits/10/100/10060.jpg\"}},{\"response\":\"success\",\"id\":\"19\",\"name\":\"Allan Quatermain\",\"powerstats\":{\"intelligence\":\"null\",\"strength\":\"null\",\"speed\":\"null\",\"durability\":\"null\",\"power\":\"null\",\"combat\":\"null\"},\"biography\":{\"full-name\":\"\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"-\"],\"place-of-birth\":\"-\",\"first-appearance\":\"(original version) King Solomon's Mines; (this version) League of Extraordinary Gentlemen #1 (America's Best Comics)\",\"publisher\":\"Wildstorm\",\"alignment\":\"good\"},\"appearance\":{\"gender\":\"Male\",\"race\":\"null\",\"height\":[\"-\",\"0 cm\"],\"weight\":[\"- lb\",\"0 kg\"],\"eye-color\":\"-\",\"hair-color\":\"-\"},\"work\":{\"occupation\":\"(former) hunter; (current) agent of the British government\",\"base\":\"The Secret Wing of the British Museum, London\"},\"connections\":{\"group-affiliation\":\"League of Extraordinary Gentlemen\",\"relatives\":\"-\"},\"image\":{\"url\":\"https://www.superherodb.com/pictures2/portraits/10/100/630.jpg\"}}],\"oponent\":[{\"response\":\"success\",\"id\":\"99\",\"name\":\"Black Cat\",\"powerstats\":{\"intelligence\":\"75\",\"strength\":\"16\",\"speed\":\"33\",\"durability\":\"10\",\"power\":\"23\",\"combat\":\"70\"},\"biography\":{\"full-name\":\"Felicia Hardy\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"Felicity Harmon\"],\"place-of-birth\":\"Queens, New York\",\"first-appearance\":\"Amazing Spider-Man #194 (July, 1979)\",\"publisher\":\"Marvel Comics\",\"alignment\":\"good\"},\"appearance\":{\"gender\":\"Female\",\"race\":\"Human\",\"height\":[\"5'10\",\"178 cm\"],\"weight\":[\"120 lb\",\"54 kg\"],\"eye-color\":\"Green\",\"hair-color\":\"Blond\"},\"work\":{\"occupation\":\"Cat burglar; Private investigator, founder of Cat's Eye Investigations.\",\"base\":\"-\"},\"connections\":{\"group-affiliation\":\"Formerly Heroes for Hire\",\"relatives\":\"Walter Hardy (father, deceased), Lydia Hardy (mother)\"},\"image\":{\"url\":\"https://www.superherodb.com/pictures2/portraits/10/100/32.jpg\"}},{\"response\":\"success\",\"id\":\"42\",\"name\":\"Ardina\",\"powerstats\":{\"intelligence\":\"63\",\"strength\":\"100\",\"speed\":\"100\",\"durability\":\"80\",\"power\":\"100\",\"combat\":\"25\"},\"biography\":{\"full-name\":\"Ardina\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"-\"],\"place-of-birth\":\"-\",\"first-appearance\":\"The Order #4\",\"publisher\":\"Marvel Comics\",\"alignment\":\"good\"},\"appearance\":{\"gender\":\"Female\",\"race\":\"Alien\",\"height\":[\"6'4\",\"193 cm\"],\"weight\":[\"218 lb\",\"98 kg\"],\"eye-color\":\"White\",\"hair-color\":\"Orange\"},\"work\":{\"occupation\":\"-\",\"base\":\"-\"},\"connections\":{\"group-affiliation\":\"-\",\"relatives\":\"Norrin Radd (Silver Surfer, clonal source), Clea (creator)\"},\"image\":{\"url\":\"https://www.superherodb.com/pictures2/portraits/10/100/10132.jpg\"}},{\"response\":\"success\",\"id\":\"79\",\"name\":\"Beta Ray Bill\",\"powerstats\":{\"intelligence\":\"63\",\"strength\":\"80\",\"speed\":\"35\",\"durability\":\"95\",\"power\":\"100\",\"combat\":\"84\"},\"biography\":{\"full-name\":\"Beta Ray Bill (translation of his Korbinite name)\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"Beta Ray Thor\",\"Simon Walters\"],\"place-of-birth\":\"Burning Galaxy\",\"first-appearance\":\"Thor #337\",\"publisher\":\"Marvel Comics\",\"alignment\":\"good\"},\"appearance\":{\"gender\":\"Male\",\"race\":\"null\",\"height\":[\"6'7\",\"201 cm\"],\"weight\":[\"480 lb\",\"216 kg\"],\"eye-color\":\"-\",\"hair-color\":\"No Hair\"},\"work\":{\"occupation\":\"Guardian of the Korbinite race\",\"base\":\"currently New York City, formerly Asgard, formerly the fleet of Korbinite ships.\"},\"connections\":{\"group-affiliation\":\"ally of the Asgardians, Thor Corps, Star Masters\",\"relatives\":\"-\"},\"image\":{\"url\":\"https://www.superherodb.com/pictures2/portraits/10/100/28.jpg\"}},{\"response\":\"success\",\"id\":\"17\",\"name\":\"Alfred Pennyworth\",\"powerstats\":{\"intelligence\":\"63\",\"strength\":\"10\",\"speed\":\"17\",\"durability\":\"10\",\"power\":\"7\",\"combat\":\"55\"},\"biography\":{\"full-name\":\"Alfred Thaddeus Crane Pennyworth\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"Alfred Beagle\"],\"place-of-birth\":\"-\",\"first-appearance\":\"Batman #16 (April, 1943)\",\"publisher\":\"DC Comics\",\"alignment\":\"good\"},\"appearance\":{\"gender\":\"Male\",\"race\":\"Human\",\"height\":[\"5'10\",\"178 cm\"],\"weight\":[\"160 lb\",\"72 kg\"],\"eye-color\":\"Blue\",\"hair-color\":\"Black\"},\"work\":{\"occupation\":\"Butler; Caretaker, former Actor; Field Medic; Government Agent\",\"base\":\"Wayne Manor; Batcave; Gotham City\"},\"connections\":{\"group-affiliation\":\"Batman Family, Outsiders\",\"relatives\":\"Jarvis Pennyworth (father, deceased), Wilfred Pennyworth (older brother), Daphne Pennyworth (niece); Bruce Wayne (Batman, legal ward)\"},\"image\":{\"url\":\"https://www.superherodb.com/pictures2/portraits/10/100/628.jpg\"}},{\"response\":\"success\",\"id\":\"72\",\"name\":\"Battlestar\",\"powerstats\":{\"intelligence\":\"50\",\"strength\":\"53\",\"speed\":\"35\",\"durability\":\"74\",\"power\":\"48\",\"combat\":\"74\"},\"biography\":{\"full-name\":\"Lemar Hoskins\",\"alter-egos\":\"No alter egos found.\",\"aliases\":[\"Bucky\"],\"place-of-birth\":\"Chicago, Illinois\",\"first-appearance\":\"Captain America #323 (1986); (as Bucky)\",\"publisher\":\"Marvel Comics\",\"alignment\":\"good\"},\"appearance\":{\"gender\":\"Male\",\"race\":\"null\",\"height\":[\"6'6\",\"198 cm\"],\"weight\":[\"295 lb\",\"133 kg\"],\"eye-color\":\"Brown\",\"hair-color\":\"Black\"},\"work\":{\"occupation\":\"Former wrestler, federal operative\",\"base\":\"-\"},\"connections\":{\"group-affiliation\":\"Formerly Wild Pack, Bold Urban Commandos (Buckies), former partner of U.S.Agent, operative of the Commission on Superhuman Activities\",\"relatives\":\"-\"},\"image\":{\"url\":\"https://www.superherodb.com/pictures2/portraits/10/100/231.jpg\"}}]}";

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

    public static int AttributeStringToNumber(String attribute){
        if(attribute.equals("Inteligência")){
            return 1;
        } else if (attribute.equals("Resistência")) {
            return 2;
        } else if (attribute.equals("Força")) {
            return 3;
        } else if (attribute.equals("Combate")) {
            return 4;
        } else if (attribute.equals("Velocidade")) {
            return 5;
        } else if (attribute.equals("Poder")) {
            return 6;
        }
        return 0;
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

    public static String UrlEncodeBase64(String base64Input)
    {
        return base64Input.replace('+', '.').replace('/', '_').replace('=', '-');
    }

    public static String UrlDecodeBase64(String encodedBase64Input)
    {
        return encodedBase64Input.replace('.', '+').replace('_', '/').replace('-', '=');
    }
}
