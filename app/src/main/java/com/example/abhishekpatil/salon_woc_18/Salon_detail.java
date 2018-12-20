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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Calendar;


public class Salon_detail extends Fragment {
    private String name;
    private String address;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_salon_detail, container, false);


        name = Salon_detailArgs.fromBundle(getArguments()).getName();
        address = Salon_detailArgs.fromBundle(getArguments()).getAddress();
        phonenumberBarber = Salon_detailArgs.fromBundle(getArguments()).getPhonenumber();

        phonenumberCustomer = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().toString();
        customerref = FirebaseDatabase.getInstance().getReference().child("customer").child(phonenumberCustomer);
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
        mname.setText(name);
        maddrerss.setText(address);
        mphonenumber.setText(phonenumberBarber);


        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        final String date = day + month + year;

        barberref.child("services").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("haircut").getValue().toString().equals("0")) {
                    cb1.setEnabled(false);
                } else {
                    cb1.setText("haircut(" + dataSnapshot.child("haircut").getValue().toString() + ")");
                }
                if (dataSnapshot.child("hairspa").getValue().toString().equals("0")) {
                    cb2.setEnabled(false);

                } else {
                    cb2.setText("hairspa(" + dataSnapshot.child("hairspa").getValue().toString() + ")");
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

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        barberref.child(date).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    if (!dataSnapshot.child("time1").child("status").getValue().toString().equals("1")) {
                        rb1.setClickable(false);
                        rb1.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child("time2").child("status").getValue().toString().equals("1")) {
                        rb2.setClickable(false);
                        rb2.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child("time3").child("status").getValue().toString().equals("1")) {
                        rb3.setClickable(false);
                        rb3.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child("time4").child("status").getValue().toString().equals("1")) {
                        rb4.setClickable(false);
                        rb4.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child("time5").child("status").getValue().toString().equals("1")) {
                        rb5.setClickable(false);
                        rb5.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child("time6").child("status").getValue().toString().equals("1")) {
                        rb6.setClickable(false);
                        rb6.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child("time7").child("status").getValue().toString().equals("1")) {
                        rb7.setClickable(false);
                        rb7.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child("time8").child("status").getValue().toString().equals("1")) {
                        rb8.setClickable(false);
                        rb8.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child("time9").child("status").getValue().toString().equals("1")) {
                        rb9.setClickable(false);
                        rb9.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child("time10").child("status").getValue().toString().equals("1")) {
                        rb10.setClickable(false);
                        rb10.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child("time11").child("status").getValue().toString().equals("1")) {
                        rb11.setClickable(false);
                        rb11.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child("time12").child("status").getValue().toString().equals("1")) {
                        rb12.setClickable(false);
                        rb12.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                    if (!dataSnapshot.child("time13").child("status").getValue().toString().equals("1")) {
                        rb13.setClickable(false);
                        rb13.setTextColor(getResources().getColor(R.color.offcolor));
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
                book.setEnabled(false);

                if (cb1.isChecked()) {
                    myServices += "haircut ";
                }
                if (cb2.isChecked()) {
                    myServices += "hairspa ";
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

                int selected_id = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton)getView().findViewById(selected_id);
                String myTime = radioButton.getText().toString();


                if(myTime.equals(rb1.getText().toString())){
                    barberref.child(date).child("time1").child("name").setValue(customerName);
                    barberref.child(date).child("time1").child("service").setValue(myServices);
                    barberref.child(date).child("time1").child("status").setValue("2"); }
                if(myTime.equals(rb2.getText().toString())){
                    barberref.child(date).child("time2").child("name").setValue(customerName);
                    barberref.child(date).child("time2").child("service").setValue(myServices);
                    barberref.child(date).child("time2").child("status").setValue("2"); }
                if(myTime.equals(rb3.getText().toString())){
                    barberref.child(date).child("time3").child("name").setValue(customerName);
                    barberref.child(date).child("time3").child("service").setValue(myServices);
                    barberref.child(date).child("time3").child("status").setValue("2"); }
                if(myTime.equals(rb4.getText().toString())){
                    barberref.child(date).child("time4").child("name").setValue(customerName);
                    barberref.child(date).child("time4").child("service").setValue(myServices);
                    barberref.child(date).child("time4").child("status").setValue("2"); }
                if(myTime.equals(rb5.getText().toString())){
                    barberref.child(date).child("time5").child("name").setValue(customerName);
                    barberref.child(date).child("time5").child("service").setValue(myServices);
                    barberref.child(date).child("time5").child("status").setValue("2"); }
                if(myTime.equals(rb6.getText().toString())){
                    barberref.child(date).child("time6").child("name").setValue(customerName);
                    barberref.child(date).child("time6").child("service").setValue(myServices);
                    barberref.child(date).child("time6").child("status").setValue("2"); }
                if(myTime.equals(rb7.getText().toString())){
                    barberref.child(date).child("time7").child("name").setValue(customerName);
                    barberref.child(date).child("time7").child("service").setValue(myServices);
                    barberref.child(date).child("time7").child("status").setValue("2"); }
                if(myTime.equals(rb8.getText().toString())){
                    barberref.child(date).child("time8").child("name").setValue(customerName);
                    barberref.child(date).child("time8").child("service").setValue(myServices);
                    barberref.child(date).child("time8").child("status").setValue("2"); }
                if(myTime.equals(rb9.getText().toString())){
                    barberref.child(date).child("time9").child("name").setValue(customerName);
                    barberref.child(date).child("time9").child("service").setValue(myServices);
                    barberref.child(date).child("time9").child("status").setValue("2"); }
                if(myTime.equals(rb10.getText().toString())){
                    barberref.child(date).child("time10").child("name").setValue(customerName);
                    barberref.child(date).child("time10").child("service").setValue(myServices);
                    barberref.child(date).child("time10").child("status").setValue("1"); }
                if(myTime.equals(rb11.getText().toString())){
                    barberref.child(date).child("time11").child("name").setValue(customerName);
                    barberref.child(date).child("time11").child("service").setValue(myServices);
                    barberref.child(date).child("time11").child("status").setValue("2"); }
                if(myTime.equals(rb12.getText().toString())){
                    barberref.child(date).child("time12").child("name").setValue(customerName);
                    barberref.child(date).child("time12").child("service").setValue(myServices);
                    barberref.child(date).child("time12").child("status").setValue("2"); }
                if(myTime.equals(rb13.getText().toString())){
                    barberref.child(date).child("time13").child("name").setValue(customerName);
                    barberref.child(date).child("time13").child("service").setValue(myServices);
                    barberref.child(date).child("time13").child("status").setValue("2"); }


            }
        });

    }
}
