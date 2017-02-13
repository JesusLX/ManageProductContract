package com.limox.jesus.manageproductcontentprovider.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.limox.jesus.manageproductcontentprovider.interfaces.IProducto;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Locale;

/**
 * Entity of business for the products
 */
// Implement Serializable to can ve order
public class Product implements Serializable, Comparable<Product>, IProducto, Parcelable {
    private long mId;
    private String mName;
    private String mDescription;
    private String mDosage;
    private String mBrand;
    private double mPrice;
    private int mStock;
    private byte[] mImage;
    private int mIdCategory;
    // Son Comparators para poder ordenar de diferentes formas

    public static final Comparator<Product> NAME_COMARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public static final Comparator<Product> PRICE_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product product, Product t1) {
            return Double.compare(product.getPrice(), t1.getPrice());

        }
    };
    public static final Comparator<Product> STOCK_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product product, Product t1) {
            return Double.compare(product.getStock(), t1.getStock());

        }
    };

    public Product() {

    }


    protected Product(Parcel in) {
        mId = in.readLong();
        mName = in.readString();
        mDescription = in.readString();
        mDosage = in.readString();
        mBrand = in.readString();
        mPrice = in.readDouble();
        mStock = in.readInt();
        mImage = in.createByteArray();
        mIdCategory = in.readInt();
        idCategory = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getDosage() {
        return mDosage;
    }

    public void setDosage(String mDosage) {
        this.mDosage = mDosage;
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getBrand() {
        return mBrand;
    }

    public void setBrand(String mBrand) {
        this.mBrand = mBrand;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public int getStock() {
        return mStock;
    }

    public void setStock(int mStock) {
        this.mStock = mStock;
    }

    public byte[] getImage() {
        return mImage;
    }

    public void setImage(byte[] mImage) {
        this.mImage = mImage;
    }

    public String getFormattedPrice() {
        return String.format("%s €", mPrice);
    }

    private int idCategory;

    // This pick the format locale for the numbers of the movile
    public String getFormattedStock() {
        return String.format(Locale.getDefault(), "%d u.", mStock);
    }

    public Product(String name, String description, String dosage, String brand, double price, int stock, byte[] image, int idCategory) {

        this.mName = name;
        this.mDescription = description;
        this.mDosage = dosage;
        this.mBrand = brand;
        this.mPrice = price;
        this.mStock = stock;
        this.mImage = image;
        this.mIdCategory = idCategory;
    }

    public Product(long id, String name, String description, String dosage, String brand, double price, int stock, byte[] image, int idCategory) {
        this.mId = id;
        this.mName = name;
        this.mDescription = description;
        this.mDosage = dosage;
        this.mBrand = brand;
        this.mPrice = price;
        this.mStock = stock;
        this.mImage = image;
        this.mIdCategory = idCategory;
    }

    @Override
    public boolean equals(Object o) {
        boolean exit = true;
        if (this == o) exit = true;
        if (o == null || getClass() != o.getClass()) exit = false;

        Product product = (Product) o;

        if (!mName.equals(product.mName)) exit = false;
        if (!mDosage.equals(product.mDosage)) exit = false;
        if (mBrand.equals(product.mBrand) == false) exit = false;

        return exit;


    }

    @Override
    public int hashCode() {
        int result = mName.hashCode();
        result = 31 * result + mDosage.hashCode();
        result = 31 * result + mBrand.hashCode();
        return result;
    }

    @Override
    /**
     * Take the object casted to string
     * mName + ", " + mDesc  ription
     */
    public String toString() {
        return mName + ", " + mDescription;
    }

    @Override
    /**
     * Camps if a product is best that other
     */
    public int compareTo(Product product) {
        if (this.getName().compareTo(product.getName()) == 0)
            return this.getBrand().compareTo(product.getBrand());
        else
            return this.getName().compareTo(product.getName());
    }


    public int getIdCategory() {
        return mIdCategory;
    }

    public void setIdCategory(int idCategory) {
        mIdCategory = idCategory;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mName);
        dest.writeString(mDescription);
        dest.writeString(mDosage);
        dest.writeString(mBrand);
        dest.writeDouble(mPrice);
        dest.writeInt(mStock);
        dest.writeByteArray(mImage);
        dest.writeInt(mIdCategory);
        dest.writeInt(idCategory);
    }
}
