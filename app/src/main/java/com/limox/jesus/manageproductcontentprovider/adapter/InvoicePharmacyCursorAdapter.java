package com.limox.jesus.manageproductcontentprovider.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.limox.jesus.manageproductcontentprovider.R;
import com.limox.jesus.manageproductcontentprovider.provider.ManageProductContract;

/**
 * Created by jesus on 14/02/17.
 */

public class InvoicePharmacyCursorAdapter extends CursorAdapter {

    public InvoicePharmacyCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    class InvoicePharmacyHolder{
        TextView txvName;
        TextView txvAddress;
        TextView txvDate;
        TextView txvState;

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.item_invoice_pharmacy,parent,false);
        InvoicePharmacyHolder holder = new InvoicePharmacyHolder();
        holder.txvAddress = (TextView) rootView.findViewById(R.id.txvAddress);
        holder.txvDate = (TextView) rootView.findViewById(R.id.txvDate);
        holder.txvState = (TextView) rootView.findViewById(R.id.txvState);
        rootView.setTag(holder);
        return rootView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        InvoicePharmacyHolder holder = (InvoicePharmacyHolder) view.getTag();
        holder.txvAddress.setText(cursor.getString(cursor.getColumnIndex(ManageProductContract.Pharmacy.ADDRESS)));
        holder.txvDate.setText(cursor.getString(cursor.getColumnIndex(ManageProductContract.Invoice.DATE)));
        holder.txvState.setText(cursor.getString(cursor.getColumnIndex(ManageProductContract.InvoiceStatus.NAME )));
    }
}
