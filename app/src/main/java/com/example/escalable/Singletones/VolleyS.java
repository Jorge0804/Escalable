package com.example.escalable.Singletones;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleyS {
    private RequestQueue rq;
    private static VolleyS vs = null;
    public static Context getC;

    private VolleyS(Context c){
        getC = c;
        rq = Volley.newRequestQueue(c);
    }

    public static VolleyS getinstance(Context c){
        if (vs == null)
        {
            vs = new VolleyS(c);
        }
        return vs;
    }

    public RequestQueue getRq(){
        return rq;
    }
}

