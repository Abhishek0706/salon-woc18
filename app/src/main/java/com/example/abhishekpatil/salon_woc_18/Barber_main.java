package com.example.abhishekpatil.salon_woc_18;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.abhishekpatil.salon_woc_18.viewModels.Barber_main_view_model;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

public class Barber_main extends Fragment {



    private LinearLayout todayLayout;
    private DatabaseReference myref;
    private DatabaseReference timeref;
    private String phonenumber;
    private TextView name1;
    private TextView service1;
    private TextView name2;
    private TextView service2;
    private TextView name3;
    private TextView service3;
    private TextView name4;
    private TextView service4;
    private TextView name5;
    private TextView service5;
    private TextView name6;
    private TextView service6;
    private TextView name7;
    private TextView service7;
    private TextView name8;
    private TextView service8;
    private TextView name9;
    private TextView service9;
    private TextView name10;
    private TextView service10;
    private TextView name11;
    private TextView service11;
    private TextView name12;
    private TextView service12;
    private TextView name13;
    private TextView service13;
    private Button migrate;
    private TextView phone1;
    private TextView phone2;
    private TextView phone3;
    private TextView phone4;
    private TextView phone5;
    private TextView phone6;
    private TextView phone7;
    private TextView phone8;
    private TextView phone9;
    private TextView phone10;
    private TextView phone11;
    private TextView phone12;
    private TextView phone13;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_barber_main, container, false);

        todayLayout = (LinearLayout) view.findViewById(R.id.layout_today);
        name1 = (TextView) view.findViewById(R.id.today_status_time1);
        service1 = (TextView) view.findViewById(R.id.today_service_time1);
        name2 = (TextView) view.findViewById(R.id.today_status_time2);
        service2 = (TextView) view.findViewById(R.id.today_service_time2);
        name3 = (TextView) view.findViewById(R.id.today_status_time3);
        service3 = (TextView) view.findViewById(R.id.today_service_time3);
        name4 = (TextView) view.findViewById(R.id.today_status_time4);
        service4 = (TextView) view.findViewById(R.id.today_service_time4);
        name5 = (TextView) view.findViewById(R.id.today_status_time5);
        service5 = (TextView) view.findViewById(R.id.today_service_time5);
        name6 = (TextView) view.findViewById(R.id.today_status_time6);
        service6 = (TextView) view.findViewById(R.id.today_service_time6);
        name7 = (TextView) view.findViewById(R.id.today_status_time7);
        service7 = (TextView) view.findViewById(R.id.today_service_time7);
        name8 = (TextView) view.findViewById(R.id.today_status_time8);
        service8 = (TextView) view.findViewById(R.id.today_service_time8);
        name9 = (TextView) view.findViewById(R.id.today_status_time9);
        service9 = (TextView) view.findViewById(R.id.today_service_time9);
        name10 = (TextView) view.findViewById(R.id.today_status_time10);
        service10 = (TextView) view.findViewById(R.id.today_service_time10);
        name11 = (TextView) view.findViewById(R.id.today_status_time11);
        service11 = (TextView) view.findViewById(R.id.today_service_time11);
        name12 = (TextView) view.findViewById(R.id.today_status_time12);
        service12 = (TextView) view.findViewById(R.id.today_service_time12);
        name13 = (TextView) view.findViewById(R.id.today_status_time13);
        service13 = (TextView) view.findViewById(R.id.today_service_time13);
        phone1 = (TextView) view.findViewById(R.id.today_phone_time1);
        phone2 = (TextView) view.findViewById(R.id.today_phone_time2);
        phone3 = (TextView) view.findViewById(R.id.today_phone_time3);
        phone4 = (TextView) view.findViewById(R.id.today_phone_time4);
        phone5 = (TextView) view.findViewById(R.id.today_phone_time5);
        phone6 = (TextView) view.findViewById(R.id.today_phone_time6);
        phone7 = (TextView) view.findViewById(R.id.today_phone_time7);
        phone8 = (TextView) view.findViewById(R.id.today_phone_time8);
        phone9 = (TextView) view.findViewById(R.id.today_phone_time9);
        phone10 = (TextView) view.findViewById(R.id.today_phone_time10);
        phone11 = (TextView) view.findViewById(R.id.today_phone_time11);
        phone12 = (TextView) view.findViewById(R.id.today_phone_time12);
        phone13 = (TextView) view.findViewById(R.id.today_phone_time13);
        phonenumber = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().substring(1);

        migrate = (Button) view.findViewById(R.id.btn_tomorrow);
        timeref = FirebaseDatabase.getInstance().getReference().child("barber").child(phonenumber);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        City.setPhonenumber(phonenumber);


        migrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.barber_main, true)
                        .build();
                Navigation.findNavController(getView()).navigate(R.id.action_barber_main_to_tomorrow, null, navOptions);
            }
        });
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, 1);
        String year2 = String.valueOf(calendar1.get(Calendar.YEAR));
        String month2 = String.valueOf(calendar1.get(Calendar.MONTH)+1);
        String day2 = String.valueOf(calendar1.get(Calendar.DAY_OF_MONTH));
        if(day2.length()==1){
            day2 = "0"+day2;
        }
        if(month2.length()==1){
            month2 = "0"+month2;
        }
        final String tomorrow = day2 + month2 + year2;

        timeref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(tomorrow).exists()) {

                } else {
                    for (int i = 1; i <= 13; i++) {
                        String s = String.valueOf(i);
                        s = "time" + s;
                        timeref.child(tomorrow).child(s).child("name").setValue("----");
                        timeref.child(tomorrow).child(s).child("service").setValue("----");
                        timeref.child(tomorrow).child(s).child("phonenumber").setValue("----");
                        timeref.child(tomorrow).child(s).child("status").setValue("1");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Barber_main_view_model viewModel = ViewModelProviders.of(this).get(Barber_main_view_model.class);
        LiveData<DataSnapshot> liveData = viewModel.getDataSnapshotLiveDatabarber();

        liveData.observe(this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(@Nullable DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    if (dataSnapshot.child("time1").child("status").getValue().toString().equals("2")) {
                        name1.setText(dataSnapshot.child("time1").child("name").getValue().toString());
                        service1.setText(dataSnapshot.child("time1").child("service").getValue().toString());
                        phone1.setText(dataSnapshot.child("time1").child("phonenumber").getValue().toString());
                    }
                    if (dataSnapshot.child("time2").child("status").getValue().toString().equals("2")) {
                        name2.setText(dataSnapshot.child("time2").child("name").getValue().toString());
                        service2.setText(dataSnapshot.child("time2").child("service").getValue().toString());
                        phone2.setText(dataSnapshot.child("time2").child("phonenumber").getValue().toString());
                    }
                    if (dataSnapshot.child("time3").child("status").getValue().toString().equals("2")) {
                        name3.setText(dataSnapshot.child("time3").child("name").getValue().toString());
                        service3.setText(dataSnapshot.child("time3").child("service").getValue().toString());
                        phone3.setText(dataSnapshot.child("time3").child("phonenumber").getValue().toString());
                    }
                    if (dataSnapshot.child("time4").child("status").getValue().toString().equals("2")) {
                        name4.setText(dataSnapshot.child("time4").child("name").getValue().toString());
                        service4.setText(dataSnapshot.child("time4").child("service").getValue().toString());
                        phone4.setText(dataSnapshot.child("time4").child("phonenumber").getValue().toString());
                    }

                    if (dataSnapshot.child("time5").child("status").getValue().toString().equals("2")) {
                        name5.setText(dataSnapshot.child("time5").child("name").getValue().toString());
                        service5.setText(dataSnapshot.child("time5").child("service").getValue().toString());
                        phone5.setText(dataSnapshot.child("time5").child("phonenumber").getValue().toString());
                    }

                    if (dataSnapshot.child("time6").child("status").getValue().toString().equals("2")) {
                        name6.setText(dataSnapshot.child("time6").child("name").getValue().toString());
                        service6.setText(dataSnapshot.child("time6").child("service").getValue().toString());
                        phone6.setText(dataSnapshot.child("time6").child("phonenumber").getValue().toString());
                    }

                    if (dataSnapshot.child("time7").child("status").getValue().toString().equals("2")) {
                        name7.setText(dataSnapshot.child("time7").child("name").getValue().toString());
                        service7.setText(dataSnapshot.child("time7").child("service").getValue().toString());
                        phone7.setText(dataSnapshot.child("time7").child("phonenumber").getValue().toString());
                    }

                    if (dataSnapshot.child("time8").child("status").getValue().toString().equals("2")) {
                        name8.setText(dataSnapshot.child("time8").child("name").getValue().toString());
                        service8.setText(dataSnapshot.child("time8").child("service").getValue().toString());
                        phone8.setText(dataSnapshot.child("time8").child("phonenumber").getValue().toString());
                    }

                    if (dataSnapshot.child("time9").child("status").getValue().toString().equals("2")) {
                        name9.setText(dataSnapshot.child("time9").child("name").getValue().toString());
                        service9.setText(dataSnapshot.child("time9").child("service").getValue().toString());
                        phone9.setText(dataSnapshot.child("time9").child("phonenumber").getValue().toString());
                    }

                    if (dataSnapshot.child("time10").child("status").getValue().toString().equals("2")) {
                        name10.setText(dataSnapshot.child("time10").child("name").getValue().toString());
                        service10.setText(dataSnapshot.child("time10").child("service").getValue().toString());
                    }

                    if (dataSnapshot.child("time11").child("status").getValue().toString().equals("2")) {
                        name11.setText(dataSnapshot.child("time11").child("name").getValue().toString());
                        service11.setText(dataSnapshot.child("time11").child("service").getValue().toString());
                        phone11.setText(dataSnapshot.child("time11").child("phonenumber").getValue().toString());
                    }

                    if (dataSnapshot.child("time12").child("status").getValue().toString().equals("2")) {
                        name12.setText(dataSnapshot.child("time12").child("name").getValue().toString());
                        service12.setText(dataSnapshot.child("time12").child("service").getValue().toString());
                        phone12.setText(dataSnapshot.child("time12").child("phonenumber").getValue().toString());
                    }

                    if (dataSnapshot.child("time13").child("status").getValue().toString().equals("2")) {
                        name13.setText(dataSnapshot.child("time13").child("name").getValue().toString());
                        service13.setText(dataSnapshot.child("time13").child("service").getValue().toString());
                        phone13.setText(dataSnapshot.child("time13").child("phonenumber").getValue().toString());
                    }

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
                Barber_mainDirections.ActionBarberMainToEditProfile action = Barber_mainDirections.actionBarberMainToEditProfile();
                action.setPhonenumber(phonenumber);
                action.setType(1);
                Navigation.findNavController(getView()).navigate(action);
                return true;
            case R.id.barber_menu_edit_rates:
                Barber_mainDirections.ActionBarberMainToEditRates action1 = Barber_mainDirections.actionBarberMainToEditRates();
                action1.setPhonenumber(phonenumber);
                Navigation.findNavController(getView()).navigate(action1);
                return true;
            case R.id.barber_menu_logout:
                FirebaseAuth.getInstance().signOut();
                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.barber_main, true)
                        .build();
                Navigation.findNavController(getView()).navigate(R.id.action_barber_main_to_home);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }


}



