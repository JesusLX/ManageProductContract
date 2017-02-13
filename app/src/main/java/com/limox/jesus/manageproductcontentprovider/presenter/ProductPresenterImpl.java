package com.limox.jesus.manageproductcontentprovider.presenter;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;

import com.limox.jesus.manageproductcontentprovider.interfaces.ProductPresenter;
import com.limox.jesus.manageproductcontentprovider.model.Product;
import com.limox.jesus.manageproductcontentprovider.provider.ManageProductContract;

import java.util.ArrayList;
import java.util.List;


/**
 * Class presenter between listProductFragment and the bd
 * Created by usuario on 9/12/16.
 */

public class ProductPresenterImpl implements ProductPresenter, LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PRODUCT = 1;
    private ProductPresenter.View view;
    private Context context;

    public ProductPresenterImpl(ProductPresenter.View fragment) {
        this.view = fragment;
        this.context = fragment.getContext();

    }

    @Override
    public void loadProducts() {
        Loader<Cursor> loader = ((Activity) context).getLoaderManager().getLoader(PRODUCT);
        if (loader == null)
            ((Activity) context).getLoaderManager().initLoader(PRODUCT, null, this);
        else
            ((Activity) context).getLoaderManager().restartLoader(PRODUCT, null, this);
    }

    @Override
    public Product getProduct(int id) {
        return null;
    }

    @Override
    public void deleteProduct(Product product) {
//
        view.showMessageDelete(product);
    }

    @Override
    public void deleteProduct(ArrayList<Product> products) {

    }

    @Override
    public void updateProducts(List<Product> producs) {

    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
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
        contentValues.put(ManageProductContract.Product.CATEGORIE_ID, product.getIdCategory());

        return contentValues;
    }

    @Override
    public void addProduct(List<Product> products) {
        try {
            for (Product tmp : products) {
                context.getContentResolver().insert(ManageProductContract.Product.CONTENT_URI, getContentProduct(tmp));
            }
        } catch (SQLException e){
            view.showMessage(e.getMessage());
        }

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(context, ManageProductContract.Product.CONTENT_URI,ManageProductContract.Product.PROJECTION,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.setNotificationUri(context.getContentResolver(),ManageProductContract.Product.CONTENT_URI);
        view.setProductCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.setProductCursor(null);
    }
}
