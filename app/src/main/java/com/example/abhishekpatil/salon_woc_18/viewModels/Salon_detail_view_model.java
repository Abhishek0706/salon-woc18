package com.example.abhishekpatil.salon_woc_18.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.example.abhishekpatil.salon_woc_18.Salon_detail;
import com.example.abhishekpatil.salon_woc_18.liveDatas.Salon_detail_live_data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;

import java.util.List;

public class Salon_detail_view_model extends ViewModel {

    Salon_detail_live_data livedatabarber;
    Salon_detail_live_data livedataservices;
    private MutableLiveData<DataSnapshot> dataSnapshotMutableLiveData;



    public LiveData<DataSnapshot> getLivedatabarber(String phonenumber) {
        DatabaseReference barberref = FirebaseDatabase.getInstance().getReference().child("barber")
                .child(phonenumber);
        this.livedatabarber = new Salon_detail_live_data(barberref);
        return livedatabarber;
    }

    public LiveData<DataSnapshot> getLivedataservices(String phonenumber) {
        DatabaseReference serviceref = FirebaseDatabase.getInstance().getReference().child("barber")
                .child(phonenumber).child("services");
        this.livedataservices = new Salon_detail_live_data(serviceref);

        return livedataservices;
    }
}
