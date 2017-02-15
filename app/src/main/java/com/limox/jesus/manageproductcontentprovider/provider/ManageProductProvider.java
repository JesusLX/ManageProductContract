package com.limox.jesus.manageproductcontentprovider.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.limox.jesus.manageproductcontentprovider.R;
import com.limox.jesus.manageproductcontentprovider.db.DatabaseContract;
import com.limox.jesus.manageproductcontentprovider.db.DatabaseHelper;

/**
 * Created by usuario on 2/6/17.
 */

public class ManageProductProvider extends ContentProvider {


    private static final int PRODUCT = 1;
    private static final int PRODUCT_ID = 2;
    private static final int CATEGROY = 3;
    private static final int CATEGROY_ID = 4;
    private static final int INVOICESTATUS = 5;
    private static final int INVOICESTATUS_ID = 6;
    private static final int PHARMACY = 7;
    private static final int PHARMACY_ID = 8;
    private static final int INVOICELINE = 9;
    private static final int INVOICELINE_ID = 10;
    private static final int INVOICE = 11;
    private static final int INVOICE_ID = 12;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    SQLiteDatabase sqLiteDatabase;

    static {
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Product.CONTENT_PATH, PRODUCT);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Product.CONTENT_PATH + "/#", PRODUCT_ID);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Category.CONTENT_PATH, CATEGROY);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Category.CONTENT_PATH + "/#", CATEGROY_ID);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceStatus.CONTENT_PATH, INVOICESTATUS);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceStatus.CONTENT_PATH + "/#", INVOICESTATUS_ID);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Pharmacy.CONTENT_PATH, PHARMACY);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Pharmacy.CONTENT_PATH + "/#", PHARMACY_ID);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceLine.CONTENT_PATH, INVOICELINE);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceLine.CONTENT_PATH + "/#", INVOICELINE_ID);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Invoice.CONTENT_PATH, INVOICE);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Invoice.CONTENT_PATH + "/#", INVOICE_ID);
    }

    @Override
    public boolean onCreate() {
        sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        return sqLiteDatabase.isOpen();
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        String rowId;
        switch (uriMatcher.match(uri)) {
            case CATEGROY:
                queryBuilder.setTables(DatabaseContract.CategoryEntry.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder))
                    sortOrder = DatabaseContract.CategoryEntry.DEFAULT_SORT;
                break;
            case CATEGROY_ID:
                //rowId = uri.getLastPathSegment();
                queryBuilder.setTables(DatabaseContract.CategoryEntry.TABLE_NAME);
                rowId = uri.getPathSegments().get(1);
                selection=DatabaseContract.CategoryEntry._ID+"=?";
                selectionArgs = new String[]{rowId};
            case PRODUCT:
                queryBuilder.setTables(DatabaseContract.ProductEntry.TABLE_NAME);
                queryBuilder.setProjectionMap(ManageProductContract.Product.sProductProjectionMap);
                if (TextUtils.isEmpty(sortOrder))
                    sortOrder = DatabaseContract.ProductEntry.DEFAULT_SORT;
                break;
            case PRODUCT_ID:
                //rowId = uri.getLastPathSegment();
                queryBuilder.setTables(DatabaseContract.ProductEntry.TABLE_NAME);
                rowId = uri.getPathSegments().get(1);
                selection=DatabaseContract.ProductEntry._ID+"=?";
                selectionArgs = new String[]{rowId};
                break;
            case PHARMACY:
                queryBuilder.setTables(DatabaseContract.PharmacyEntry.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder))
                    sortOrder = DatabaseContract.PharmacyEntry.DEFAULT_SORT;
                break;
            case PHARMACY_ID:
                queryBuilder.setTables(DatabaseContract.PharmacyEntry.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder))
                    sortOrder = DatabaseContract.PharmacyEntry.DEFAULT_SORT;
                break;
            case INVOICE:
                queryBuilder.setTables(DatabaseContract.InvoiceEntry.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder))
                    sortOrder = DatabaseContract.InvoiceEntry.DEFAULT_SORT;
                break;
            case INVOICE_ID:
                queryBuilder.setTables(DatabaseContract.InvoiceEntry.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder))
                    sortOrder = DatabaseContract.InvoiceEntry.DEFAULT_SORT;
                break;
            case INVOICELINE:
                queryBuilder.setTables(DatabaseContract.InvoiceLineEntry.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder))
                    sortOrder = DatabaseContract.InvoiceLineEntry.DEFAULT_SORT;
                break;
            case INVOICELINE_ID:
                queryBuilder.setTables(DatabaseContract.InvoiceLineEntry.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder))
                    sortOrder = DatabaseContract.InvoiceLineEntry.DEFAULT_SORT;
                break;
            case INVOICESTATUS:
                queryBuilder.setTables(DatabaseContract.InvoiceStatusEntry.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder))
                    sortOrder = DatabaseContract.InvoiceStatusEntry.DEFAULT_SORT;
                break;
            case INVOICESTATUS_ID:
                queryBuilder.setTables(DatabaseContract.InvoiceStatusEntry.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder))
                    sortOrder = DatabaseContract.InvoiceStatusEntry.DEFAULT_SORT;
                break;
            case UriMatcher.NO_MATCH:
                throw new IllegalArgumentException("Uri invalir: " + uri);
        }
        String sqlQuery = queryBuilder.buildQuery(projection, selection, null, null, sortOrder, null);
        Log.i("manageproductcontent ", sqlQuery);
        Cursor cursor = queryBuilder.query(sqLiteDatabase, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        String mimetype = null;
        switch (uriMatcher.match(uri)){
            case CATEGROY:
                mimetype = "vnd.android.cursor.dir/vnd.limox.jesus.manageproductcontentprovider.category";
                break;
            case CATEGROY_ID:
                mimetype = "vnd.android.cursor.item/vnd.limox.jesus.manageproductcontentprovider.category";
                break;
            case PRODUCT:
                mimetype = "vnd.android.cursor.dir/vnd.limox.jesus.manageproductcontentprovider.product";
                break;
            case PRODUCT_ID:
                mimetype = "vnd.android.cursor.item/vnd.limox.jesus.manageproductcontentprovider.product";
                break;
            case PHARMACY:
                mimetype = "vnd.android.cursor.dir/vnd.limox.jesus.manageproductcontentprovider.pharmacy";
                break;
            case PHARMACY_ID:
                mimetype = "vnd.android.cursor.item/vnd.limox.jesus.manageproductcontentprovider.pharmacy";
                break;
        }
        return mimetype;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        Uri newUri = null;
        long regId = 0;

        switch (uriMatcher.match(uri)){
            case CATEGROY:
                regId = sqLiteDatabase.insert(DatabaseContract.CategoryEntry.TABLE_NAME,null,values);
                newUri = ContentUris.withAppendedId(uri,regId);
                break;
            case PRODUCT:
                String tableName = DatabaseContract.ProductEntry.TABLE_NAME;
                regId = sqLiteDatabase.insert(tableName,null,values);
                newUri = ContentUris.withAppendedId(uri,regId);
                break;
            case PHARMACY:
                regId = sqLiteDatabase.insert(DatabaseContract.PharmacyEntry.TABLE_NAME,null,values);
                newUri = ContentUris.withAppendedId(uri,regId);
                break;
        }

        if (regId != -1){
            //Notificar a los observadores que se ha moificado una uri
            getContext().getContentResolver().notifyChange(newUri,null);
        }else {
            throw new SQLException(getContext().getResources().getString(R.string.error_insert));
        }

        return newUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Uri newUri = null;
        int regId = 0;
        switch (uriMatcher.match(uri)){
            case CATEGROY:
                regId = sqLiteDatabase.delete(DatabaseContract.CategoryEntry.TABLE_NAME,selection,selectionArgs);
                newUri = ContentUris.withAppendedId(uri,regId);
                break;
            case PRODUCT:
               regId = sqLiteDatabase.delete(DatabaseContract.ProductEntry.TABLE_NAME,selection,selectionArgs);
                newUri = ContentUris.withAppendedId(uri,regId);
                break;
        }
        if (regId!= -1){
            //Notificar a los observadores que se ha moificado una uri
            getContext().getContentResolver().notifyChange(newUri,null);
        }else {
            throw new SQLException(getContext().getResources().getString(R.string.error_delete));
        }

        return regId;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int affected = 0;
        Uri newUri = null;
        String rowId;
        Log.e("Uri",uri.toString());
        switch (uriMatcher.match(uri)){
            case CATEGROY:
                affected = sqLiteDatabase.update(DatabaseContract.CategoryEntry.TABLE_NAME,values,selection,selectionArgs);
                newUri = ContentUris.withAppendedId(uri,affected);
                break;
            case CATEGROY_ID:
                //rowId = uri.getLastPathSegment();
                rowId = uri.getPathSegments().get(1);
                selection=DatabaseContract.CategoryEntry._ID+"=?";
                selectionArgs = new String[]{rowId};
                affected = sqLiteDatabase.update(DatabaseContract.CategoryEntry.TABLE_NAME,values,selection,selectionArgs);
                break;
            case PRODUCT_ID:
                //rowId = uri.getLastPathSegment();
                rowId = uri.getPathSegments().get(1);
                selection=DatabaseContract.ProductEntry._ID+"=?";
                selectionArgs = new String[]{rowId};
                affected = sqLiteDatabase.update(DatabaseContract.ProductEntry.TABLE_NAME,values,selection,selectionArgs);
                newUri = ContentUris.withAppendedId(uri,affected);
                break;
            case PRODUCT:
                affected = sqLiteDatabase.update(DatabaseContract.ProductEntry.TABLE_NAME,values,selection,selectionArgs);
                newUri = ContentUris.withAppendedId(uri,affected);
                break;
        }
       // if (affected > 0){
            //Notificar a los observadores que se ha moificado una uri
            getContext().getContentResolver().notifyChange(newUri,null);
        /*}else {
            throw new SQLException(getContext().getResources().getString(R.string.error_update));
        }*/

        return affected;
    }
}
