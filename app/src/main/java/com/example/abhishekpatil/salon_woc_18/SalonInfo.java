package com.example.abhishekpatil.salon_woc_18;

import android.widget.ImageView;

public class SalonInfo {

    private  String name;
    private String detail;
    private String status;
    private int image;

    public SalonInfo(String name, String detail, String status, int image) {
        this.name = name;
        this.detail = detail;
        this.status = status;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public String getStatus() {
        return status;
    }

    public int getImage() {
        return image;
    }
}
