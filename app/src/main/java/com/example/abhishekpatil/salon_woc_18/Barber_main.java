package com.example.abhishekpatil.salon_woc_18;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

public class Barber_main extends Fragment {

    private DatabaseReference myref;
    private DatabaseReference timeref;
    private String phonenumber;
    private Button logout;
    private TextView name1;private TextView service1;
    private TextView name2;private TextView service2;
    private TextView name3;private TextView service3;
    private TextView name4;private TextView service4;
    private TextView name5;private TextView service5;
    private TextView name6;private TextView service6;
    private TextView name7;private TextView service7;
    private TextView name8;private TextView service8;
    private TextView name9;private TextView service9;
    private TextView name10;private TextView service10;
    private TextView name11;private TextView service11;
    private TextView name12;private TextView service12;
    private TextView name13;private TextView service13;
    private Button migrate;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_barber_main,container,false);

        name1 = (TextView)view.findViewById(R.id.today_status_time1);service1 = (TextView)view.findViewById(R.id.today_service_time1);
        name2 = (TextView)view.findViewById(R.id.today_status_time2);service2 = (TextView)view.findViewById(R.id.today_service_time2);
        name3 = (TextView)view.findViewById(R.id.today_status_time3);service3 = (TextView)view.findViewById(R.id.today_service_time3);
        name4 = (TextView)view.findViewById(R.id.today_status_time4);service4 = (TextView)view.findViewById(R.id.today_service_time4);
        name5 = (TextView)view.findViewById(R.id.today_status_time5);service5 = (TextView)view.findViewById(R.id.today_service_time5);
        name6 = (TextView)view.findViewById(R.id.today_status_time6);service6 = (TextView)view.findViewById(R.id.today_service_time6);
        name7 = (TextView)view.findViewById(R.id.today_status_time7);service7 = (TextView)view.findViewById(R.id.today_service_time7);
        name8 = (TextView)view.findViewById(R.id.today_status_time8);service8 = (TextView)view.findViewById(R.id.today_service_time8);
        name9 = (TextView)view.findViewById(R.id.today_status_time9);service9 = (TextView)view.findViewById(R.id.today_service_time9);
        name10 = (TextView)view.findViewById(R.id.today_status_time10);service10 = (TextView)view.findViewById(R.id.today_service_time10);
        name11 = (TextView)view.findViewById(R.id.today_status_time11);service11 = (TextView)view.findViewById(R.id.today_service_time11);
        name12 = (TextView)view.findViewById(R.id.today_status_time12);service12 = (TextView)view.findViewById(R.id.today_service_time12);
        name13 = (TextView)view.findViewById(R.id.today_status_time13);service13 = (TextView)view.findViewById(R.id.today_service_time13);

        logout = (Button)view.findViewById(R.id.btn_logout_barber);
        phonenumber = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();

        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String date = day+month+year;


        myref = FirebaseDatabase.getInstance().getReference().child("barber").child(phonenumber).child(date);
        migrate = (Button)view.findViewById(R.id.btn_tomorrow);
        timeref = FirebaseDatabase.getInstance().getReference().child("barber").child(phonenumber);


        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.sign_in, true)
                        .build();
                Navigation.findNavController(getView()).navigate(R.id.action_barber_main_to_sign_in,null,navOptions);
            }
        });


        migrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.action_barber_main_to_tomorrow);
            }
        });
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE,1);
        String year2 = String.valueOf(calendar1.get(Calendar.YEAR));
        String month2 = String.valueOf(calendar1.get(Calendar.MONTH));
        String day2 = String.valueOf(calendar1.get(Calendar.DAY_OF_MONTH));
        final String tomorrow = day2+month2+year2;

        timeref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(tomorrow).exists()){

                }
                else{
                    for(int i=1;i<=13;i++){
                        String s = String.valueOf(i);
                        s = "time"+s;
                        timeref.child(tomorrow).child(s).child("name").setValue("----");
                        timeref.child(tomorrow).child(s).child("service").setValue("----");
                        timeref.child(tomorrow).child(s).child("status").setValue("1");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("time1").child("status").getValue().toString().equals("1")){

                    name1.setText(dataSnapshot.child("time1").child("name").getValue().toString());
                    service1.setText(dataSnapshot.child("time1").child("service").getValue().toString());
                }

                if(dataSnapshot.child("time2").child("status").getValue().toString().equals("1")){

                    name2.setText(dataSnapshot.child("time2").child("name").getValue().toString());
                    service2.setText(dataSnapshot.child("time2").child("service").getValue().toString());
                }

                if(dataSnapshot.child("time3").child("status").getValue().toString().equals("1")){

                    name3.setText(dataSnapshot.child("time3").child("name").getValue().toString());
                    service3.setText(dataSnapshot.child("time3").child("service").getValue().toString());
                }

                if(dataSnapshot.child("time4").child("status").getValue().toString().equals("1")){

                    name4.setText(dataSnapshot.child("time4").child("name").getValue().toString());
                    service4.setText(dataSnapshot.child("time4").child("service").getValue().toString());
                }

                if(dataSnapshot.child("time5").child("status").getValue().toString().equals("1")){

                    name5.setText(dataSnapshot.child("time5").child("name").getValue().toString());
                    service5.setText(dataSnapshot.child("time5").child("service").getValue().toString());
                }

                if(dataSnapshot.child("time6").child("status").getValue().toString().equals("1")){

                    name6.setText(dataSnapshot.child("time6").child("name").getValue().toString());
                    service6.setText(dataSnapshot.child("time6").child("service").getValue().toString());
                }

                if(dataSnapshot.child("time7").child("status").getValue().toString().equals("1")){

                    name7.setText(dataSnapshot.child("time7").child("name").getValue().toString());
                    service7.setText(dataSnapshot.child("time7").child("service").getValue().toString());
                }

                if(dataSnapshot.child("time8").child("status").getValue().toString().equals("1")){

                    name8.setText(dataSnapshot.child("time8").child("name").getValue().toString());
                    service8.setText(dataSnapshot.child("time8").child("service").getValue().toString());
                }

                if(dataSnapshot.child("time9").child("status").getValue().toString().equals("1")){

                    name9.setText(dataSnapshot.child("time9").child("name").getValue().toString());
                    service9.setText(dataSnapshot.child("time9").child("service").getValue().toString());
                }

                if(dataSnapshot.child("time10").child("status").getValue().toString().equals("1")){

                    name10.setText(dataSnapshot.child("time10").child("name").getValue().toString());
                    service10.setText(dataSnapshot.child("time10").child("service").getValue().toString());
                }

                if(dataSnapshot.child("time11").child("status").getValue().toString().equals("1")){

                    name11.setText(dataSnapshot.child("time11").child("name").getValue().toString());
                    service11.setText(dataSnapshot.child("time11").child("service").getValue().toString());
                }

                if(dataSnapshot.child("time12").child("status").getValue().toString().equals("1")){

                    name12.setText(dataSnapshot.child("time12").child("name").getValue().toString());
                    service12.setText(dataSnapshot.child("time12").child("service").getValue().toString());
                }

                if(dataSnapshot.child("time13").child("status").getValue().toString().equals("1")){

                    name13.setText(dataSnapshot.child("time13").child("name").getValue().toString());
                    service13.setText(dataSnapshot.child("time13").child("service").getValue().toString());
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}
