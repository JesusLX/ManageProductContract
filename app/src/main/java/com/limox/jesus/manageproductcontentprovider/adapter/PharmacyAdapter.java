package com.limox.jesus.manageproductcontentprovider.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.limox.jesus.manageproductcontentprovider.R;
import com.limox.jesus.manageproductcontentprovider.model.Pharmacy;

/**
 * Created by jesus on 30/01/17.
 */

public class PharmacyAdapter extends CursorAdapter {




    public class PharmacyHolder{
        TextView txvCif;
        TextView txvAddress;
        TextView txvPhone;
        TextView txvEmail;
    }

    public PharmacyAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }



    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View rootView = layoutInflater.inflate(R.layout.item_pharmacy,parent,false);
        PharmacyHolder viewHolder = new PharmacyHolder();
        viewHolder.txvCif = (TextView) rootView.findViewById(R.id.p_txvCif);
        viewHolder.txvAddress = (TextView) rootView.findViewById(R.id.p_txvAddress);
        viewHolder.txvPhone = (TextView) rootView.findViewById(R.id.p_txvPhone);
        viewHolder.txvEmail = (TextView) rootView.findViewById(R.id.p_txvEmail);
        rootView.setTag(viewHolder);
        return rootView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        PharmacyHolder viewHolder = (PharmacyHolder) view.getTag();
        viewHolder.txvCif.setText(cursor.getString(1));
        viewHolder.txvAddress.setText(cursor.getString(2));
        viewHolder.txvPhone.setText(cursor.getString(3));
        viewHolder.txvEmail.setText(cursor.getString(4));
    }

    @Override
    public Object getItem(int position) {
        getCursor().moveToPosition(position);
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setId(getCursor().getLong(0));
        pharmacy.setCif(getCursor().getString(1));
        pharmacy.setAddress(getCursor().getString(2));
        pharmacy.setPhone(getCursor().getString(3));
        pharmacy.setEmail(getCursor().getString(4));

        return pharmacy;
    }

}
