package com.limox.jesus.manageproductcontentprovider;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.limox.jesus.manageproductcontentprovider.Fragments.ListProduct_Fragment;
import com.limox.jesus.manageproductcontentprovider.interfaces.SignUpPresenter;
import com.limox.jesus.manageproductcontentprovider.presenter.LoginPresenterImpl;

public class Login_Activity extends AppCompatActivity implements SignUpPresenter.View {

    private LoginPresenterImpl mLoginPresenterImpl;
    private EditText mEdtPassword;
    private EditText mEdtUser;
    private Button mBtnLogin;
    private TextInputLayout mTilUser;
    private TextInputLayout mTilPassword;
    private TextView mTxvForgot;
    private TextView mTxvCreateu;
    private final String TAG = "logintextinputlayout";
    private ViewGroup layout; //Cogemos el padre, generalizando en el padre de los layout

    long tiempoPrimerClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //region Initiations
        mLoginPresenterImpl = new LoginPresenterImpl(this);
        layout = (ViewGroup) findViewById(R.id.activity_login);
        mEdtUser = (EditText) findViewById(R.id.edtUser);
        mEdtPassword = (EditText) findViewById(R.id.edtPassword);
        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mTilUser = (TextInputLayout) findViewById(R.id.tilUser);
        mTilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        mTxvForgot = (TextView) findViewById(R.id.txvForgot);
        mTxvCreateu = (TextView) findViewById(R.id.txvCreateu);
        //endregion

        // Typeface typeface = Typeface.createFromAsset(getAssets(), "hipster.ttf");
        // mTxvForgot.setTypeface(typeface);

        //region Anonymous method
        mEdtUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTilUser.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mEdtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTilPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validates the credentials picked from the name and the password
                mLoginPresenterImpl.validateCredentialLogin(mEdtUser.getText().toString(), mEdtPassword.getText().toString());
            }
        });

        mTxvCreateu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_Activity.this, SignUp_Activity.class));
            }
        });
        //endregion

        //Comprobar la persistencia del objeto Application
        /* I comment that because if I let it free I cant cast class at the other activity
            cause the Manifest need the application name of who is going to use others class resources
        if (((LoginApplication)getApplicationContext()).getUser() != null){
            Log.d(TAG, ((LoginApplication)getApplicationContext()).getUser().toString());
        }else {
            Log.d(TAG, "No hay nadie");
        }
        */
        // All who is iniciated at onCreate must be finalize  at onStop
        Log.d(TAG, "Activity inicializada");

    }

    @Override
    public void setMessageError(String nameResource, int idView) {
        //Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
        // Se tiene que recoger el recurso cuyo nombre se el que
        // pasa en nameResource

        String messageError = getResources().getString(getResources().getIdentifier(nameResource, "string", getPackageName()));
        switch (idView) {
            case R.id.tilUser:
                // Lanzamos el error con el mensaje en un snackbar
                Snackbar.make(layout, messageError, Snackbar.LENGTH_SHORT).show();

                break;
            case R.id.tilPassword:
                Snackbar.make(layout, messageError, Snackbar.LENGTH_SHORT).show();
                break;
            case 0: // Login accepted
                Intent intent = new Intent(this, ListProduct_Fragment.class);
                startActivity(intent);
                finish();
                break;
        }

    }

    //Es el par de onCreate
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Activity finalizada");
    }


    /**
     * Turns the values of mEdtPassword and mEdtUser to ""
     */
    private void resetValues() {
        mEdtPassword.setText("");
        mEdtUser.setText("");
    }

    public void startActivity() {
        Intent intent = new Intent(Login_Activity.this, Home_Activity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (tiempoPrimerClick + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(this, "Vuelve a presionar para salir", Toast.LENGTH_SHORT).show();
        }
        tiempoPrimerClick = System.currentTimeMillis();
    }
}
