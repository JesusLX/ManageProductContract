package com.limox.jesus.manageproductcontentprovider;

import android.app.Application;
import android.content.Context;

import com.limox.jesus.manageproductcontentprovider.db.DatabaseHelper;

/**
 * Created by jesus on 20/10/16.
 */

public class ManageProductApplication extends Application {

    public static final int SORT_DEAFULT = 0;
    public static final int SORT_ALPH_UP = 1;
    public static final int SORT_ALPH_DOWN = 2;
    public static final int SORT_PRICE_DOWN = 3;
    public static Context context;


    public static Context getContext() {
        return context;
    }
    public ManageProductApplication(){
        context = this;
    }

}