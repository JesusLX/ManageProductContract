package com.limox.jesus.manageproductcontentprovider.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.limox.jesus.manageproductcontentprovider.ManageProductApplication;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by usuario on 20/01/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "ManageProduct.db";
    private volatile static DatabaseHelper mInstance; //IMPORTANTE poniendo volatile evitamos de que se cachee el valor de mInstance y otro hilo que quiera acceder lo vea como null
    private AtomicInteger mOpenCounter;
    private SQLiteDatabase mDatabase;

    public synchronized static DatabaseHelper getInstance() {
        if (mInstance == null) {
            mInstance = new DatabaseHelper();// Así para asegurar que el contexto no se vaya a null

        }
        return mInstance;

    }

    private DatabaseHelper() {
        super(ManageProductApplication.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
        mOpenCounter = new AtomicInteger();
    }

    public synchronized SQLiteDatabase openDatabase() {

        if (mOpenCounter.incrementAndGet() == 1) {
            mDatabase = getWritableDatabase();
        }
        return mDatabase;
    }

    /**
     * Close database only if the last thread whose call this method is the last using the db
     */
    public synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            mDatabase.close();
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN)
                db.setForeignKeyConstraintsEnabled(true);
            else
                db.execSQL("PRAGMA foreign_keys=ON");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try{
            db.execSQL(DatabaseContract.CategoryEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.ProductEntry.SQL_CREATE_ENTRIES);

            Log.d("prd",DatabaseContract.ProductEntry.SQL_CREATE_ENTRIES);

            db.execSQL(DatabaseContract.PharmacyEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.InvoiceStatusEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.InvoiceEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.InvoiceLineEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.CategoryEntry.SQL_INSERT_ENTRIES);
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.e("manageproductdataba", "Error al crear la base de datos: " + e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransaction();
        try {
            db.execSQL(DatabaseContract.CategoryEntry.SQL_DELETE_ENTRIES);
            db.execSQL(DatabaseContract.ProductEntry.SQL_DELETE_ENTRIES);
            db.execSQL(DatabaseContract.PharmacyEntry.SQL_DELETE_ENTRIES);
            db.execSQL(DatabaseContract.InvoiceStatusEntry.SQL_DELETE_ENTRIES);
            db.execSQL(DatabaseContract.InvoiceEntry.SQL_DELETE_ENTRIES);
            db.execSQL(DatabaseContract.InvoiceLineEntry.SQL_DELETE_ENTRIES);
            onCreate(db);
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.e("manageproductdataba", "Error al actualizar la base de datos: " + e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, newVersion, oldVersion);
    }

    public SQLiteDatabase open() {
        return getWritableDatabase();
    }
}
