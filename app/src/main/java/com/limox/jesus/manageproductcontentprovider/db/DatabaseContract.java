package com.limox.jesus.manageproductcontentprovider.db;

import android.provider.BaseColumns;


/**
 * Created by usuario on 2/6/17.
 */

public class DatabaseContract {
    private DatabaseContract() {
    }

    public static class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME = "name";
        public static final String SQL_CREATE_ENTRIES = String.format(" CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT NOT NULL)",
                TABLE_NAME, BaseColumns._ID, COLUMN_NAME);
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s ", TABLE_NAME);
        public static final String SQL_INSERT_ENTRIES = String.format("INSERT INTO %s (%s) VALUES %s,%s,%s", TABLE_NAME, COLUMN_NAME, "('pastilla')", "('polvo')", "('jarabe')");
        public static final String[] ALL_COLUMN = new String[]{BaseColumns._ID, COLUMN_NAME};
        public static final String DEFAULT_SORT = COLUMN_NAME;
    }

    public static class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "product";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DOSAGE = "dosage";
        public static final String COLUMN_BRAND = "brand";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_STOCK = "stock";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_CATEGORIE_ID = "idCategory";
        public static final int COLUMN_ID_KEY = 0;
        public static final int COLUMN_NAME_KEY = 1;
        public static final int COLUMN_DESCRIPTION_KEY = 2;
        public static final int COLUMN_DOSAGE_KEY = 3;
        public static final int COLUMN_BRAND_KEY = 4;
        public static final int COLUMN_PRICE_KEY = 5;
        public static final int COLUMN_STOCK_KEY = 6;
        public static final int COLUMN_IMAGE_KEY = 7;
        public static final int COLUMN_CATEGORIE_ID_KEY = 8;
        public static final String PRODUCT_JOIN_CATEGORY = String.format(" %s INNER JOIN %s ON %s=%s.%s", TABLE_NAME, CategoryEntry.TABLE_NAME, COLUMN_CATEGORIE_ID, CategoryEntry.TABLE_NAME, BaseColumns._ID);
        public static final String[] COLUMNS_PRODUCT_JOIN_CATEGORY = new String[]{
                TABLE_NAME + "." + COLUMN_NAME, COLUMN_DESCRIPTION, CategoryEntry.TABLE_NAME + "." + CategoryEntry.COLUMN_NAME
        };
        public static final String[] ALL_COLUMN = new String[]{BaseColumns._ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_DOSAGE, COLUMN_BRAND, COLUMN_PRICE, COLUMN_STOCK, COLUMN_IMAGE, COLUMN_CATEGORIE_ID};
        public static final String REFERENCE_CATEGORIE_ID = String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT", CategoryEntry.TABLE_NAME, BaseColumns._ID);
        public static final String SQL_CREATE_ENTRIES = String.format(" CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " %s TEXT NOT NULL," +
                        " %s TEXT NOT NULL," +
                        " %s TEXT NOT NULL," +
                        " %s TEXT NOT NULL," +
                        " %s REAL NOT NULL," +
                        " %s INTEGER NOT NULL," +
                        " %s BLOB       ," +
                        " %s INTEGER NOT NULL %s)"
                , TABLE_NAME,
                BaseColumns._ID,
                COLUMN_NAME,
                COLUMN_DESCRIPTION,
                COLUMN_DOSAGE,
                COLUMN_BRAND,
                COLUMN_PRICE,
                COLUMN_STOCK,
                COLUMN_IMAGE,
                COLUMN_CATEGORIE_ID,
                REFERENCE_CATEGORIE_ID);
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s ", TABLE_NAME);
        // public static final String SQL_INSERT_ENTRIES = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s) VALUES %s",
        //        TABLE_NAME,COLUMN_NAME,COLUMN_DESCRIPTION,COLUMN_DOSAGE,COLUMN_BRAND,COLUMN_PRICE,COLUMN_STOCK,COLUMN_IMAGE,
        //      String.format("('%s','%s','%s','%s','%s','%s','%s',)",
        //            "Ibopufeno","Descripcion","600","Farmacol","66.5","60", ));
        public static final String DEFAULT_SORT = COLUMN_NAME;
    }

    public static class PharmacyEntry implements BaseColumns {
        public static final String TABLE_NAME = "pharmacy";
        public static final String COLUMN_CIF = "cif";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_EMAIL = "email";
        public static final String SQL_CREATE_ENTRIES = String.format(" CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " %s TEXT NOT NULL," +
                        " %s TEXT NOT NULL," +
                        " %s TEXT NOT NULL," +
                        " %s TEXT NOT NULL)"
                , TABLE_NAME, BaseColumns._ID, COLUMN_CIF, COLUMN_ADDRESS, COLUMN_PHONE, COLUMN_EMAIL);
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s ", TABLE_NAME);
        public static final String[] ALL_COLUMN = {BaseColumns._ID, COLUMN_CIF, COLUMN_ADDRESS, COLUMN_PHONE, COLUMN_EMAIL};
        //public static final String SQL_INSERT_ENTRIES = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES %s",TABLE_NAME,"");
        public static final String DEFAULT_SORT = COLUMN_CIF;
    }

    public static class InvoiceStatusEntry implements BaseColumns {
        public static final String TABLE_NAME = "invoiceStatus";
        public static final String COLUMN_NAME = "name";
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (%s INTEGER NOT NULL, " +
                " %s TEXT NOT NULL)", TABLE_NAME, BaseColumns._ID, COLUMN_NAME);
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s ", TABLE_NAME);
        public static final String DEFAULT_SORT = COLUMN_NAME;
    }

    public static class InvoiceEntry implements BaseColumns {
        public static final String TABLE_NAME = "invoice";
        public static final String COLUMN_PHARMACY_ID = "idPharmacy";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_STATUS = "status";
        public static final String REFERENCE_PHARMACY_ID = String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT", PharmacyEntry.TABLE_NAME, BaseColumns._ID);
        public static final String REFERENCE_STATUS_ID = String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT", InvoiceStatusEntry.TABLE_NAME, BaseColumns._ID);
        public static final String SQL_CREATE_ENTRIES = String.format(" CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " %s INTEGER NOT NULL %s," +
                        " %s TEXT NOT NULL," +
                        " %s INTEGER NOT NULL %s)"
                , TABLE_NAME, BaseColumns._ID, COLUMN_PHARMACY_ID, REFERENCE_PHARMACY_ID, COLUMN_DATE, COLUMN_STATUS, REFERENCE_STATUS_ID);
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s ", TABLE_NAME);
        public static final String DEFAULT_SORT = COLUMN_PHARMACY_ID;
        public static final String INVOICE_JOIN = " i INNER JOIN pharmacy p ON i._id = p._id INNER JOIN invoicestatus s ON i.status = s._id";
    }

    public static class InvoiceLineEntry implements BaseColumns {

        public static final String TABLE_NAME = "invoiceLine";
        public static final String COLUMN_INVOICE_ID = "idInvoice";
        public static final String COLUMN_ORDER_PRODUCT = "orderProduct";
        public static final String COLUMN_PRODUCT_ID = "idProduct";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_PRICE = "price";
        public static final String SELECT_PRIMARY_KEY = String.format("PRIMARY KEY (%s)", COLUMN_INVOICE_ID);
        public static final String REFERENCE = "REFERENCES %s (%s) ON UPDATE CASCADE ON  DELETE RESTRICT";
        public static final String REFERENCE_ID_INVOICE = String.format(REFERENCE, InvoiceEntry.TABLE_NAME, InvoiceEntry._ID);
        public static final String REFERENCE_ID_PRODUCT = String.format(REFERENCE, ProductEntry.TABLE_NAME, ProductEntry._ID);

        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                        " %s INTEGER NOT NULL %s," +
                        " %s INTEGER," +
                        " %s INTEGER NOT NULL %s," +
                        " %s INTEGER NOT NULL," +
                        " %s REAL NOT NULL, %s)",
                TABLE_NAME,
                COLUMN_INVOICE_ID,
                REFERENCE_ID_INVOICE,
                COLUMN_ORDER_PRODUCT,
                COLUMN_PRODUCT_ID,
                REFERENCE_ID_PRODUCT,
                COLUMN_AMOUNT,
                COLUMN_PRICE,
                SELECT_PRIMARY_KEY
        );


        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s",
                TABLE_NAME);

        public static final String DEFAULT_SORT = COLUMN_INVOICE_ID;
    }
}
