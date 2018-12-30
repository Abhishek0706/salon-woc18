package com.example.abhishekpatil.salon_woc_18.liveDatas;

import android.arch.lifecycle.LiveData;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.example.abhishekpatil.salon_woc_18.Cities;
import com.example.abhishekpatil.salon_woc_18.City;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Customer_main_live_data extends LiveData<DataSnapshot> {
    private final Query query;
    private  final  MyValueEventListener listener = new MyValueEventListener();


    public Customer_main_live_data(Query query){
        this.query = query;
    }

    public  Customer_main_live_data (DatabaseReference ref){
        String mycity = City.getCity();
        this.query = ref.orderByChild("city").equalTo(mycity);
    }

    @Override
    protected void onActive() {
        query.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        query.removeEventListener(listener);
    }

    public class MyValueEventListener implements ValueEventListener {

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            setValue(dataSnapshot);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }
}
