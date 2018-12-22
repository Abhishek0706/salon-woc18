package com.example.abhishekpatil.salon_woc_18;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

public class Tomorrow extends Fragment {
    private DatabaseReference myref;

    private String phonenumber;
    private Switch switch1;
    private TextView name1;
    private TextView service1;
    private Switch switch2;
    private TextView name2;
    private TextView service2;
    private Switch switch3;
    private TextView name3;
    private TextView service3;
    private Switch switch4;
    private TextView name4;
    private TextView service4;
    private Switch switch5;
    private TextView name5;
    private TextView service5;
    private Switch switch6;
    private TextView name6;
    private TextView service6;
    private Switch switch7;
    private TextView name7;
    private TextView service7;
    private Switch switch8;
    private TextView name8;
    private TextView service8;
    private Switch switch9;
    private TextView name9;
    private TextView service9;
    private Switch switch10;
    private TextView name10;
    private TextView service10;
    private Switch switch11;
    private TextView name11;
    private TextView service11;
    private Switch switch12;
    private TextView name12;
    private TextView service12;
    private Switch switch13;
    private TextView name13;
    private TextView service13;
//    private Button logout;
    private Button migrate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tomorrow, container, false);
        phonenumber = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().substring(1);

//        logout = (Button) view.findViewById(R.id.btn_logout_barber2);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

        String date = day + month + year;


        myref = FirebaseDatabase.getInstance().getReference().child("barber").child(phonenumber).child(date);

        switch1 = (Switch) view.findViewById(R.id.tomorrow_switch1);
        name1 = (TextView) view.findViewById(R.id.tomorrow_status_time1);
        service1 = (TextView) view.findViewById(R.id.tomorrow_service_time1);
        switch2 = (Switch) view.findViewById(R.id.tomorrow_switch2);
        name2 = (TextView) view.findViewById(R.id.tomorrow_status_time2);
        service2 = (TextView) view.findViewById(R.id.tomorrow_service_time2);
        switch3 = (Switch) view.findViewById(R.id.tomorrow_switch3);
        name3 = (TextView) view.findViewById(R.id.tomorrow_status_time3);
        service3 = (TextView) view.findViewById(R.id.tomorrow_service_time3);
        switch4 = (Switch) view.findViewById(R.id.tomorrow_switch4);
        name4 = (TextView) view.findViewById(R.id.tomorrow_status_time4);
        service4 = (TextView) view.findViewById(R.id.tomorrow_service_time4);
        switch5 = (Switch) view.findViewById(R.id.tomorrow_switch5);
        name5 = (TextView) view.findViewById(R.id.tomorrow_status_time5);
        service5 = (TextView) view.findViewById(R.id.tomorrow_service_time5);
        switch6 = (Switch) view.findViewById(R.id.tomorrow_switch6);
        name6 = (TextView) view.findViewById(R.id.tomorrow_status_time6);
        service6 = (TextView) view.findViewById(R.id.tomorrow_service_time6);
        switch7 = (Switch) view.findViewById(R.id.tomorrow_switch7);
        name7 = (TextView) view.findViewById(R.id.tomorrow_status_time7);
        service7 = (TextView) view.findViewById(R.id.tomorrow_service_time7);
        switch8 = (Switch) view.findViewById(R.id.tomorrow_switch8);
        name8 = (TextView) view.findViewById(R.id.tomorrow_status_time8);
        service8 = (TextView) view.findViewById(R.id.tomorrow_service_time8);
        switch9 = (Switch) view.findViewById(R.id.tomorrow_switch9);
        name9 = (TextView) view.findViewById(R.id.tomorrow_status_time9);
        service9 = (TextView) view.findViewById(R.id.tomorrow_service_time9);
        switch10 = (Switch) view.findViewById(R.id.tomorrow_switch10);
        name10 = (TextView) view.findViewById(R.id.tomorrow_status_time10);
        service10 = (TextView) view.findViewById(R.id.tomorrow_service_time10);
        switch11 = (Switch) view.findViewById(R.id.tomorrow_switch11);
        name11 = (TextView) view.findViewById(R.id.tomorrow_status_time11);
        service11 = (TextView) view.findViewById(R.id.tomorrow_service_time11);
        switch12 = (Switch) view.findViewById(R.id.tomorrow_switch12);
        name12 = (TextView) view.findViewById(R.id.tomorrow_status_time12);
        service12 = (TextView) view.findViewById(R.id.tomorrow_service_time12);
        switch13 = (Switch) view.findViewById(R.id.tomorrow_switch13);
        name13 = (TextView) view.findViewById(R.id.tomorrow_status_time13);
        service13 = (TextView) view.findViewById(R.id.tomorrow_service_time13);
        migrate = (Button) view.findViewById(R.id.btn_today);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                NavOptions navOptions = new NavOptions.Builder()
