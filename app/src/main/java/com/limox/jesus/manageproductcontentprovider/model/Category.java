package com.limox.jesus.manageproductcontentprovider.model;

/**
 * Created by usuario on 9/01/17.
 */

public class Category {

    private int mId;
    private String mName;

    public Category(int id, String name) {
        this.mId = id;
        this.mName = name;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }
}
