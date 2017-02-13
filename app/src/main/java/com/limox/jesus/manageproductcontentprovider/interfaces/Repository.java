package com.limox.jesus.manageproductcontentprovider.interfaces;

import com.limox.jesus.manageproductcontentprovider.model.Product;

import java.util.List;

/**
 * Created by jesus on 11/12/16.
 */

public interface Repository {
    List<Product> allProducts = null;

    Product getProductById(int id);
    void deleteProduct(Product product);
    void addProduct(Product product);
    void updateProduct(Product product);
}
