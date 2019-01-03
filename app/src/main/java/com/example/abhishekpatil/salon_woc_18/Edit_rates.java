package com.example.abhishekpatil.salon_woc_18;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

public class Edit_rates extends Fragment {
    private CheckBox box_haircut;
    private EditText rate_haircut;
    private CheckBox box_hairspa;
    private EditText rate_hairspa;
    private CheckBox box_haircolor;
    private EditText rate_haircolor;
    private CheckBox box_massage;
    private EditText rate_massage;
    private CheckBox box_facial;
    private EditText rate_facial;
    private CheckBox box_bleach;
    private EditText rate_bleach;
    private Button btn;
    private DatabaseReference myref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_rates,container,false);
        box_haircut = (CheckBox) view.findViewById(R.id.cbx_haircut);
        rate_haircut = (EditText) view.findViewById(R.id.ratex_haircut);
        box_hairspa = (CheckBox) view.findViewById(R.id.cbx_hairspa);
        rate_hairspa = (EditText) view.findViewById(R.id.ratex_hairspa);
        box_haircolor = (CheckBox) view.findViewById(R.id.cbx_haircolor);
        rate_haircolor = (EditText) view.findViewById(R.id.ratex_haircolor);
        box_massage = (CheckBox) view.findViewById(R.id.cbx_massage);
        rate_massage = (EditText) view.findViewById(R.id.ratex_massage);
        box_facial = (CheckBox) view.findViewById(R.id.cbx_facial);
        rate_facial = (EditText) view.findViewById(R.id.ratex_facial);
        box_bleach = (CheckBox) view.findViewById(R.id.cbx_bleach);
        rate_bleach = (EditText) view.findViewById(R.id.ratex_bleach);

        btn = (Button) view.findViewById(R.id.btn_edit_rates);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        final String phonenumber = City.getPhonenumber();
        myref = FirebaseDatabase.getInstance().getReference().child("barber").child(phonenumber).child("services");
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(!dataSnapshot.child("haircut").getValue().toString().equals("0")){
                    box_haircut.setChecked(true);
                    rate_haircut.setText(dataSnapshot.child("haircut").getValue().toString());
                }
                else{
                    box_haircut.setChecked(false);
                }
                if(!dataSnapshot.child("shave").getValue().toString().equals("0")){
                    box_hairspa.setChecked(true);
                    rate_hairspa.setText(dataSnapshot.child("shave").getValue().toString());
                }
                else{
                    box_hairspa.setChecked(false);
                }
                if(!dataSnapshot.child("haircolor").getValue().toString().equals("0")){
                    box_haircolor.setChecked(true);
                    rate_haircolor.setText(dataSnapshot.child("haircolor").getValue().toString());
                }
                else{
                    box_haircolor.setChecked(false);
                }
                if(!dataSnapshot.child("massage").getValue().toString().equals("0")){
                    box_massage.setChecked(true);
                    rate_massage.setText(dataSnapshot.child("massage").getValue().toString());
                }
                else{
                    box_massage.setChecked(false);
                }
                if(!dataSnapshot.child("facial").getValue().toString().equals("0")){
                    box_facial.setChecked(true);
                    rate_facial.setText(dataSnapshot.child("facial").getValue().toString());
                }
                else{
                    box_facial.setChecked(false);
                }
                if(!dataSnapshot.child("bleach").getValue().toString().equals("0")){
                    box_bleach.setChecked(true);
                    rate_bleach.setText(dataSnapshot.child("bleach").getValue().toString());
                }
                else{
                    box_bleach.setChecked(false);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (box_haircut.isChecked()) {
                    myref.child("haircut").setValue(rate_haircut.getText().toString());
                } else {
                    myref.child("haircut").setValue("0");
                }
                if (box_hairspa.isChecked()) {
                    myref.child("shave").setValue(rate_hairspa.getText().toString());
                } else {
                    myref.child("shave").setValue("0");
                }
                if (box_haircolor.isChecked()) {
                    myref.child("haircolor").setValue(rate_haircolor.getText().toString());
                } else {
                    myref.child("haircolor").setValue("0");
                }
                if (box_massage.isChecked()) {
                    myref.child("massage").setValue(rate_massage.getText().toString());
                } else {
                    myref.child("massage").setValue("0");
                }
                if (box_facial.isChecked()) {
                    myref.child("facial").setValue(rate_facial.getText().toString());
                } else {
                    myref.child("facial").setValue("0");
                }
                if (box_bleach.isChecked()) {
                    myref.child("bleach").setValue(rate_bleach.getText().toString());
                } else {
                    myref.child("bleach").setValue("0");
                }
                btn.setEnabled(false);

                Toast.makeText(getContext(),"Changes Applied.",Toast.LENGTH_LONG).show();

                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.edit_rates, true)
                        .build();
                Navigation.findNavController(v).navigate(R.id.action_edit_rates_to_barber_main,null,navOptions);
            }
        });
    }
}
