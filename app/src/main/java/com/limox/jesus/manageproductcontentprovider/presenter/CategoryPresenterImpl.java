package com.limox.jesus.manageproductcontentprovider.presenter;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;


import com.limox.jesus.manageproductcontentprovider.db.DatabaseContract;
import com.limox.jesus.manageproductcontentprovider.interfaces.CategoryPresenter;
import com.limox.jesus.manageproductcontentprovider.provider.ManageProductContract;

/**
 * Created by usuario on 26/01/17.
 */

public class CategoryPresenterImpl implements CategoryPresenter, LoaderManager.LoaderCallbacks<Cursor> {

    private final Context context;
    private CategoryPresenter.View view;
    private final static int CATEGORY = 1;

    public CategoryPresenterImpl(CategoryPresenter.View view) {
        this.view = view;
        this.context = view.getContext();
    }

    @Override
    public void getAllCategory() {
        /*Cursor cursor = DatabaseManager.getInstance().getAllCategories();
        adapter.swapCursor(cursor);

        DatabaseHelper.getInstance().closeDatabase();*/
        Loader<Cursor> loader = ((Activity)context).getLoaderManager().getLoader(CATEGORY);
        if (loader == null)
            ((Activity)context).getLoaderManager().initLoader(CATEGORY,null,this);
        else
            ((Activity)context).getLoaderManager().restartLoader(CATEGORY,null,this);


    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Loader<Cursor> loader = null;

        loader = new CursorLoader(context, ManageProductContract.Category.CONTENT_URI,ManageProductContract.Category.PROJECTION,null,null, DatabaseContract.CategoryEntry.DEFAULT_SORT);

        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        view.setCursorCategory(data);
        view.getCursor().setNotificationUri(context.getContentResolver(),ManageProductContract.Category.CONTENT_URI);
        //DatabaseHelper.getInstance().closeDatabase();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.setCursorCategory(null);
    }

}
