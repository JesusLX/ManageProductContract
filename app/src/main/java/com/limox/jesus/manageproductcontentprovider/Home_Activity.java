package com.limox.jesus.manageproductcontentprovider;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.limox.jesus.manageproductcontentprovider.Fragments.AddInvoice_Fragment;
import com.limox.jesus.manageproductcontentprovider.Fragments.AddPharmacy_Fragment;
import com.limox.jesus.manageproductcontentprovider.Fragments.InvoicePharmacy_Fragment;
import com.limox.jesus.manageproductcontentprovider.Fragments.ListProduct_Fragment;
import com.limox.jesus.manageproductcontentprovider.Fragments.ManageProduct_Fragment;
import com.limox.jesus.manageproductcontentprovider.Fragments.MultiListProduct_Fragment;
import com.limox.jesus.manageproductcontentprovider.Fragments.Pharmacy_Fragment;
import com.limox.jesus.manageproductcontentprovider.interfaces.InvoicePharmacyPresenter;
import com.limox.jesus.manageproductcontentprovider.settings.AccountSettings_Activity;
import com.limox.jesus.manageproductcontentprovider.settings.GeneralSettings_Activity;

public class Home_Activity extends AppCompatActivity implements ListProduct_Fragment.ListProductListener, ManageProduct_Fragment.ManageProductListener, MultiListProduct_Fragment.ListProductListener,Pharmacy_Fragment.OnPharmacyFragmentListener,AddPharmacy_Fragment.OnAddPharmacyFragmentListener,InvoicePharmacy_Fragment.OnInvoicePharmacyFragmentListener {

    private ListProduct_Fragment listProductFragment;
    private ManageProduct_Fragment manageProductFragment;
    private DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    MenuItem lastItem;
    long tiempoPrimerClick;
    Toolbar mToolbar;
    private ActionBarDrawerToggle mActionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_navigation);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        // Set my action bar
        mToolbar = (Toolbar) findViewById(R.id.h_toolbar);
        setSupportActionBar(mToolbar);

        // DrawerToggle
        mActionBarDrawerToggle = setupDrawerToggle();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);


        // Now we interactue with mToolbar with the methods of th supportActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupDrawerContent();
        if (savedInstanceState == null)
            showListProduct();

    }

    // TODO Importante, poner el syncState para que el drawertoogle funcione
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.drawer_open,R.string.drawer_close);
    }

    private void setupDrawerContent() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.action_home:
                        showListProduct();
                        break;
                    case R.id.action_product:
                        showListProduct();
                        break;
                    case R.id.action_pharmacy:
                        showListPharmacy();
                        break;
                    case R.id.action_sales:
                        startInvoices();
                        break;
                    default:

                        item.setChecked(false);
                        break;
                }


                setTitle(item.getTitle());
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }

        });
    }

    private void showListPharmacy() {
        // listProductFragment = new ListProduct_Fragment();
        Pharmacy_Fragment mlp = new Pharmacy_Fragment ();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.activity_frame_home, mlp);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    /*
            * For the event of the menu
    *
            * @param item
    * @return if whe use the event, if is false it go to the super
            */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {

            case R.id.action_settings_general:
                intent = new Intent(Home_Activity.this, GeneralSettings_Activity.class);
                startActivity(intent);
                break;
            case R.id.action_settings_account:
                intent = new Intent(Home_Activity.this, AccountSettings_Activity.class);
                startActivity(intent);
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void showManageProduct(Bundle bundle) {

        manageProductFragment = ManageProduct_Fragment.newInstance(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.activity_frame_home, manageProductFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void showListProduct() {
        // listProductFragment = new ListProduct_Fragment();
        MultiListProduct_Fragment mlp = new MultiListProduct_Fragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.activity_frame_home, mlp);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            int contador = getSupportFragmentManager().getBackStackEntryCount();
            if (contador <= 0) {
                if (tiempoPrimerClick + 2000 > System.currentTimeMillis()) {
                    super.onBackPressed();
                    return;
                } else {
                    Toast.makeText(this, "Vuelve a presionar para salir", Toast.LENGTH_SHORT).show();
                }
                tiempoPrimerClick = System.currentTimeMillis();
            } else
                super.onBackPressed();
        }
    }

    @Override
    public void startAddPharmacyFragmetn() {

        AddPharmacy_Fragment mlp = new AddPharmacy_Fragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.activity_frame_home, mlp);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void startAddInvoices() {
        AddInvoice_Fragment mlp = new AddInvoice_Fragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.activity_frame_home, mlp);
        ft.addToBackStack(null);
        ft.commit();
    }
    public void startInvoices() {
        InvoicePharmacy_Fragment mlp = new InvoicePharmacy_Fragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.activity_frame_home, mlp);
        ft.addToBackStack(null);
        ft.commit();
    }
}