//                        .setPopUpTo(R.id.sign_in, true)
//                        .build();
//                Navigation.findNavController(getView()).navigate(R.id.action_tomorrow_to_sign_in, null, navOptions);
//            }
//        });
        migrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.action_tomorrow_to_barber_main);
            }
        });

        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("time1").child("status").getValue().toString().equals("2")) {
                    switch1.setChecked(true);
                    name1.setText(dataSnapshot.child("time1").child("name").getValue().toString());
                    service1.setText(dataSnapshot.child("time1").child("service").getValue().toString());
                } else if(dataSnapshot.child("time1").child("status").getValue().toString().equals("1")){
                    switch1.setChecked(true);
                    name1.setText("Available");
                    service1.setText(" ");
                }
                else{
                    switch1.setChecked(false);
                    name1.setText("Closed");
                }
                if (dataSnapshot.child("time2").child("status").getValue().toString().equals("2")) {
                    switch2.setChecked(true);
                    name2.setText(dataSnapshot.child("time2").child("name").getValue().toString());
                    service2.setText(dataSnapshot.child("time2").child("service").getValue().toString());
                } else if(dataSnapshot.child("time2").child("status").getValue().toString().equals("1")){
                    switch2.setChecked(true);
                    name2.setText("Available");
                    service2.setText(" ");
                }
                else{
                    switch2.setChecked(false);
                    name2.setText("Closed");
                }
                if (dataSnapshot.child("time3").child("status").getValue().toString().equals("2")) {
                    switch3.setChecked(true);
                    name3.setText(dataSnapshot.child("time3").child("name").getValue().toString());
                    service3.setText(dataSnapshot.child("time3").child("service").getValue().toString());
                }  else if(dataSnapshot.child("time3").child("status").getValue().toString().equals("1")){
                    switch3.setChecked(true);
                    name3.setText("Available");
                    service3.setText(" ");
                }
                else{
                    switch3.setChecked(false);
                    name3.setText("Closed");
                }
                if (dataSnapshot.child("time4").child("status").getValue().toString().equals("2")) {
                    switch4.setChecked(true);
                    name4.setText(dataSnapshot.child("time4").child("name").getValue().toString());
                    service4.setText(dataSnapshot.child("time4").child("service").getValue().toString());
                } else if(dataSnapshot.child("time4").child("status").getValue().toString().equals("1")){
                    switch4.setChecked(true);
                    name4.setText("Available");
                    service4.setText(" ");
                }
                else{
                    switch4.setChecked(false);
                    name4.setText("Closed");
                }
                if (dataSnapshot.child("time5").child("status").getValue().toString().equals("2")) {
                    switch5.setChecked(true);
                    name5.setText(dataSnapshot.child("time5").child("name").getValue().toString());
                    service5.setText(dataSnapshot.child("time5").child("service").getValue().toString());
                }  else if(dataSnapshot.child("time5").child("status").getValue().toString().equals("1")){
                    switch5.setChecked(true);
                    name5.setText("Available");
                    service5.setText(" ");
                }
                else{
                    switch5.setChecked(false);
                    name5.setText("Closed");
                }
                if (dataSnapshot.child("time6").child("status").getValue().toString().equals("2")) {
                    switch6.setChecked(true);
                    name6.setText(dataSnapshot.child("time6").child("name").getValue().toString());
                    service6.setText(dataSnapshot.child("time6").child("service").getValue().toString());
                } else if(dataSnapshot.child("time6").child("status").getValue().toString().equals("1")){
                    switch6.setChecked(true);
                    name6.setText("Available");
                    service6.setText(" ");
                }
                else{
                    switch6.setChecked(false);
                    name6.setText("Closed");
                }
                if (dataSnapshot.child("time7").child("status").getValue().toString().equals("2")) {
                    switch7.setChecked(true);
                    name7.setText(dataSnapshot.child("time7").child("name").getValue().toString());
                    service7.setText(dataSnapshot.child("time7").child("service").getValue().toString());
                } else if(dataSnapshot.child("time7").child("status").getValue().toString().equals("1")){
                    switch7.setChecked(true);
                    name7.setText("Available");
                    service7.setText(" ");
                }
                else{
                    switch7.setChecked(false);
                    name7.setText("Closed");
                }
                if (dataSnapshot.child("time8").child("status").getValue().toString().equals("2")) {
                    switch8.setChecked(true);
                    name8.setText(dataSnapshot.child("time8").child("name").getValue().toString());
                    service8.setText(dataSnapshot.child("time8").child("service").getValue().toString());
                } else if(dataSnapshot.child("time8").child("status").getValue().toString().equals("1")){
                    switch8.setChecked(true);
                    name8.setText("Available");
                    service8.setText(" ");
                }
                else{
                    switch8.setChecked(false);
                    name8.setText("Closed");
                }
                if (dataSnapshot.child("time9").child("status").getValue().toString().equals("2")) {
                    switch9.setChecked(true);
                    name9.setText(dataSnapshot.child("time9").child("name").getValue().toString());
                    service9.setText(dataSnapshot.child("time9").child("service").getValue().toString());
                } else if(dataSnapshot.child("time9").child("status").getValue().toString().equals("1")){
                    switch9.setChecked(true);
                    name9.setText("Available");
                    service9.setText(" ");
                }
                else{
                    switch9.setChecked(false);
                    name9.setText("Closed");
                }
                if (dataSnapshot.child("time10").child("status").getValue().toString().equals("2")) {
                    switch10.setChecked(true);
                    name10.setText(dataSnapshot.child("time10").child("name").getValue().toString());
                    service10.setText(dataSnapshot.child("time10").child("service").getValue().toString());
                } else if(dataSnapshot.child("time10").child("status").getValue().toString().equals("1")){
                    switch10.setChecked(true);
                    name10.setText("Available");
                    service10.setText(" ");
                }
                else{
                    switch10.setChecked(false);
                    name10.setText("Closed");
                }
                if (dataSnapshot.child("time11").child("status").getValue().toString().equals("2")) {
                    switch11.setChecked(true);
                    name11.setText(dataSnapshot.child("time11").child("name").getValue().toString());
                    service11.setText(dataSnapshot.child("time11").child("service").getValue().toString());
                }  else if(dataSnapshot.child("time11").child("status").getValue().toString().equals("1")){
                    switch11.setChecked(true);
                    name11.setText("Available");
                    service11.setText(" ");
                }
                else{
                    switch11.setChecked(false);
                    name11.setText("Closed");
                }
                if (dataSnapshot.child("time12").child("status").getValue().toString().equals("2")) {
                    switch12.setChecked(true);
                    name12.setText(dataSnapshot.child("time12").child("name").getValue().toString());
                    service12.setText(dataSnapshot.child("time12").child("service").getValue().toString());
                } else if(dataSnapshot.child("time12").child("status").getValue().toString().equals("1")){
                    switch12.setChecked(true);
                    name12.setText("Available");
                    service12.setText(" ");
                }
                else{
                    switch12.setChecked(false);
                    name12.setText("Closed");
                }
                if (dataSnapshot.child("time13").child("status").getValue().toString().equals("2")) {
                    switch13.setChecked(true);
                    name13.setText(dataSnapshot.child("time13").child("name").getValue().toString());
                    service13.setText(dataSnapshot.child("time13").child("service").getValue().toString());
                } else if(dataSnapshot.child("time13").child("status").getValue().toString().equals("1")){
                    switch13.setChecked(true);
                    name13.setText("Available");
                    service13.setText(" ");
                }
                else{
                    switch13.setChecked(false);
                    name13.setText("Closed");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time1").child("name").setValue("----");
                    myref.child("time1").child("service").setValue("----");
                    myref.child("time1").child("status").setValue("1");

                } else {
                    myref.child("time1").child("name").setValue("----");
                    myref.child("time1").child("service").setValue("----");
                    myref.child("time1").child("status").setValue("0");
                }
            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time2").child("name").setValue("----");
                    myref.child("time2").child("service").setValue("----");
                    myref.child("time2").child("status").setValue("1");
                } else {
                    myref.child("time2").child("name").setValue("----");
                    myref.child("time2").child("service").setValue("----");
                    myref.child("time2").child("status").setValue("0");
                }
            }
        });
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time3").child("name").setValue("----");
                    myref.child("time3").child("service").setValue("----");
                    myref.child("time3").child("status").setValue("1");
                } else {
                    myref.child("time3").child("name").setValue("----");
                    myref.child("time3").child("service").setValue("----");
                    myref.child("time3").child("status").setValue("0");
                }
            }
        });
        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time4").child("name").setValue("----");
                    myref.child("time4").child("service").setValue("----");
                    myref.child("time4").child("status").setValue("1");
                } else {
                    myref.child("time4").child("name").setValue("----");
                    myref.child("time4").child("service").setValue("----");
                    myref.child("time4").child("status").setValue("0");
                }
            }
        });
        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time5").child("name").setValue("----");
                    myref.child("time5").child("service").setValue("----");
                    myref.child("time5").child("status").setValue("1");
                } else {
                    myref.child("time5").child("name").setValue("----");
                    myref.child("time5").child("service").setValue("----");
                    myref.child("time5").child("status").setValue("0");
                }
            }
        });
        switch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time6").child("name").setValue("----");
                    myref.child("time6").child("service").setValue("----");
                    myref.child("time6").child("status").setValue("1");
                } else {
                    myref.child("time6").child("name").setValue("----");
                    myref.child("time6").child("service").setValue("----");
                    myref.child("time6").child("status").setValue("0");
                }
            }
        });
        switch7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time7").child("name").setValue("----");
                    myref.child("time7").child("service").setValue("----");
                    myref.child("time7").child("status").setValue("1");
                } else {
                    myref.child("time7").child("name").setValue("----");
                    myref.child("time7").child("service").setValue("----");
                    myref.child("time7").child("status").setValue("0");
                }
            }
        });
        switch8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time8").child("name").setValue("----");
                    myref.child("time8").child("service").setValue("----");
                    myref.child("time8").child("status").setValue("1");
                } else {
                    myref.child("time8").child("name").setValue("----");
                    myref.child("time8").child("service").setValue("----");
                    myref.child("time8").child("status").setValue("0");
                }
            }
        });
        switch9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time9").child("name").setValue("----");
                    myref.child("time9").child("service").setValue("----");
                    myref.child("time9").child("status").setValue("1");
                } else {
                    myref.child("time9").child("name").setValue("----");
                    myref.child("time9").child("service").setValue("----");
                    myref.child("time9").child("status").setValue("0");
                }
            }
        });
        switch10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time10").child("name").setValue("----");
                    myref.child("time10").child("service").setValue("----");
                    myref.child("time10").child("status").setValue("1");
                } else {
                    myref.child("time10").child("name").setValue("----");
                    myref.child("time10").child("service").setValue("----");
                    myref.child("time10").child("status").setValue("0");
                }
            }
        });
        switch11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time11").child("name").setValue("----");
                    myref.child("time11").child("service").setValue("----");
                    myref.child("time11").child("status").setValue("1");
                } else {
                    myref.child("time11").child("name").setValue("----");
                    myref.child("time11").child("service").setValue("----");
                    myref.child("time11").child("status").setValue("0");
                }
            }
        });
        switch12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time12").child("name").setValue("----");
                    myref.child("time12").child("service").setValue("----");
                    myref.child("time12").child("status").setValue("1");
                } else {
                    myref.child("time12").child("name").setValue("----");
                    myref.child("time12").child("service").setValue("----");
                    myref.child("time12").child("status").setValue("0");
                }
            }
        });
        switch13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time13").child("name").setValue("----");
                    myref.child("time13").child("service").setValue("----");
                    myref.child("time13").child("status").setValue("1");
                } else {
                    myref.child("time13").child("name").setValue("----");
                    myref.child("time13").child("service").setValue("----");
                    myref.child("time13").child("status").setValue("0");
                }
            }
        });


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_barber, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.barber_menu_edit_profile:
                Toast.makeText(getContext(), "edit profile", Toast.LENGTH_LONG).show();
                return true;
            case R.id.barber_menu_edit_rates:
                Toast.makeText(getContext(), "edit rates", Toast.LENGTH_LONG).show();
                return true;
            case R.id.barber_menu_logout:
                FirebaseAuth.getInstance().signOut();
                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.sign_in, true)
                        .build();
                Navigation.findNavController(getView()).navigate(R.id.action_tomorrow_to_sign_in, null, navOptions);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
