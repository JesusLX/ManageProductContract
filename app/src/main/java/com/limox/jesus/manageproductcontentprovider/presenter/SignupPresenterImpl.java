package com.limox.jesus.manageproductcontentprovider.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;

import com.limox.jesus.manageproductcontentprovider.R;
import com.limox.jesus.manageproductcontentprovider.interfaces.IValidateAccount;
import com.limox.jesus.manageproductcontentprovider.interfaces.SignUpPresenter;
import com.limox.jesus.manageproductcontentprovider.model.Error;
import com.limox.jesus.manageproductcontentprovider.preferences.AccountPreferenceImpl;
import com.limox.jesus.manageproductcontentprovider.utils.ErrorMapUtils;


/**
 * Created by usuario on 11/11/16.
 */

public class SignupPresenterImpl implements SignUpPresenter.Presenter, SignUpPresenter.PresenterUser {

    private int validateUser;
    private int validatePassword;
    private int validateEmail;
    private IValidateAccount.View view;
    private Context context;

    public SignupPresenterImpl(IValidateAccount.View view) {
        this.view = view;
        this.context = (Context) view;
    }

    public void validateCredentials(String user, String password, String email){

        validateUser = validateCredentialsUser(user) ;
        validatePassword = validateCredentialsPassword(password);
        validateEmail = validateCredentialsEmail(email);

        if (validateUser== Error.OK) {
            if (validatePassword== Error.OK) {
                if (validateEmail == Error.OK){
                // Guardamos en las preferencias
                savePreferences(user,password,email);
                view.startActivity();
                }else{
                    String nameResource= ErrorMapUtils.getErrorMapResource(context).get(String.valueOf(validateEmail));
                    view.setMessageError(nameResource, R.id.crtUser_til_email);
                }
            } else{
                //                                  coge el mapa del contexto          coge el valor de la key
                String nameResource= ErrorMapUtils.getErrorMapResource(context).get(String.valueOf(validatePassword));
                view.setMessageError(nameResource, R.id.crtUser_til_email);
            }
        }else {
            //                                  coge el mapa del contexto          coge el valor de la key
            String nameResource= ErrorMapUtils.getErrorMapResource(context).get(String.valueOf(validateUser));
            view.setMessageError(nameResource, R.id.crtUser_til_email);
        }
    }

    private void savePreferences(String user,String password,String email) {
        AccountPreferenceImpl accountPreference = (AccountPreferenceImpl) AccountPreferenceImpl.getInstance(context);
        accountPreference.putUser(user);
        accountPreference.putPassword(password);
        accountPreference.putEmail(email);
    }


    @Override
    public int validateCredentialsUser(String user) {
        boolean validate = true;
        String msgError = "";
        int idError = Error.OK;
        if (TextUtils.isEmpty(user)) {
            idError = Error.DATA_EMPTY;
        }
        return idError;
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

    @Override
    public int validateCredentialsEmail(String email) {
        int idError = Error.OK;
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                idError = Error.EMAIL_INVALID;

                return idError;
    }
}
