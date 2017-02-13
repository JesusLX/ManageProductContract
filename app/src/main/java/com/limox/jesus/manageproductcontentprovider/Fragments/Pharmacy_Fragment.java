package com.limox.jesus.manageproductcontentprovider.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.limox.jesus.manageproductcontentprovider.R;
import com.limox.jesus.manageproductcontentprovider.adapter.PharmacyAdapter;
import com.limox.jesus.manageproductcontentprovider.db.DatabaseHelper;
import com.limox.jesus.manageproductcontentprovider.interfaces.PharmacyPresenter;
import com.limox.jesus.manageproductcontentprovider.presenter.PharmacyPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnPharmacyFragmentListener} interface
 * to handle interaction events.
 */
public class Pharmacy_Fragment extends Fragment implements PharmacyPresenter.View{

    private ListView mLvPharmacy;
    private FloatingActionButton fabAdd;
    private PharmacyAdapter mlvAdapter;
    private PharmacyPresenter presenter;
    private OnPharmacyFragmentListener mCallback;

    public Pharmacy_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getAllPharmacy();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_farmacy, container, false);
        mLvPharmacy = (ListView) rootView.findViewById(R.id.lvPharmacy);
        fabAdd = (FloatingActionButton) rootView.findViewById(R.id.fabAdd);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLvPharmacy.setAdapter(mlvAdapter);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.startAddPharmacyFragmetn();
            }
        });
        presenter.getAllPharmacy();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPharmacyFragmentListener) {
            mCallback = (OnPharmacyFragmentListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement OnPharmacyFragmentListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mlvAdapter = new PharmacyAdapter(getContext(),null,1);
        presenter = new PharmacyPresenterImpl(this);
    }

    @Override
    public void setCursorPharmacy(Cursor cursor) {
        mlvAdapter = new PharmacyAdapter(getContext(),cursor,1);
        mLvPharmacy.setAdapter(mlvAdapter);
        cursor = null;
        DatabaseHelper.getInstance().closeDatabase();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mlvAdapter = null;
        presenter = null;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getAllPharmacy();
    }

    public interface OnPharmacyFragmentListener {
        void startAddPharmacyFragmetn();
    }

}
