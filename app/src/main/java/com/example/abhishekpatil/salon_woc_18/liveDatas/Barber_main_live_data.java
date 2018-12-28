package com.example.abhishekpatil.salon_woc_18.liveDatas;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Barber_main_live_data extends LiveData<DataSnapshot> {
    private final Query query;
    private  final  MyValueEventListener listener = new MyValueEventListener();

    public Barber_main_live_data(Query query){
        this.query = query;
    }

    public  Barber_main_live_data (DatabaseReference ref){
        this.query = ref;
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
