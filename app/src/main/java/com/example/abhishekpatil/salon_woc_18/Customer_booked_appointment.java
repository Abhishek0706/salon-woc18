package com.example.abhishekpatil.salon_woc_18;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Customer_booked_appointment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<HistoryItem> historyItems;
    private String phonenumber;
    private DatabaseReference historyref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_booked_appointment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.history_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Customer_booked_appointmentArgs args = Customer_booked_appointmentArgs.fromBundle(getArguments());
        phonenumber = args.getPhonenumber().toString();


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        historyItems = new ArrayList<HistoryItem>();
        historyref = FirebaseDatabase.getInstance().getReference().child("customer").child(phonenumber).child("history");


        Query query = historyref.orderByChild("date").equalTo("23112018");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    historyItems.add(snapshot.getValue(HistoryItem.class));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        adapter = new MyAdapterHistory(historyItems, getContext());

        recyclerView.setAdapter(adapter);

    }
}
