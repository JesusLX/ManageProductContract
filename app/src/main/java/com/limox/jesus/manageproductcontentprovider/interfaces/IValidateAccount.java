package com.limox.jesus.manageproductcontentprovider.interfaces;

/**
 * Created by jesus on 20/10/16.
 */

public interface IValidateAccount {


    interface View{
        public void setMessageError(String messageError, int idView);
        void startActivity();
    }

    interface Presenter{
        public int validateCredentialsUser(String user);
        public int validateCredentialsPassword(String password);


      /*  public static int validateCredentialsUser(String user) {
            boolean validate = true;
            String msgError = "";
            int idError = 0;
            if (TextUtils.isEmpty(user)) {
                return Error.DATA_EMPTY;
            }
            return Error.OK;
        }


        public static int validateCredentialsPassword(String password) {
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
        }*/

    }

}
