package com.example.abhishekpatil.salon_woc_18;

import android.net.Uri;

public class City {
    public static  String city;
    public static String phonenumber;
    public static String name;
    public static String address;
    public static Uri uri;

    public static Uri getUri() {
        return uri;
    }

    public static void setUri(Uri uri) {
        City.uri = uri;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        City.address = address;
    }

    public static String barberphonenumber;

    public static String getBarberphonenumber() {
        return barberphonenumber;
    }

    public static void setBarberphonenumber(String barberphonenumber) {
        City.barberphonenumber = barberphonenumber;
    }

    public static String getPhonenumber() {
        return phonenumber;
    }

    public static void setPhonenumber(String phonenumber) {
        City.phonenumber = phonenumber;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        City.name = name;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        City.city = city;
    }
}
