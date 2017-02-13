package com.limox.jesus.manageproductcontentprovider.interfaces;

import android.content.Context;
import android.database.Cursor;

import com.limox.jesus.manageproductcontentprovider.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 9/12/16.
 */
public interface ProductPresenter {
    void loadProducts();
    Product getProduct(int id);
    void deleteProduct(Product product);
    void onDestroy();
    List<Product> getAllProducts();
    void addProduct(Product product);
    void addProduct(List<Product> products);

    void deleteProduct(ArrayList<Product> products);

    void updateProducts(List<Product> producs);

    interface View{
        void showProducts(List<Product> products);

        void showEmptyText(boolean show);

        void showMessage(String message);

        void showMessageDelete(Product product);

        void showMessageDelete(ArrayList<Product> products);

        Context getContext();

        void setProductCursor(Cursor data);
    }


}
