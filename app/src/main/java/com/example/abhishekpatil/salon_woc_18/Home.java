package com.example.abhishekpatil.salon_woc_18;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

public class Home extends Fragment {


    private FirebaseDatabase database;
    private DatabaseReference customerref;
    private DatabaseReference barberref;
    private FirebaseUser user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        customerref = database.getReference().child("customer");
        barberref = database.getReference().child("barber");

        if(user!= null){
            final String phonenumber = user.getPhoneNumber().substring(1);
            Toast.makeText(getContext(),phonenumber,Toast.LENGTH_LONG).show();

            customerref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(phonenumber).exists()){
                        NavOptions navOptions = new NavOptions.Builder()
                                .setPopUpTo(R.id.home, true)
                                .build();

                      String city= dataSnapshot.child(phonenumber).child("city").getValue().toString();
                      HomeDirections.ActionHomeToCustomerMain action = HomeDirections.actionHomeToCustomerMain();
                      action.setPhonenumber(phonenumber);
                      action.setCity(city);


                        Navigation.findNavController(getView()).navigate(action,navOptions);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            barberref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(phonenumber).exists()){
                        NavOptions navOptions = new NavOptions.Builder()
                                .setPopUpTo(R.id.home, true)
                                .build();
                        Navigation.findNavController(getView()).navigate(R.id.action_home_to_barber_main,null, navOptions);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        else
        {
            NavOptions navOptions = new NavOptions.Builder()
                    .setPopUpTo(R.id.home, true)
                    .build();
            Navigation.findNavController(getView()).navigate(R.id.action_home_to_sign_up,null,navOptions);
        }
    }
}
