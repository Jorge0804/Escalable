package com.example.escalable.Class;

import android.content.SharedPreferences;

public class Data {
    private static SharedPreferences archive;
    public static String Image_url = "http://toshito.mipantano.com/img/";
    public static String url= "http://toshito.mipantano.com/api/";

    public static void putapi_token(String api_token)
    {
        SharedPreferences.Editor editor = archive.edit();
        editor.putString("api_token", api_token);
        editor.apply();
    }

    public static String getapi_token()
    {
        return archive.getString("api_token", "");
    }
}

