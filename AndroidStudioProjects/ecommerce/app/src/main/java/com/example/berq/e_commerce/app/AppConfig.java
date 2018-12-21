package com.example.berq.e_commerce.app;

public class AppConfig {
    // Server user login url
    public static String URL_LOGIN = "http://192.168.1.25/android_login_api/login.php";

    // Server user register url
    public static String URL_REGISTER = "http://192.168.1.25/android_login_api/register.php";

    // Server return json type product url
    public static String URL_JSONPRODUCT = "http://192.168.1.25/android_login_api/jsonProduct.php";

    public static String URL_Chosen_cat_Product = "http://192.168.1.25/android_login_api/Chosen_cat_Product.php";

    public static String URL_getProductforcart = "http://192.168.1.25/android_login_api/getProductforcart.php";

    public static String URL_POSTorders = "http://192.168.1.25/android_login_api/order.php";


    // Phone categories number for mysql to volley
    public static int CAT_IPHONE = 0;

    public static int CAT_SAMSUNG = 1;

    public static int CAT_HUAWEI = 2;

    public static int CAT_LG = 3;

    public static int CAT_ASUS = 4;

    public static int CAT_GENERAL = 5;

    public static int CAT_HONOR = 6;

    public static int CAT_VESTEL = 7;

    public static int CAT_XIAOMI = 8;


}
