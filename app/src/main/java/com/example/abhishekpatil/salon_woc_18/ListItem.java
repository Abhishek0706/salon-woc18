package com.example.abhishekpatil.salon_woc_18;

import android.net.Uri;

public class ListItem {
    private String name;
    private String address;
    private String phonenumber;
    private Uri uri;

    public ListItem(String name, String address, String phonenumber ,Uri uri) {
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;

    }

    public ListItem() {

    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }


}
