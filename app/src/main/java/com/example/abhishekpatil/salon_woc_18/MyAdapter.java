package com.example.abhishekpatil.salon_woc_18;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abhishekpatil.salon_woc_18.viewModels.Salon_detail_view_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.List;

import androidx.navigation.Navigation;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ListItem listItem = listItems.get(i);
        viewHolder.text_name.setText(listItem.getName());
        viewHolder.text_address.setText(listItem.getAddress());
        viewHolder.text_phonenumber.setText(listItem.getPhonenumber());

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // HERE I WILL NAVIGATE

                DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child("barber")
                        .child(listItem.getPhonenumber());
                myref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.DATE, 1);
                        String year = String.valueOf(calendar.get(Calendar.YEAR));
                        String month = String.valueOf(calendar.get(Calendar.MONTH));
                        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                        String date = day + month + year;
                        if(dataSnapshot.child(date).exists()){
                            Customer_mainDirections.ActionCustomerMainToSalonDetail action = Customer_mainDirections.actionCustomerMainToSalonDetail();
                            action.setName(listItem.getName());
                            action.setAddress(listItem.getAddress());
                            action.setPhonenumber(listItem.getPhonenumber());
                            City.setBarberphonenumber(listItem.getPhonenumber());
                            Navigation.findNavController(v).navigate(action);
                        }
                        else{
                            Toast.makeText(context,"NOT AVAILABLE",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text_name;
        private TextView text_address;
        private TextView text_phonenumber;
        private ImageView img;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_name = (TextView) itemView.findViewById(R.id.nameOfSalon);
            text_address = (TextView) itemView.findViewById(R.id.addressOfSalon);
            text_phonenumber = (TextView) itemView.findViewById(R.id.phonenumber_of_Salon);
            img = (ImageView) itemView.findViewById(R.id.image);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.layoutOfSalon);


        }
    }
}
