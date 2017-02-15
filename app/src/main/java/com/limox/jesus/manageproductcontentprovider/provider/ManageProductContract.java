package com.limox.jesus.manageproductcontentprovider.provider;

import android.net.Uri;
import android.provider.BaseColumns;

import com.limox.jesus.manageproductcontentprovider.db.DatabaseContract;

import java.util.HashMap;

/**
 * Created by usuario on 20/01/17.
 * This class keep the schema of data of the db
 */
public class ManageProductContract {

    public static final String AUTHORITY = "com.limox.jesus.manageproductcontentprovider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    private ManageProductContract() {
    }

    public static class Category implements BaseColumns {
        public static final String CONTENT_PATH = "category";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        private static final String NAME = "name";
        public static final String[] PROJECTION = new String[]{BaseColumns._ID, NAME};
    }

    public static class Product implements BaseColumns {
        public static final String CONTENT_PATH = "product";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String DOSAGE = "dosage";
        public static final String BRAND = "brand";
        public static final String PRICE = "price";
        public static final String STOCK = "stock";
        public static final String IMAGE = "image";
        public static final String CATEGORIE_ID = "category_id";
        public static final String[] PROJECTION = new String[]{BaseColumns._ID, NAME, DESCRIPTION, DOSAGE, BRAND, PRICE, STOCK, IMAGE, CATEGORIE_ID};
        public static final HashMap<String, String> sProductProjectionMap;

        static {
            sProductProjectionMap = new HashMap<>();
            sProductProjectionMap.put(DatabaseContract.ProductEntry.COLUMN_NAME, DatabaseContract.ProductEntry.COLUMN_NAME);
            sProductProjectionMap.put(BaseColumns._ID, BaseColumns._ID);
            sProductProjectionMap.put(Product.NAME, DatabaseContract.ProductEntry.COLUMN_NAME);
            sProductProjectionMap.put(Product.DESCRIPTION, DatabaseContract.ProductEntry.COLUMN_DESCRIPTION);
            sProductProjectionMap.put(Product.DOSAGE, DatabaseContract.ProductEntry.COLUMN_DOSAGE);
            sProductProjectionMap.put(Product.BRAND, DatabaseContract.ProductEntry.COLUMN_BRAND);
            sProductProjectionMap.put(Product.PRICE, DatabaseContract.ProductEntry.COLUMN_PRICE);
            sProductProjectionMap.put(Product.STOCK, DatabaseContract.ProductEntry.COLUMN_STOCK);
            sProductProjectionMap.put(Product.IMAGE, DatabaseContract.ProductEntry.COLUMN_IMAGE);
            sProductProjectionMap.put(Product.CATEGORIE_ID, DatabaseContract.ProductEntry.COLUMN_CATEGORIE_ID);
        }
    }

    public static class Pharmacy implements BaseColumns {
        public static final String CONTENT_PATH = "pharmacy";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String CIF = "cif";
        public static final String ADDRESS = "address";
        public static final String PHONE = "phone";
        public static final String EMAIL = "email";
        public static final String[] PROJECTION = {BaseColumns._ID, CIF, ADDRESS, PHONE, EMAIL};
    }

    public static class InvoiceStatus implements BaseColumns {
        public static final String CONTENT_PATH = "invoiceStatus";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String NAME = "name";
        public static final String[] PROJECTION = new String[]{BaseColumns._ID, NAME};
    }

    public static class Invoice implements BaseColumns {
        public static final String CONTENT_PATH = "invoice";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String IDPHARMACY = "idPharmacy";
        public static final String DATE = "date";
        public static final String STATUS = "status";
        public static final String[] PROJECTION = new String[]{
                DatabaseContract.PharmacyEntry.COLUMN_CIF,

                "i." + DatabaseContract.InvoiceStatusEntry.COLUMN_NAME,
                DATE,
                "s." + InvoiceStatus.NAME,
                "s." + InvoiceStatus._ID
        };
    }

    public static class InvoiceLine implements BaseColumns {
        public static final String CONTENT_PATH = "invoiceLine";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String INVOICE_ID = "idInvoice";
        public static final String ORDER_PRODUCT = "orderProduct";
        public static final String ID_PRODUCT = "idProduct";
        public static final String AMOUNT = "amount";
        public static final String PRICE = "price";
        public static final String[] PROJECTION = new String[]{BaseColumns._ID, INVOICE_ID, ORDER_PRODUCT, ID_PRODUCT, AMOUNT, PRICE};
    }
}
