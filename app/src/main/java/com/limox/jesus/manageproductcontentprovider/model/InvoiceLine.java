package com.limox.jesus.manageproductcontentprovider.model;

/**
 * Created by usuario on 9/01/17.
 */

public class InvoiceLine {
    private int mIdInvoice;
    private int mOrderProduct;
    private int mIdProduct;
    private int mAmount;
    private double mPrice;

    public InvoiceLine(int idInvoice, int orderProduct, int idProduct, int amount, double price) {
        this.mIdInvoice = idInvoice;
        this.mOrderProduct = orderProduct;
        this.mIdProduct = idProduct;
        this.mAmount = amount;
        this.mPrice = price;
    }

    public int getIdInvoice() {
        return mIdInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.mIdInvoice = idInvoice;
    }

    public int getOrderProduct() {
        return mOrderProduct;
    }

    public void setOrderProduct(int orderProduct) {
        this.mOrderProduct = orderProduct;
    }

    public int getIdProduct() {
        return mIdProduct;
    }

    public void setIdProduct(int idProduct) {
        this.mIdProduct = idProduct;
    }

    public int getAmount() {
        return mAmount;
    }

    public void setAmount(int amount) {
        this.mAmount = amount;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        this.mPrice = price;
    }
}
