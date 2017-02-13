package com.limox.jesus.manageproductcontentprovider.model;

/**
 * Created by usuario on 9/01/17.
 */

public class Pharmacy {
    private long mId;
    private String mCif;
    private String mAddress;
    private String mPhone;
    private String mEmail;

    public Pharmacy(long id, String cif, String address, String phone, String email) {
        this.mId = id;
        this.mCif = cif;
        this.mAddress = address;
        this.mPhone = phone;
        this.mEmail = email;
    }
    public Pharmacy(String cif, String address, String phone, String email) {
        this.mCif = cif;
        this.mAddress = address;
        this.mPhone = phone;
        this.mEmail = email;
    }
    public Pharmacy(){}

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public String getCif() {
        return mCif;
    }

    public void setCif(String mCif) {
        this.mCif = mCif;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        this.mPhone = phone;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }
}
