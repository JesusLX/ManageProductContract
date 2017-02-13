package com.limox.jesus.manageproductcontentprovider.presenter;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;

import com.limox.jesus.manageproductcontentprovider.interfaces.ManagePresenter;
import com.limox.jesus.manageproductcontentprovider.model.Product;
import com.limox.jesus.manageproductcontentprovider.provider.ManageProductContract;

/**
 * Created by jesus on 11/12/16.
 */

public class ManagePresenterImpl implements ManagePresenter {

    private static final int PRODUCT = 1;
    ManagePresenter.View view;
    private Context context;

    public ManagePresenterImpl(View view) {
        this.view = view;
        this.context = view.getContext();
    }

    @Override
    public void addProduct(Product product) {
        try {
            ContentValues contentValues;
            contentValues = getContentProduct(product);
            context.getContentResolver().insert(ManageProductContract.Product.CONTENT_URI, contentValues);
        } catch (SQLException e) {
            view.showMessage(e.getMessage());
        }
    }
    private ContentValues getContentProduct(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ManageProductContract.Product.NAME, product.getName());
        contentValues.put(ManageProductContract.Product.DESCRIPTION, product.getDescription());
        contentValues.put(ManageProductContract.Product.BRAND, product.getBrand());
        contentValues.put(ManageProductContract.Product.DOSAGE, product.getDosage());
        contentValues.put(ManageProductContract.Product.PRICE, product.getPrice());
        contentValues.put(ManageProductContract.Product.STOCK, product.getStock());
        contentValues.put(ManageProductContract.Product.IMAGE, product.getImage());
        contentValues.put(ManageProductContract.Product.sProductProjectionMap.get(ManageProductContract.Product.CATEGORIE_ID), product.getIdCategory());

        return contentValues;
    }
    @Override
    public void updateProduct(Product product) {
        Uri uri = ContentUris.withAppendedId(ManageProductContract.Product.CONTENT_URI,product.getId());
        String where = ManageProductContract.Product._ID+" = ?";
        String[] whereParams = {String.valueOf(product.getId())};
        context.getContentResolver().update(uri,getContentProduct(product),null,null);
    }


    @Override
    public void onDestroy() {
        view = null;
    }
}
