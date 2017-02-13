package com.limox.jesus.manageproductcontentprovider.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.limox.jesus.manageproductcontentprovider.adapter.ProductCursorAdapter;
import com.limox.jesus.manageproductcontentprovider.interfaces.IProducto;
import com.limox.jesus.manageproductcontentprovider.model.Product;
import com.limox.jesus.manageproductcontentprovider.interfaces.ProductPresenter;
import com.limox.jesus.manageproductcontentprovider.presenter.ProductPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class ListProduct_Fragment extends Fragment implements ProductPresenter.View {

    private ListView listProduct;
    private TextView txvEmptyProduct;
    private ProductCursorAdapter adapter;
    private FloatingActionButton fabAdd;
    private Product tmpProduct;
    private ListProductListener mCallBack;
    private ProductPresenter presenter;
    private View rootView;


    public interface ListProductListener {
        void showManageProduct(Bundle bundle); //Para lanzar manage product
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
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getAllProducts();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_list_product, container, false);

        listProduct = (ListView) rootView.findViewById(R.id.ap_lvPorductos);
        txvEmptyProduct = (TextView) rootView.findViewById(android.R.id.empty);
        fabAdd = (FloatingActionButton) rootView.findViewById(R.id.ap_fabAdd);
        listProduct.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //* Esto es de ahora*//*
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("¡Cuidado!");
                builder.setMessage("¿Estas segur@ de que quieres borrar este producto?");

                builder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // presenter.deleteProduct(adapter.getItem(position));

                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return true;
            }
        });
        listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                tmpProduct = (Product) parent.getItemAtPosition(position);
                bundle.putParcelable(IProducto.PRODUCT_KEY, tmpProduct);
                mCallBack.showManageProduct(bundle);
                //TODO MIRAR EL PRODUCTO QUE SE LE ENVIA

            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                // Cogemos un objeto vacío
                bundle.putParcelable(IProducto.PRODUCT_KEY, null);
                mCallBack.showManageProduct(bundle);
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
               // presenter.sortAllProduct();
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
        if (data != null)
        adapter.changeCursor(data);
    }
}
