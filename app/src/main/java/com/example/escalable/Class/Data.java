package com.example.escalable.Class;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.escalable.Activities.Login;

public class Data {
    public static SharedPreferences archive;
    private String token;
    public static String Image_url = "http://toshito.mipantano.com/img/";
    public static String url = "http://toshito.mipantano.com/api/";
    public static String user_name;

    public static Boolean check_session()
    {
        if(Data.getapi_token() != "")
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void setapi_token(String api_token)
    {
        SharedPreferences.Editor editor = archive.edit();
        editor.putString("api_token", api_token);
        editor.apply();
    }

    public static String getapi_token()
    {
        return archive.getString("api_token", "");
    }

    public static void removeapi_token()
    {
        SharedPreferences.Editor editor = archive.edit();
        editor.putString("api_token", "");
        editor.apply();
    }
}

