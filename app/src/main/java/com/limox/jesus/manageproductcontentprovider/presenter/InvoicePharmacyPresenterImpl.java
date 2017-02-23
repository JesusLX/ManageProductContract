package com.limox.jesus.manageproductcontentprovider.presenter;

import android.app.Activity;
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
    private InvoicePharmacyPresenter.View view;

    public InvoicePharmacyPresenterImpl(Context context,InvoicePharmacyPresenter.View view) {
        this.mContext = context;
        this.view = view;
    }

    public void getAllInvoices(){
        Loader<Cursor> loader = ((Activity)mContext).getLoaderManager().getLoader(0);
        if (loader == null)
            ((Activity) mContext).getLoaderManager().initLoader(0, null, this);
        else
            ((Activity) mContext).getLoaderManager().restartLoader(0, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(mContext, ManageProductContract.Invoice.CONTENT_URI, ManageProductContract.Invoice.PROJECTION, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.setNotificationUri(mContext.getContentResolver(), ManageProductContract.Invoice.CONTENT_URI); //Notificame cuando haya un cambio en algo
        view.setCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.setCursor(null);
    }
}
