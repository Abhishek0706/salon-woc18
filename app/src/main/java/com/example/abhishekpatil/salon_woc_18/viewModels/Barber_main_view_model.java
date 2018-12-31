package com.example.abhishekpatil.salon_woc_18.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.abhishekpatil.salon_woc_18.liveDatas.Barber_main_live_data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Barber_main_view_model extends ViewModel {

    private final DatabaseReference timeref = FirebaseDatabase.getInstance().getReference().child("barber")
            .child(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().substring(1));
    public final Barber_main_live_data livedata_barber = new Barber_main_live_data(timeref);

    public LiveData<DataSnapshot> getDataSnapshotLiveDatabarber(){
        return livedata_barber;
    }

}
