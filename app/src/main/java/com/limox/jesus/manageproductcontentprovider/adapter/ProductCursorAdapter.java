package com.limox.jesus.manageproductcontentprovider.adapter;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.limox.jesus.manageproductcontentprovider.R;
import com.limox.jesus.manageproductcontentprovider.db.DatabaseContract;
import com.limox.jesus.manageproductcontentprovider.provider.ManageProductContract;
import com.limox.jesus.manageproductcontentprovider.utils.ImageResource;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by usuario on 9/02/17.
 */

public class ProductCursorAdapter extends CursorAdapter {

    public class ProductHolder{
        ImageView ivProduct;
        TextView txvName;
        TextView txvStock;
        TextView txvPrice;
    }

    public ProductCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View rootView = layoutInflater.inflate(R.layout.item_product,parent,false);
        ProductHolder viewHolder = new ProductHolder();
        viewHolder.ivProduct = (ImageView) rootView.findViewById(R.id.ivwImage_item);
        viewHolder.txvName = (TextView) rootView.findViewById(R.id.txvName_item);
        viewHolder.txvPrice = (TextView) rootView.findViewById(R.id.txvPrice_item);
        viewHolder.txvStock = (TextView) rootView.findViewById(R.id.txvStock_item);
        rootView.setTag(viewHolder);
        return rootView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (cursor != null) {
            ProductHolder viewHolder = (ProductHolder) view.getTag();
            if (cursor.getBlob(DatabaseContract.ProductEntry.COLUMN_IMAGE_KEY) != null) {
                //Picasso.with(context).load(cursor.getString(DatabaseContract.ProductEntry.COLUMN_IMAGE_KEY)).into(viewHolder.ivProduct);
                viewHolder.ivProduct.setImageBitmap(ImageResource.getBitmap(cursor.getBlob(DatabaseContract.ProductEntry.COLUMN_IMAGE_KEY)));
            }else
                viewHolder.ivProduct.setImageResource(R.drawable.img1);


            viewHolder.txvName.setText(cursor.getString(DatabaseContract.ProductEntry.COLUMN_NAME_KEY));
            viewHolder.txvStock.setText(cursor.getString(DatabaseContract.ProductEntry.COLUMN_STOCK_KEY));
            viewHolder.txvPrice.setText(cursor.getString(DatabaseContract.ProductEntry.COLUMN_PRICE_KEY));
        }
    }
}
