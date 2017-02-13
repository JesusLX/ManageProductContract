package com.limox.jesus.manageproductcontentprovider;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;

import com.limox.jesus.manageproductcontentprovider.preferences.MultiChoicePresenter;

import java.util.ArrayList;

/**
 * Created by usuario on 16/12/16.
 * This class manage the multiselect listener
 */
public class SimpleMultiChoiceModeListener implements AbsListView.MultiChoiceModeListener {

    Context mContext;
    MultiChoicePresenter mPresenter;
    private int statusBarColor;
    private int cont;
    ArrayList<View> listView;

    public SimpleMultiChoiceModeListener(Context context, ArrayList<View> lista,MultiChoicePresenter presenter) {
        this.mContext = context;
        this.mPresenter = presenter;
        this.cont = 0;
        listView = lista;
    }

    /**
     *
     * @param mode Es la barra de menu que se superpone a la toolbar
     * @param position posicion del elemento en la lista
     * @param id
     * @param checked
     */
    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        if (checked) {
            cont++;
            mPresenter.setNewSelection(position,checked);
        }else {
            cont--;
            mPresenter.removeSelection(position);

        }
        if (mode != null)
            mode.setTitle(cont+"");
    }

    /**
     * Se está creando el menu de acción
     * @param mode
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
        for (View v: listView) {
            v.setVisibility(View.GONE);
        }
        return true;
    }

    /**
     * Cuando termina de crearse, o cuando se actualiza
     * @param mode
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
       if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            statusBarColor = ((AppCompatActivity) mContext).getWindow().getStatusBarColor();
            ((AppCompatActivity)mContext).getWindow().setStatusBarColor(ContextCompat.getColor(mContext,R.color.background_statusbar));
        }
        return false;
    }

    /**
     * Clicamos en los iconos de la toolbar
     * @param mode
     * @param item
     * @return
     */
    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete_product:
                mPresenter.deleteSelecterProduct();
            break;
        }
        mode.finish();
        return true;
    }

    /**
     *
     * @param mode
     */
    @Override
    public void onDestroyActionMode(ActionMode mode) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            ((AppCompatActivity)mContext).getWindow().setStatusBarColor(statusBarColor);
        }
        mPresenter.clearSelection();
        for (View v: listView) {
            v.setVisibility(View.VISIBLE);

        }
        cont = 0;
    }
}
