package com.limox.jesus.manageproductcontentprovider.presenter;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.limox.jesus.manageproductcontentprovider.interfaces.PharmacyPresenter;
import com.limox.jesus.manageproductcontentprovider.model.Pharmacy;
import com.limox.jesus.manageproductcontentprovider.provider.ManageProductContract;


/**
 * Class presenter between the pharmacy fragments and the bd
 * Created by usuario on 11/01/17.
 */

public class PharmacyPresenterImpl implements PharmacyPresenter,LoaderManager.LoaderCallbacks<Cursor>  {

    private Context context;

    private PharmacyPresenter.View view;

    public PharmacyPresenterImpl(PharmacyPresenter.View view) {
        this.view = view;
        this.context = view.getContext();
    }

    @Override
    public void addPharmacy(Pharmacy pharmacy) {
        context.getContentResolver().insert(ManageProductContract.Pharmacy.CONTENT_URI,getContentValues(pharmacy));
    }

    private ContentValues getContentValues(Pharmacy pharmacy){
        ContentValues cv = new ContentValues();
        cv.put(ManageProductContract.Pharmacy.CIF,pharmacy.getCif());
        cv.put(ManageProductContract.Pharmacy.ADDRESS,pharmacy.getAddress());
        cv.put(ManageProductContract.Pharmacy.EMAIL,pharmacy.getEmail());
        cv.put(ManageProductContract.Pharmacy.PHONE,pharmacy.getPhone());
        return cv;
    }
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(context,ManageProductContract.Pharmacy.CONTENT_URI,ManageProductContract.Pharmacy.PROJECTION,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        view.setCursorPharmacy(cursor);
    }

    @Override
    public void getAllPharmacy() {
        Loader<Cursor> loader = ((Activity)context).getLoaderManager().getLoader(0);
        if (loader == null)
            ((Activity)context).getLoaderManager().initLoader(0,null,this);
        else
            ((Activity) context).getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.setCursorPharmacy(null);
    }
}
