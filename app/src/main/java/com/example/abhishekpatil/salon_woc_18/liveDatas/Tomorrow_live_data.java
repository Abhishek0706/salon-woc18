package com.example.abhishekpatil.salon_woc_18.liveDatas;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Tomorrow_live_data extends LiveData<DataSnapshot> {
    private final Query query;
    private  final MyValueEventListener listener = new MyValueEventListener();

    public Tomorrow_live_data(Query query){
        this.query = query;
    }

    public  Tomorrow_live_data (DatabaseReference ref){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String date = day + month + year;

        this.query = ref.child(date);
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

