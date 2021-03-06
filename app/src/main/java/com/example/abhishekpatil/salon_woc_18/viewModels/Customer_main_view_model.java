package com.example.abhishekpatil.salon_woc_18.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import com.example.abhishekpatil.salon_woc_18.Customer_main;
import com.example.abhishekpatil.salon_woc_18.ListItem;
import com.example.abhishekpatil.salon_woc_18.liveDatas.Customer_main_live_data;
import com.example.abhishekpatil.salon_woc_18.liveDatas.Tomorrow_live_data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Customer_main_view_model extends ViewModel {
    private final DatabaseReference barberref = FirebaseDatabase.getInstance().getReference().child("barber");
    private Customer_main_live_data live_data = new Customer_main_live_data(barberref);


    public LiveData<DataSnapshot> getDataSnapshotLiveDatabarberlist() {

        return live_data;
    }
}
