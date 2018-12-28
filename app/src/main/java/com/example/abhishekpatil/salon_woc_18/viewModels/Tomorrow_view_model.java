package com.example.abhishekpatil.salon_woc_18.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.example.abhishekpatil.salon_woc_18.liveDatas.Tomorrow_live_data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Tomorrow_view_model extends ViewModel {


    private final DatabaseReference timeref = FirebaseDatabase.getInstance().getReference().child("barber")
            .child(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().substring(1));

    public LiveData<DataSnapshot> getDataSnapshotLiveDatatomorrow(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String date = day + month + year;

        DatabaseReference tomorrowref = timeref.child(date);

        Tomorrow_live_data liveData = new Tomorrow_live_data(tomorrowref);
        return  liveData;

    }
}
