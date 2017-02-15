package com.limox.jesus.manageproductcontentprovider.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.limox.jesus.manageproductcontentprovider.R;
import com.limox.jesus.manageproductcontentprovider.adapter.InvoicePharmacyCursorAdapter;
import com.limox.jesus.manageproductcontentprovider.interfaces.InvoicePharmacyPresenter;
import com.limox.jesus.manageproductcontentprovider.presenter.InvoicePharmacyPresenterImpl;


public class InvoicePharmacy_Fragment extends Fragment {

    private InvoicePharmacyCursorAdapter mAdapter;
    private InvoicePharmacyPresenter mPresenter;
    private ListView mLvInvoices;
    private FloatingActionButton mFabAdd;
    private OnInvoicePharmacyFragmentListener mCallback;

    public interface OnInvoicePharmacyFragmentListener {
        void startAddInvoices();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new InvoicePharmacyCursorAdapter(getContext(),null,1);
        mPresenter = new InvoicePharmacyPresenterImpl(getContext());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnInvoicePharmacyFragmentListener)
            mCallback = (OnInvoicePharmacyFragmentListener) activity;
        else
            throw new ClassCastException(activity.toString()+" must implement OnInvoicePharmacyFragmentListener");
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_invoice_pharmacy, container, false);
        mLvInvoices = (ListView) rootView.findViewById(R.id.lvInvoicePharmacy);
        mFabAdd = (FloatingActionButton) rootView.findViewById(R.id.fabAdd);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLvInvoices.setAdapter(mAdapter);
        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.startAddInvoices();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
