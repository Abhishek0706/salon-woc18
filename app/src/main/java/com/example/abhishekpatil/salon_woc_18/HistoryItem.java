package com.example.abhishekpatil.salon_woc_18;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HistoryItem {
    private DatabaseReference myref;
    private String date;
    private String time;
    private String name;
    private String address;
    private String phonenumber;

    public HistoryItem(String date, String time, String phonenumber) {

        this.date = date;
        this.time = time;
        this.phonenumber = phonenumber;
        this.name = "no name";
        this.address = "no address";

        myref = FirebaseDatabase.getInstance().getReference().child("barber").child(phonenumber);
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = dataSnapshot.child("name").getValue().toString();
                address = dataSnapshot.child("address").getValue().toString();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private HistoryItem(){

    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
}
