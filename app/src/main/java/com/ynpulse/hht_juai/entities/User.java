package com.ynpulse.hht_juai.entities;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class User implements Serializable{

    private int Id;
    private String uuid;
    private String realname;
    private String Vein;
    private String Avatar;
    private Bitmap avatarBitMap;


    public User() {
    }

    public Bitmap getAvatarBitMap() {
        return avatarBitMap;
    }

    public void setAvatarBitMap(Bitmap avatarBitMap) {
        this.avatarBitMap = avatarBitMap;
    }

    public User(int id, String uuid, String realname, String vein, String avatar) {
        Id = id;
        this.uuid = uuid;
        this.realname = realname;
        Vein = vein;
        Avatar = avatar;

        new Thread(new Runnable() {
            @Override
            public void run() {
                if(!Avatar.isEmpty()){
                    try {
                        URL url=new URL(Avatar);
                        HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
                        if(urlConnection.getResponseCode()==200){
                            InputStream inputStream=urlConnection.getInputStream();
                            avatarBitMap= BitmapFactory.decodeStream(inputStream);
                            inputStream.close();
                        }

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();



    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getVein() {
        return Vein;
    }

    public void setVein(String vein) {
        Vein = vein;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
