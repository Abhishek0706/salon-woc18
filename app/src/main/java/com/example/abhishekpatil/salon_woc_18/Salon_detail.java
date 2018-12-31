package com.example.abhishekpatil.salon_woc_18;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.abhishekpatil.salon_woc_18.viewModels.Salon_detail_view_model;
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

public class Salon_detail extends Fragment {
    private String Barbername;
    private String Barberaddress;
    private Salon_detail_view_model view_model;
    private String myServices = " ";
    private String phonenumberBarber;
    private String customerName;
    private String phonenumberCustomer;
    private TextView mname;
    private TextView maddrerss;
    private TextView mphonenumber;
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;
    private CheckBox cb5;
    private CheckBox cb6;
    private RadioGroup radioGroup;
    private RadioButton radioButton, rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10, rb11, rb12, rb13;
    private DatabaseReference barberref;
    private DatabaseReference customerref;
    private Button book;
    private DatabaseReference historyref;
    private static String date;
    private LinearLayout layout;
    private TextView heading2;
    private StorageReference mstorage;
    private ImageView myImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_salon_detail, container, false);
        myImage = (ImageView) view.findViewById(R.id.salon_imageView);
        mstorage = FirebaseStorage.getInstance().getReference();
        Barbername = Salon_detailArgs.fromBundle(getArguments()).getName();
        Barberaddress = Salon_detailArgs.fromBundle(getArguments()).getAddress();
        phonenumberBarber = Salon_detailArgs.fromBundle(getArguments()).getPhonenumber();
        layout = (LinearLayout) view.findViewById(R.id.salon_detail_layout);
        phonenumberCustomer = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().substring(1);
        heading2 = (TextView) view.findViewById(R.id.heading2);
        customerref = FirebaseDatabase.getInstance().getReference().child("customer").child(phonenumberCustomer);
        historyref = customerref.child("history").push();
        mname = (TextView) view.findViewById(R.id.salon_info_name);
        maddrerss = (TextView) view.findViewById(R.id.salon_info_address);
        mphonenumber = (TextView) view.findViewById(R.id.salon_info_phonenumber);
        cb1 = (CheckBox) view.findViewById(R.id.info_cb_haircut);
        cb2 = (CheckBox) view.findViewById(R.id.info_cb_hairspa);
        cb3 = (CheckBox) view.findViewById(R.id.info_cb_haircolor);
        cb4 = (CheckBox) view.findViewById(R.id.info_cb_massage);
        cb5 = (CheckBox) view.findViewById(R.id.info_cb_facial);
        cb6 = (CheckBox) view.findViewById(R.id.info_cb_bleach);
        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);
        rb1 = (RadioButton) view.findViewById(R.id.rb1);
        rb2 = (RadioButton) view.findViewById(R.id.rb2);
        rb3 = (RadioButton) view.findViewById(R.id.rb3);
        rb4 = (RadioButton) view.findViewById(R.id.rb4);
        rb5 = (RadioButton) view.findViewById(R.id.rb5);
        rb6 = (RadioButton) view.findViewById(R.id.rb6);
        rb7 = (RadioButton) view.findViewById(R.id.rb7);
        rb8 = (RadioButton) view.findViewById(R.id.rb8);
        rb9 = (RadioButton) view.findViewById(R.id.rb9);
        rb10 = (RadioButton) view.findViewById(R.id.rb10);
        rb11 = (RadioButton) view.findViewById(R.id.rb11);
        rb12 = (RadioButton) view.findViewById(R.id.rb12);
        rb13 = (RadioButton) view.findViewById(R.id.rb13);
        barberref = FirebaseDatabase.getInstance().getReference().child("barber").child(phonenumberBarber);
        book = (Button) view.findViewById(R.id.btn_booknow);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mname.setText(Barbername);
        maddrerss.setText(Barberaddress);
        mphonenumber.setText(phonenumberBarber);
        mstorage.child(City.getBarberphonenumber()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getContext()).load(uri).into(myImage);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH)+1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        if(day.length()==1){
            day = "0"+day;
        }
        if(month.length()==1){
            month = "0"+month;
        }
        date = day + month + year;

        view_model = ViewModelProviders.of(this).get(Salon_detail_view_model.class);


        LiveData<DataSnapshot> liveData1 = view_model.getLivedataservices();
        liveData1.observe(this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(@Nullable DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("haircut").getValue().toString().equals("0")) {
                    cb1.setEnabled(false);
                } else {
                    cb1.setText("haircut(" + dataSnapshot.child("haircut").getValue().toString() + ")");
                }
                if (dataSnapshot.child("shave").getValue().toString().equals("0")) {
                    cb2.setEnabled(false);

                } else {
                    cb2.setText("shave(" + dataSnapshot.child("shave").getValue().toString() + ")");
                }
                if (dataSnapshot.child("haircolor").getValue().toString().equals("0")) {
                    cb3.setEnabled(false);

                } else {
                    cb3.setText("haircolor(" + dataSnapshot.child("haircolor").getValue().toString() + ")");
                }
                if (dataSnapshot.child("massage").getValue().toString().equals("0")) {
                    cb4.setEnabled(false);

                } else {
                    cb4.setText("massage(" + dataSnapshot.child("massage").getValue().toString() + ")");
                }
                if (dataSnapshot.child("facial").getValue().toString().equals("0")) {
                    cb5.setEnabled(false);

                } else {
                    cb5.setText("facial(" + dataSnapshot.child("facial").getValue().toString() + ")");
                }
                if (dataSnapshot.child("bleach").getValue().toString().equals("0")) {
                    cb6.setEnabled(false);

                } else {
                    cb6.setText("bleach(" + dataSnapshot.child("bleach").getValue().toString() + ")");
                }
            }
        });

        LiveData<DataSnapshot> liveData = view_model.getLivedatabarber();
        liveData.observe(this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(@Nullable DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(date).exists()) {
                    if (!dataSnapshot.child(date).child("time1").child("status").getValue().toString().equals("1")) {
                        rb1.setClickable(false);
                        rb1.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child(date).child("time2").child("status").getValue().toString().equals("1")) {
                        rb2.setClickable(false);
                        rb2.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child(date).child("time3").child("status").getValue().toString().equals("1")) {
                        rb3.setClickable(false);
                        rb3.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child(date).child("time4").child("status").getValue().toString().equals("1")) {
                        rb4.setClickable(false);
                        rb4.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child(date).child("time5").child("status").getValue().toString().equals("1")) {
                        rb5.setClickable(false);
                        rb5.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child(date).child("time6").child("status").getValue().toString().equals("1")) {
                        rb6.setClickable(false);
                        rb6.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child(date).child("time7").child("status").getValue().toString().equals("1")) {
                        rb7.setClickable(false);
                        rb7.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child(date).child("time8").child("status").getValue().toString().equals("1")) {
                        rb8.setClickable(false);
                        rb8.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child(date).child("time9").child("status").getValue().toString().equals("1")) {
                        rb9.setClickable(false);
                        rb9.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child(date).child("time10").child("status").getValue().toString().equals("1")) {
                        rb10.setClickable(false);
                        rb10.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child(date).child("time11").child("status").getValue().toString().equals("1")) {
                        rb11.setClickable(false);
                        rb11.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child(date).child("time12").child("status").getValue().toString().equals("1")) {
                        rb12.setClickable(false);
                        rb12.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child(date).child("time13").child("status").getValue().toString().equals("1")) {
                        rb13.setClickable(false);
                        rb13.setTextColor(getResources().getColor(R.color.offcolor));
                    }

                } else {
                    // if barber has not opened his mobile today and customer opened his id then this makes his database and make all slot off
                    for (int i = 1; i <= 13; i++) {
                        String s = String.valueOf(i);
                        s = "time" + s;
                        barberref.child(date).child(s).child("name").setValue("----");
                        barberref.child(date).child(s).child("service").setValue("----");
                        barberref.child(date).child(s).child("status").setValue("0");
                    }
                }

            }
        });


        customerref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                customerName = dataSnapshot.child("name").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (cb1.isChecked()) {
                    myServices += "haircut ";
                }
                if (cb2.isChecked()) {
                    myServices += "shave ";
                }
                if (cb3.isChecked()) {
                    myServices += "haircolor ";
                }
                if (cb4.isChecked()) {
                    myServices += "massage ";
                }
                if (cb5.isChecked()) {
                    myServices += "facial ";
                }
                if (cb6.isChecked()) {
                    myServices += "bleach ";
                }

                if (myServices.equals(" ")) {
                    Toast.makeText(getContext(), "Minimum One Service Required", Toast.LENGTH_LONG).show();
                    return;
                }
                int selected_id = radioGroup.getCheckedRadioButtonId();
                if (selected_id == -1) {
                    Toast.makeText(getContext(), "Choose Time", Toast.LENGTH_LONG).show();
                    heading2.setTextColor(getResources().getColor(R.color.redcolor));
                    return;
                }
                radioButton = (RadioButton) getView().findViewById(selected_id);
                String myTime = radioButton.getText().toString();


                if (myTime.equals(rb1.getText().toString())) {
                    bookAppointment("time1", customerName, myServices);
                } else if (myTime.equals(rb2.getText().toString())) {
                    bookAppointment("time2", customerName, myServices);
                } else if (myTime.equals(rb3.getText().toString())) {
                    bookAppointment("time3", customerName, myServices);
                } else if (myTime.equals(rb4.getText().toString())) {
                    bookAppointment("time4", customerName, myServices);
                } else if (myTime.equals(rb5.getText().toString())) {
                    bookAppointment("time5", customerName, myServices);
                } else if (myTime.equals(rb6.getText().toString())) {
                    bookAppointment("time6", customerName, myServices);
                } else if (myTime.equals(rb7.getText().toString())) {
                    bookAppointment("time7", customerName, myServices);
                } else if (myTime.equals(rb8.getText().toString())) {
                    bookAppointment("time8", customerName, myServices);
                } else if (myTime.equals(rb9.getText().toString())) {
                    bookAppointment("time9", customerName, myServices);
                } else if (myTime.equals(rb10.getText().toString())) {
                    bookAppointment("time10", customerName, myServices);
                } else if (myTime.equals(rb11.getText().toString())) {
                    bookAppointment("time11", customerName, myServices);
                } else if (myTime.equals(rb12.getText().toString())) {
                    bookAppointment("time12", customerName, myServices);
                } else if (myTime.equals(rb13.getText().toString())) {
                    bookAppointment("time13", customerName, myServices);
                } else {
                    Toast.makeText(getContext(), "Choose Time", Toast.LENGTH_LONG).show();
                    return;

                }

                book.setEnabled(false);
                Toast.makeText(getContext(), "Booking Successful", Toast.LENGTH_LONG).show();
                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.salon_detail, true).build();
                Navigation.findNavController(getView()).navigate(R.id.action_salon_detail_to_customer_main, null, navOptions);


            }
        });


    }

    public void bookAppointment(String time, String name, String service) {
        barberref.child(date).child(time).child("name").setValue(name);
        barberref.child(date).child(time).child("service").setValue(service);
        barberref.child(date).child(time).child("status").setValue("2");
        barberref.child(date).child(time).child("phonenumber").setValue(phonenumberCustomer);
        historyref.child("date").setValue(date);
        historyref.child("time").setValue(time);
        historyref.child("phonenumber").setValue(phonenumberBarber);
        historyref.child("name").setValue(Barbername);
        historyref.child("address").setValue(Barberaddress);
    }
}
