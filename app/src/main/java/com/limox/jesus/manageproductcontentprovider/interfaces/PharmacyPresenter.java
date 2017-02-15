package com.limox.jesus.manageproductcontentprovider.interfaces;

import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.limox.jesus.manageproductcontentprovider.adapter.PharmacyAdapter;
import com.limox.jesus.manageproductcontentprovider.model.Pharmacy;

/**
 * Created by usuario on 11/01/17.
 */

public interface PharmacyPresenter {
    void addPharmacy(Pharmacy pharmacy);
    Loader<Cursor> onCreateLoader(int id, Bundle args);
    void onLoadFinished(Loader<Cursor> loader, Cursor cursor);
    void getAllPharmacy();

    interface View{
        void onStart();
        Context getContext();
        void setCursorPharmacy(Cursor cursor);
        void onDestroy();

    }
}
