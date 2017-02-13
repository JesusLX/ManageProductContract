package com.limox.jesus.manageproductcontentprovider.interfaces;

import android.content.Context;

import com.limox.jesus.manageproductcontentprovider.model.Product;

/**
 * Created by jesus on 11/12/16.
 */

public interface ManagePresenter {
    void addProduct(Product product);
    void updateProduct(Product product);
    void onDestroy();
    interface View{
        void showMessage(String message);

        Context getContext();
    }
}
