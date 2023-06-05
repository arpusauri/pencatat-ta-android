package com.arya.pencatatta;

public class Config {
    // URLs
    public static final String IP              = "http://192.168.137.1";
    public static final String DATA            = "/android_ta/data";
    public static final String ADMIN           = "/android_ta/admin";
    public static final String URL_ADD_DATA    = IP+DATA+"/AddData.php";
    public static final String URL_SHOW_DATA   = IP+DATA+"/ShowData.php";
    public static final String URL_GET_DATA    = IP+DATA+"/FetchData.php?id=";
    public static final String URL_UPDATE_DATA = IP+DATA+"/UpdateData.php";
    public static final String URL_DELETE_DATA = IP+DATA+"/DeleteData.php?id=";
    public static final String URL_LOGIN       = IP+ADMIN+"/LoginAdmin.php";
    public static final String URL_REGISTER    = "http://192.168.137.1/android_ta/admin/RegisterAdmin.php";

    // PHP Keys
    public static final String KEY_ID          = "id";
    public static final String KEY_NOINDUK     = "no_induk";
    public static final String KEY_JUDUL       = "judul";
    public static final String KEY_PEMILIK     = "pemilik";
    public static final String KEY_PEMBIMBING  = "pembimbing";
    public static final String KEY_TEMPAT      = "tempat_pkl";
    public static final String KEY_TAHUN       = "tahun";

    // JSON Tags
    public static final String TAG_JSON_ARRAY  = "result";
    public static final String TAG_ID          = "id";
    public static final String TAG_NOINDUK     = "no_induk";
    public static final String TAG_JUDUL       = "judul";
    public static final String TAG_PEMILIK     = "pemilik";
    public static final String TAG_PEMBIMBING  = "pembimbing";
    public static final String TAG_TEMPAT      = "tempat_pkl";
    public static final String TAG_TAHUN       = "tahun";
}
