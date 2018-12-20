package com.example.abhishekpatil.salon_woc_18;

public class ListItem {
    private String name;
    private String address;
    private String phonenumber;

    public ListItem(String name, String address, String phonenumber) {
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    public ListItem(){

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
