package com.limox.jesus.manageproductcontentprovider.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.limox.jesus.manageproductcontentprovider.R;
import com.limox.jesus.manageproductcontentprovider.interfaces.IValidateAccount;
import com.limox.jesus.manageproductcontentprovider.interfaces.IValidateAccount.Presenter;
import com.limox.jesus.manageproductcontentprovider.model.Error;
import com.limox.jesus.manageproductcontentprovider.utils.ErrorMapUtils;

/**
 * Created by jesus on 20/10/16.
 */

public class LoginPresenterImpl implements Presenter {
    // Viene de IValidateAccount

    private static final String TAG = "manageproducts";
    private IValidateAccount.View view;
    private int validateUser;
    private int validatePassword;
    private Context mContext;

    public LoginPresenterImpl(IValidateAccount.View view) {
        this.view = view;
        this.mContext = (Context)view;
    }
    public void  validateCredentialLogin(String user, String password){
         validateUser = validateCredentialsUser(user) ;
         validatePassword = validateCredentialsPassword(password);

        if (validateUser== Error.OK) {
            if (validatePassword== Error.OK) {
                // Se puede utilizar la llamada al método starActivity con un Intent como
                // parámetro y no tener que implementar el método startActivity en la vista
                // porque llama al método super.startActivity() dentro de la Activity
                view.startActivity();
            } else{
                //                                  coge el mapa del contexto          coge el valor de la key
                String nameResource= ErrorMapUtils.getErrorMapResource(mContext).get(String.valueOf(validatePassword));
                view.setMessageError(nameResource, R.id.tilPassword);
            }
        }else {
            //                                  coge el mapa del contexto          coge el valor de la key
            String nameResource= ErrorMapUtils.getErrorMapResource(mContext).get(String.valueOf(validateUser));
            view.setMessageError(nameResource, R.id.tilUser);
        }
    }


    @Override
    public int validateCredentialsUser(String user) {
        boolean validate = true;
        String msgError = "";
        int idError = 0;
        if (TextUtils.isEmpty(user)) {
            return Error.DATA_EMPTY;
        }
        return Error.OK;
    }

    @Override
    public int validateCredentialsPassword(String password) {
        boolean validate = false;
        String msgError = "";
        int idError = R.id.edtPassword;

        if (TextUtils.isEmpty(password)) {
            idError = Error.DATA_EMPTY;
        } else if (!password.matches("^.{0,}([0-9])+.{0,}$")) {
            idError = Error.PASSWORD_CASE;
        } else if (!password.matches("^.+[a-zA-Z]+.+$")) {
            idError = Error.PASSWORD_DIGIT;
        } else if (password.length() < 8) {
            idError = Error.PASSWORD_LENGTH;
        }
        else {
            idError = Error.OK;
        }
        // Throw the error
        // If wasn't found any error it sends the code for the good login
        return idError;
    }
}

