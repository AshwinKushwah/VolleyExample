package com.example.advanced.volleyexample;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Ashwin on 05-Sep-18.
 */

public class VolleySingleton {

    private  static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;
    public static VolleySingleton getInstance(Context context){

        if(mInstance==null)
        {
            mInstance = new VolleySingleton(context);
        }

        return mInstance;
    }

    private VolleySingleton(Context context){

        mRequestQueue = getRequestQueue(context);
    }

    public RequestQueue getRequestQueue(Context context){

        if(mRequestQueue == null){

            mRequestQueue = Volley.newRequestQueue(context);


        }
        return mRequestQueue;
    }
}
