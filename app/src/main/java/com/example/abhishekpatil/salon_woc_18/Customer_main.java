package com.example.abhishekpatil.salon_woc_18;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Customer_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment_main1 f1 = new Fragment_main1();

        String[] s = new String[4];
        s[0] = "gourav";
        s[1] = "good";
        s[2] = "closed";
        s[3] = String.valueOf(R.drawable.ic_launcher_background);

        // here I will take data from firebase and send it through bundle
        Bundle bundle = new Bundle();
        bundle.putStringArray("info",s);
        f1.setArguments(bundle);

        ft.add(R.id.frame,f1);
        ft.commit();

    }
}
