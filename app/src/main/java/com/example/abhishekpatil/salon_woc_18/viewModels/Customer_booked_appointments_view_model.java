package com.example.abhishekpatil.salon_woc_18.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.abhishekpatil.salon_woc_18.liveDatas.Customer_booked_appointments_live_data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Customer_booked_appointments_view_model  extends ViewModel {
    public final DatabaseReference historyref = FirebaseDatabase.getInstance().getReference()
            .child("customer").child(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().substring(1)).child("history");

    public  final Query query = historyref.orderByChild("date");
    private final Customer_booked_appointments_live_data live_data = new Customer_booked_appointments_live_data(query);

    public LiveData<DataSnapshot> getDataSnapshotLiveDatahistory(){
        return live_data;
    }
}
