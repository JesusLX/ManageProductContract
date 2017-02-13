package com.limox.jesus.manageproductcontentprovider.interfaces;


import android.content.Context;
import android.database.Cursor;

/**
 * Created by usuario on 26/01/17.
 */

public interface CategoryPresenter {
    void getAllCategory();



    public interface View {

        Context getContext();

        void setCursorCategory(Cursor cursor);

        Cursor getCursor();
    }
}
