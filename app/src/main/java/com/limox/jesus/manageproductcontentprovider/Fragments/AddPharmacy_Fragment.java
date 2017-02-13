package com.limox.jesus.manageproductcontentprovider.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.limox.jesus.manageproductcontentprovider.R;
import com.limox.jesus.manageproductcontentprovider.interfaces.PharmacyPresenter;
import com.limox.jesus.manageproductcontentprovider.model.Pharmacy;
import com.limox.jesus.manageproductcontentprovider.presenter.PharmacyPresenterImpl;


public class AddPharmacy_Fragment extends Fragment implements PharmacyPresenter.View{


    private PharmacyPresenter mPresenter;
    private Button btnAdd;
    private EditText edtCif;
    private EditText edtAddress;
    private EditText edtPhone;
    private EditText edtEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PharmacyPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_pharmacy, container, false);
        btnAdd = (Button) rootView.findViewById(R.id.btnAdd);
        edtCif = (EditText) rootView.findViewById(R.id.edtCif);
        edtAddress = (EditText) rootView.findViewById(R.id.edtAddress);
        edtPhone = (EditText) rootView.findViewById(R.id.edtPhone);
        edtEmail = (EditText) rootView.findViewById(R.id.edtEmail);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pharmacy tmp = new Pharmacy(edtCif.getText().toString(),
                        edtAddress.getText().toString(),
                        edtPhone.getText().toString(),
                        edtEmail.getText().toString());

                mPresenter.addPharmacy(tmp);
                getActivity().onBackPressed();
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

    @Override
    public void setCursorPharmacy(Cursor cursor) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }

    public interface OnAddPharmacyFragmentListener {

    }

}
