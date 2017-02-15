package com.limox.jesus.manageproductcontentprovider.presenter;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.limox.jesus.manageproductcontentprovider.interfaces.InvoicePharmacyPresenter;
import com.limox.jesus.manageproductcontentprovider.provider.ManageProductContract;

/**
 * Created by jesus on 15/02/17.
 */

public class InvoicePharmacyPresenterImpl implements InvoicePharmacyPresenter,LoaderManager.LoaderCallbacks<Cursor> {

    private Context mContext;

    public InvoicePharmacyPresenterImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(mContext, ManageProductContract.Invoice.CONTENT_URI,)
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
