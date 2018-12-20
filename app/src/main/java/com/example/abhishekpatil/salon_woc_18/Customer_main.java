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
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;


public class Customer_main extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    private DatabaseReference myref;
    private DatabaseReference customerref;
    private FirebaseUser muser;
    private String phonenumber;
    private static String city;
    private Button logout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_main,container,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myref = FirebaseDatabase.getInstance().getReference().child("barber");
        customerref = FirebaseDatabase.getInstance().getReference().child("customer");
        muser = FirebaseAuth.getInstance().getCurrentUser();
        logout = (Button)view.findViewById(R.id.btn_logout);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


        phonenumber = muser.getPhoneNumber();
        customerref.child(phonenumber).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                city = dataSnapshot.child("city").getValue().toString();
                Toast.makeText(getContext(),city,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.sign_in, true)
                        .build();
                Navigation.findNavController(getView()).navigate(R.id.action_customer_main_to_sign_in,null,navOptions);
            }
        });



        listItems = new ArrayList<ListItem>();

        Query query = myref.orderByChild("city").equalTo(city);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){


                    listItems.add(snapshot.getValue(ListItem.class));
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        adapter = new MyAdapter(listItems,getContext());

        recyclerView.setAdapter(adapter);
        }
}
