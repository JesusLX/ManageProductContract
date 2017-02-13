package com.limox.jesus.manageproductcontentprovider.presenter;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.limox.jesus.manageproductcontentprovider.interfaces.PharmacyPresenter;
import com.limox.jesus.manageproductcontentprovider.model.Pharmacy;



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
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        view.setCursorPharmacy(cursor);
    }

    @Override
    public void getAllPharmacy() {
        ((Activity)context).getLoaderManager().initLoader(0,null,this);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.setCursorPharmacy(null);
    }
}
