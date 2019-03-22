package com.example.escalable.Singletones;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.escalable.Activities.MainActivity;

public class VolleyS {
    private RequestQueue rq;
    private static VolleyS vs = null;
    private ImageLoader mImageLoader;
    public static Context getC;

    private VolleyS(Context c){
        getC = c;
        rq = Volley.newRequestQueue(c);
        mImageLoader = new ImageLoader(this.rq, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);

            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }

            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
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

    public ImageLoader getImageLoader(){
        return this.mImageLoader;
    }
}
