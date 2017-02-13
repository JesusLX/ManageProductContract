package com.limox.jesus.manageproductcontentprovider.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.limox.jesus.manageproductcontentprovider.R;
import com.limox.jesus.manageproductcontentprovider.db.DatabaseContract;
import com.limox.jesus.manageproductcontentprovider.interfaces.CategoryPresenter;
import com.limox.jesus.manageproductcontentprovider.interfaces.IProducto;
import com.limox.jesus.manageproductcontentprovider.interfaces.ManagePresenter;
import com.limox.jesus.manageproductcontentprovider.model.Product;
import com.limox.jesus.manageproductcontentprovider.presenter.CategoryPresenterImpl;
import com.limox.jesus.manageproductcontentprovider.presenter.ManagePresenterImpl;
import com.limox.jesus.manageproductcontentprovider.utils.ImageResource;

import java.io.FileNotFoundException;
import java.io.InputStream;

import it.sephiroth.android.library.picasso.Picasso;

public class ManageProduct_Fragment extends Fragment implements ManagePresenter.View , CategoryPresenter.View{

    private static final int REQ_CODE_PICK_IMAGE = 1;
    EditText edtName;
    EditText edtBrand;
    EditText edtDescription;
    EditText edtPrice;
    EditText edtStock;
    EditText edtDosage;
    Spinner spCategory;
    private SimpleCursorAdapter mCursorAdapterCategory;
    ImageView ibtnImage;
    Button btnOk;
    Product product;
    private Bitmap bitmap;
    private View rootView;
    private static final int ADD_PRODUCT = 0;
    private static final int EDIT_PRODUCT = 0;
    private static final String MANAGE_KEY = "manage";
    private static ManageProduct_Fragment fragment;
    private byte[] imgRes;


    ManageProductListener mCallback;
    ManagePresenterImpl managePresenter;
    CategoryPresenter presenterCategory;

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void setCursorCategory(Cursor cursor) {
        //mCursorAdapterCategory.swapCursor(cursor);
        // Este de abajo lo cambia sin cerrar
        mCursorAdapterCategory.changeCursor(cursor);
    }

    @Override
    public Cursor getCursor() {
        return mCursorAdapterCategory.getCursor();
    }


    public interface ManageProductListener {
        void showListProduct();
    }

    public static ManageProduct_Fragment newInstance(Bundle args) {

        fragment = new ManageProduct_Fragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (ManageProductListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(getContext().toString() + " must implement ManageProductListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        managePresenter = new ManagePresenterImpl(this);
        presenterCategory = new CategoryPresenterImpl(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rootView = null;

    }

    @Override
    public void onStart() {
        super.onStart();
        // Una vez creada la vista se meten los datos
        presenterCategory.getAllCategory();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //if (rootView == null) {
        rootView = inflater.inflate(R.layout.fragment_manage_product, null);

        edtName = (EditText) rootView.findViewById(R.id.mp_edtName);
        edtBrand = (EditText) rootView.findViewById(R.id.mp_edtBrand);
        edtDescription = (EditText) rootView.findViewById(R.id.mp_edtDescription);
        edtPrice = (EditText) rootView.findViewById(R.id.mp_edtPrice);
        edtStock = (EditText) rootView.findViewById(R.id.mp_edtStock);
        edtDosage = (EditText) rootView.findViewById(R.id.mp_edtDosage);

        spCategory = (Spinner) rootView.findViewById(R.id.mp_spCategory);
        ibtnImage = (ImageView) rootView.findViewById(R.id.mp_iBtnPicture);

        btnOk = (Button) rootView.findViewById(R.id.btnOk);


        product = (Product) getArguments().getSerializable(IProducto.PRODUCT_KEY);

        if (product != null) {
            imgRes = product.getImage();
            ibtnImage.setImageBitmap(ImageResource.getBitmap(imgRes));
            edtName.setText(product.getName());
            edtBrand.setText(product.getBrand());
            edtDescription.setText(product.getDescription());
            edtPrice.setText(Double.toString(product.getPrice()));
            edtStock.setText(Integer.toString(product.getStock()));
            edtDosage.setText(product.getDosage());
            btnOk.setText(getResources().getString(R.string.action_add_product));
        } else {

            btnOk.setText(getResources().getString(R.string.action_create_product));

        }
        bitmap = ((BitmapDrawable) ibtnImage.getDrawable()).getBitmap();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] from = {DatabaseContract.CategoryEntry.COLUMN_NAME};
        int[] to = {android.R.id.text1};
        mCursorAdapterCategory = new SimpleCursorAdapter(getContext(),android.R.layout.simple_spinner_item,null,from,to,0);
        mCursorAdapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(mCursorAdapterCategory);

        ibtnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,REQ_CODE_PICK_IMAGE);
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = ((CursorAdapter)spCategory.getAdapter()).getCursor();
                cursor.moveToPosition(spCategory.getSelectedItemPosition());
                int positon = cursor.getInt(0);
                String name = edtName.getText().toString();
                String brand = edtBrand.getText().toString();
                String description = edtDescription.getText().toString();
                String dosage = edtDosage.getText().toString();
                Double price = Double.parseDouble(edtPrice.getText().toString());
                int stock = Integer.parseInt(edtStock.getText().toString());
                imgRes = ImageResource.getByte(bitmap);
                if (imgRes != null) {
                    if (product == null) {
                        product = new Product(name, description, dosage, brand, price, stock, imgRes, positon);
                        managePresenter.addProduct(product);
                    } else {
                        product = new Product(product.getId(), name, description, dosage, brand, price, stock, imgRes, positon);
                        managePresenter.updateProduct(product);
                    }
                    mCallback.showListProduct();
                }else
                    Snackbar.make(getView(),"Debe elegir una imagen",Snackbar.LENGTH_LONG);
            }
        });
        // }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        managePresenter = null;
        presenterCategory = null;
        product = null;
        //mCursorAdapterCategory.getCursor().close();
        mCursorAdapterCategory = null;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQ_CODE_PICK_IMAGE:
                if (resultCode == Activity.RESULT_OK){
                    Uri selectedImage = data.getData();
                    InputStream imageStream = null;
                    try{
                        bitmap = ImageResource.decodeUri(selectedImage);
                        ibtnImage.setImageBitmap(bitmap);
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }

                }
                break;
        }
    }
}
