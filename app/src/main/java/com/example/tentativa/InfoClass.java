package com.example.tentativa;

import android.net.Uri;

import java.util.List;

public class InfoClass {

    static String ACCOUNT_NAME = "VoidInfo";
    static String ACCOUNT_EMAIL = "VoidInfo";
    static String ACCOUNT_ID = "VoidInfo";
    static Uri ACCOUNT_PHOTO = null;
    static boolean SEND = false;

    static String title = "VoidInfo";
    static String boby = "VoidInfo";

    public static List<TextStructure> LISTA;

    public static String getAccountName() {
        return ACCOUNT_NAME;
    }

    public static void setAccountName(String accountName) {
        ACCOUNT_NAME = accountName;
    }

    public static String getAccountEmail() {
        return ACCOUNT_EMAIL;
    }

    public static void setAccountEmail(String accountEmail) {
        ACCOUNT_EMAIL = accountEmail;
    }

    public static String getAccountId() {
        return ACCOUNT_ID;
    }

    public static void setAccountId(String accountId) {
        ACCOUNT_ID = accountId;
    }

    public static Uri getAccountPhoto() {
        return ACCOUNT_PHOTO;
    }

    public static void setAccountPhoto(Uri accountPhoto) {
        ACCOUNT_PHOTO = accountPhoto;
    }




}
