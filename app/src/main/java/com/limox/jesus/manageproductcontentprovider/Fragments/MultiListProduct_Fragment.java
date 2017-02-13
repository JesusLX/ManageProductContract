package com.limox.jesus.manageproductcontentprovider.Fragments;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.limox.jesus.manageproductcontentprovider.R;
import com.limox.jesus.manageproductcontentprovider.SimpleMultiChoiceModeListener;
import com.limox.jesus.manageproductcontentprovider.adapter.ProductCursorAdapter;
import com.limox.jesus.manageproductcontentprovider.db.DatabaseContract;
import com.limox.jesus.manageproductcontentprovider.interfaces.IProducto;
import com.limox.jesus.manageproductcontentprovider.interfaces.ProductPresenter;
import com.limox.jesus.manageproductcontentprovider.model.Product;
import com.limox.jesus.manageproductcontentprovider.preferences.MultiChoicePresenter;
import com.limox.jesus.manageproductcontentprovider.presenter.ProductPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class MultiListProduct_Fragment extends Fragment implements ProductPresenter.View {

    private ListView listProduct;
    private TextView txvEmptyProduct;
    private FloatingActionButton fabAdd;
    private ProductCursorAdapter adapter;
    private Product tmpProduct;
    private ListProductListener mCallBack;
    private ProductPresenter presenter;
    private View rootView;
    private ArrayList<View> hideableViews;


    public interface ListProductListener {
        void showManageProduct(Bundle bundle); //Para lanzar manage product
    }


    public static MultiListProduct_Fragment newInstance() {

        Bundle args = new Bundle();

        MultiListProduct_Fragment fragment = new MultiListProduct_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallBack = (ListProductListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(getContext().toString() + " must implement ListProductListener ");
        }
    }

    @Override //Para cuando se va de la activity
    public void onDetach() {
        super.onDetach();//Se elimina todo lo inicializado
        mCallBack = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ProductCursorAdapter(getContext(),null,1);
        presenter = new ProductPresenterImpl(this);
        //for the back click
        setRetainInstance(true);
        // This option told the activity who the fragment has his own menu
        // and call the method callback onCreateOptionsMenu().
        setHasOptionsMenu(true);

        hideableViews = new ArrayList<>();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter = null;
        presenter = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rootView = null;
        //((CoordinatorLayout)rootView).removeViews(R.layout.fragment_list_product,0);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.loadProducts();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_list_product, null, false);

        listProduct = (ListView) rootView.findViewById(R.id.ap_lvPorductos);
        txvEmptyProduct = (TextView) rootView.findViewById(android.R.id.empty);
        fabAdd = (FloatingActionButton) rootView.findViewById(R.id.ap_fabAdd);
        listProduct.setAdapter(adapter);


        listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();

                adapter.getCursor().moveToPosition(position);
                tmpProduct = new Product();

                tmpProduct.setId(adapter.getCursor().getLong(DatabaseContract.ProductEntry.COLUMN_ID_KEY));
                tmpProduct.setName(adapter.getCursor().getString(DatabaseContract.ProductEntry.COLUMN_NAME_KEY));
                tmpProduct.setDescription(adapter.getCursor().getString(DatabaseContract.ProductEntry.COLUMN_DESCRIPTION_KEY));
                tmpProduct.setDosage(adapter.getCursor().getString(DatabaseContract.ProductEntry.COLUMN_DOSAGE_KEY));
                tmpProduct.setBrand(adapter.getCursor().getString(DatabaseContract.ProductEntry.COLUMN_BRAND_KEY));
                tmpProduct.setPrice(adapter.getCursor().getDouble(DatabaseContract.ProductEntry.COLUMN_PRICE_KEY));
                tmpProduct.setStock(adapter.getCursor().getInt(DatabaseContract.ProductEntry.COLUMN_STOCK_KEY));
                tmpProduct.setImage(adapter.getCursor().getBlob(DatabaseContract.ProductEntry.COLUMN_IMAGE_KEY));
                tmpProduct.setIdCategory(adapter.getCursor().getInt(DatabaseContract.ProductEntry.COLUMN_CATEGORIE_ID_KEY));

                bundle.putParcelable(IProducto.PRODUCT_KEY, tmpProduct);
                mCallBack.showManageProduct(bundle);
                //TODO MIRAR EL PRODUCTO QUE SE LE ENVIA

            }
        });

        fabAdd = (FloatingActionButton) rootView.findViewById(R.id.ap_fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                // Cogemos un objeto vacío
                bundle.putParcelable(IProducto.PRODUCT_KEY, null);
                mCallBack.showManageProduct(bundle);
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listProduct.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        hideableViews.add(fabAdd);
        SimpleMultiChoiceModeListener mcl = new SimpleMultiChoiceModeListener(getActivity(),hideableViews,new MultiChoicePresenter(presenter));
        listProduct.setMultiChoiceModeListener(mcl);
        listProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int posSelected = listProduct.getCheckedItemPosition();
                listProduct.setItemChecked(position,posSelected == position);
                return true;
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    /**
     * Inflete the option menu of the fragment
     *
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_listproduct, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort_alphabetically:
                //adapter.sortAllProduct();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showProducts(List<Product> producs) {
        presenter.updateProducts(producs);
    }

    /**
     * Hide or show the listView of allProducts or the TextView who warning if the list is empty
     * @param hide
     */
    private void hideList(boolean hide) {
        listProduct.setVisibility(hide ? View.GONE : View.VISIBLE);
        txvEmptyProduct.setVisibility(hide ? View.VISIBLE : View.GONE);
    }

    public void showEmptyText(boolean show) {
        hideList(show);
    }

    public void showMessage(String message) {

    }


    @Override
    public void showMessageDelete(final Product product) {
        Snackbar.make(getView(),"deshacer borrado de "+product.getName(),Snackbar.LENGTH_LONG).setAction("deshacer", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addProduct(product);

            }
        }).show();
        //SETCALLBACK: Hace una llamada a un método calback de un snackbar,
        // incluuso despues de que se haya eliminado mediante un swipe
        /*.setCallback(new Snackbar.Callback(){
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);

                if(event!=DISMISS_EVENT_SWIPE){
                    managePresenter.deleteProduct(product);
                }
            }

            @Override
            public void onShown(Snackbar snackbar) {
                super.onShown(snackbar);
            }
        }).show();*///Esto sería en el caso de que quisiesemos hacerlo con un callback del snackbar
    }

    @Override
    public void showMessageDelete(final ArrayList<Product> products) {
        Snackbar.make(getView(),"deshacer borrado de "+products.size()+" productos",Snackbar.LENGTH_LONG).setAction("deshacer", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addProduct(products);

            }
        }).show();
    }

    @Override
    public void setProductCursor(Cursor data) {
        adapter.swapCursor(data);

    }
}
