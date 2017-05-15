package id.sch.smktelkom_mlg.privateassignment.xirpl326.aplikasisam.service;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import id.sch.smktelkom_mlg.privateassignment.xirpl326.aplikasisam.Fragment.Home;

/**
 * Created by Prasetya on 10/5/2017.
 */

public class VolleySingleton {
    private static volatile VolleySingleton mInstance;
    private static Home mCtx;
    private RequestQueue mRequestQueue;

   /*private VolleySingleton(Context context){
       if(mInstance!=null){
           throw new RuntimeException("Use getInstance() method to get the single instance of this class");
       }
       mCtx=context;
       mRequestQueue = getRequestQueue();
   }
    public static VolleySingleton getInstance(Context context){
       if(mInstance!=null){
           synchronized (VolleySingleton.class){
               if(mInstance==null) mInstance = new VolleySingleton(context);
           }
       }
       return mInstance;
   }
   */

    private VolleySingleton(Home context) {
        if (mInstance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class");
        }
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static VolleySingleton getInstance(Home context) {
        if (mInstance != null) {
            synchronized (VolleySingleton.class) {
                if (mInstance == null) mInstance = new VolleySingleton(context);
            }
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getActivity());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
