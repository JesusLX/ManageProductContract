package com.limox.jesus.manageproductcontentprovider.model;

/**
 * Created by usuario on 9/01/17.
 */

public class Invoice {
    private int mId;
    private int mIdPharmacy;
    private String mDate;
    private int mState;

    public Invoice(int id, int idPharmacy, String date, int state) {
        this.mId = id;
        this.mIdPharmacy = idPharmacy;
        this.mDate = date;
        this.mState = state;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public int getIdPharmacy() {
        return mIdPharmacy;
    }

    public void setIdPharmacy(int idPharmacy) {
        this.mIdPharmacy = idPharmacy;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public int getState() {
        return mState;
    }

    public void setState(int state) {
        this.mState = state;
    }
}
