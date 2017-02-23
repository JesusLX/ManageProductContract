package com.limox.jesus.manageproductcontentprovider.interfaces;

import android.database.Cursor;

/**
 * Created by jesus on 15/02/17.
 */

public interface InvoicePharmacyPresenter {
    void getAllInvoices();

    public interface View {
        void setCursor(Cursor data);
    }
}
