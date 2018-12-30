package com.example.abhishekpatil.salon_woc_18;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.abhishekpatil.salon_woc_18.viewModels.Customer_booked_appointments_view_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;
import java.util.List;


public class Customer_booked_appointment extends android.support.v4.app.Fragment {
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
        phonenumber = args.getPhonenumber();


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        historyItems = new ArrayList<HistoryItem>();

        Customer_booked_appointments_view_model view_model = ViewModelProviders.of(this).get(Customer_booked_appointments_view_model.class);
        LiveData<DataSnapshot> liveData = view_model.getDataSnapshotLiveDatahistory();

        liveData.observe(this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(@Nullable DataSnapshot dataSnapshot) {
                historyItems.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    historyItems.add(snapshot.getValue(HistoryItem.class));
                    adapter.notifyDataSetChanged();
                }

                if (historyItems.size() == 0) {
                    historyItems.clear();
                    Toast.makeText(getContext(), "NO BOOKING YET", Toast.LENGTH_LONG).show();
                }

            }
        });


        adapter = new MyAdapterHistory(historyItems, getContext());

        recyclerView.setAdapter(adapter);

    }
}
