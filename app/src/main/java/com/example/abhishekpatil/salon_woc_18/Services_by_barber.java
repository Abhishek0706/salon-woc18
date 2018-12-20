package com.example.abhishekpatil.salon_woc_18;

import android.content.Context;
import android.content.Intent;
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
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.navigation.Navigation;


public class Services_by_barber extends Fragment {

    private CheckBox box_haircut; private EditText rate_haircut;
    private CheckBox box_hairspa;private EditText rate_hairspa;
    private CheckBox box_haircolor;private EditText rate_haircolor;
    private CheckBox box_massage;private EditText rate_massage;
    private CheckBox box_facial;private EditText rate_facial;
    private CheckBox box_bleach;private EditText rate_bleach;
    private Button btn;
    private DatabaseReference myref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_services_by_barber,container,false);
        box_haircut = (CheckBox)view.findViewById(R.id.cb_haircut); rate_haircut = (EditText)view.findViewById(R.id.rate_haircut);
        box_hairspa = (CheckBox)view.findViewById(R.id.cb_hairspa); rate_hairspa = (EditText)view.findViewById(R.id.rate_hairspa);
        box_haircolor = (CheckBox)view.findViewById(R.id.cb_haircolor); rate_haircolor = (EditText)view.findViewById(R.id.rate_haircolor);
        box_massage = (CheckBox)view.findViewById(R.id.cb_massage); rate_massage = (EditText)view.findViewById(R.id.rate_massage);
        box_facial = (CheckBox)view.findViewById(R.id.cb_facial); rate_facial = (EditText)view.findViewById(R.id.rate_facial);
        box_bleach = (CheckBox)view.findViewById(R.id.cb_bleach); rate_bleach = (EditText)view.findViewById(R.id.rate_bleach);

        btn  = (Button)view.findViewById(R.id.btn_next_services);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        final String phonenumber = Services_by_barberArgs.fromBundle(getArguments()).getPhonenumber();
        myref =  FirebaseDatabase.getInstance().getReference().child("barber").child(phonenumber).child("services");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(box_haircut.isChecked()){
                    myref.child("haircut").setValue(rate_haircut.getText().toString());
                }
                else
                {
                    myref.child("haircut").setValue("0");
                }
                if(box_hairspa.isChecked()){
                    myref.child("hairspa").setValue(rate_hairspa.getText().toString());
                }
                else
                {
                    myref.child("hairspa").setValue("0");
                }
                if(box_haircolor.isChecked()){
                    myref.child("haircolor").setValue(rate_haircolor.getText().toString());
                }
                else
                {
                    myref.child("haircolor").setValue("0");
                }
                if(box_massage.isChecked()){
                    myref.child("massage").setValue(rate_massage.getText().toString());
                }
                else
                {
                    myref.child("massage").setValue("0");
                }
                if(box_facial.isChecked()){
                    myref.child("facial").setValue(rate_facial.getText().toString());
                }
                else
                {
                    myref.child("facial").setValue("0");
                }
                if(box_bleach.isChecked()){
                    myref.child("bleach").setValue(rate_bleach.getText().toString());
                }
                else
                {
                    myref.child("bleach").setValue("0");
                }


                Navigation.findNavController(v).navigate(R.id.action_services_by_barber_to_barber_main);
            }
        });

    }
}
