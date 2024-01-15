package com.example.pagedebase;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
public class DatabaseManager {


    private Context context;
    public RequestQueue requestQueue;

    public DatabaseManager(Context context)
    {
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);
    }

}
