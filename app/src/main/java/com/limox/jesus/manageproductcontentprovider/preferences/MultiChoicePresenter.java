package com.limox.jesus.manageproductcontentprovider.preferences;

import com.limox.jesus.manageproductcontentprovider.interfaces.ProductPresenter;
import com.limox.jesus.manageproductcontentprovider.model.Product;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by usuario on 16/12/16.
 */

public class MultiChoicePresenter {
    ProductPresenter mPresent;
    HashMap<Integer,Boolean> mMap;

    public MultiChoicePresenter(ProductPresenter presenter) {
        this.mPresent = presenter;
        mMap = new HashMap<>();
    }

    public boolean isPositiveChecked(int position){
        if (!mMap.containsKey(position))
            mMap.put(position,true);
        boolean result = mMap.get(position);
        return  result;
    }

    public void setNewSelection(int position, boolean checked) {
        mMap.put(position,checked);
    }

    public void removeSelection(int position) {
        mMap.remove(position);
    }

    public void deleteSelecterProduct() {
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < mPresent.getAllProducts().size(); i++) {
            if (mMap.containsKey(i))
                if (mMap.get(i))
                    products.add(mPresent.getAllProducts().get(i));
        }

        mPresent.deleteProduct(products);
    }

    public void clearSelection() {
        mMap.clear();
    }
}
