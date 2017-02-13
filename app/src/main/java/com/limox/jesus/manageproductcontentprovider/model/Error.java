package com.limox.jesus.manageproductcontentprovider.model;

/**
 * Created by usuario on 14/11/16.
 */

public class Error {
    public static final int OK=0;
    public static final int PASSWORD_DIGIT=10;
    public static final int PASSWORD_CASE=11;
    public static final int PASSWORD_LENGTH=12;
    public static final int DATA_EMPTY = 13;
    public static final int EMAIL_INVALID = 14;
    public static int code;
    public static String message;
    // Inicializar valores static
    static {

    }
}
