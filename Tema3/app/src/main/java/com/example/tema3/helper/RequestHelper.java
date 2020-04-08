package com.example.tema3.helper;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestHelper {
    private static RequestHelper requestHelperInstance;
    private RequestQueue requestQueue;
    private static Context context;

    private RequestHelper(Context ctx){
        context = ctx;
        requestQueue = getRequestQueue();
    }

    public static synchronized RequestHelper getRequestHelperInstance(Context context){
        if (requestHelperInstance == null)
            requestHelperInstance = new RequestHelper(context);
        return requestHelperInstance;
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }





}
