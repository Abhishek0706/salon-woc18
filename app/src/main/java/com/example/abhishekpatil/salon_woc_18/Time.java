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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.navigation.Navigation;


public class Time extends Fragment {

    private DatabaseReference myref;
    private CheckBox time1;
    private CheckBox time2;
    private CheckBox time3;
    private CheckBox time4;
    private CheckBox time5;
    private CheckBox time6;
    private CheckBox time7;
    private CheckBox time8;
    private CheckBox time9;
    private CheckBox time10;
    private CheckBox time11;
    private CheckBox time12;
    private CheckBox time13;
    private Button btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time,container,false);
        String phonenumber = TimeArgs.fromBundle(getArguments()).getPhonenumber();
        myref =  FirebaseDatabase.getInstance().getReference().child("barber").child(phonenumber).child("Time");

        time1 = (CheckBox)view.findViewById(R.id.time1);
        time2 = (CheckBox)view.findViewById(R.id.time2);
        time3 = (CheckBox)view.findViewById(R.id.time3);
        time4 = (CheckBox)view.findViewById(R.id.time4);
        time5 = (CheckBox)view.findViewById(R.id.time5);
        time6 = (CheckBox)view.findViewById(R.id.time6);
        time7 = (CheckBox)view.findViewById(R.id.time7);
        time8 = (CheckBox)view.findViewById(R.id.time8);
        time9 = (CheckBox)view.findViewById(R.id.time9);
        time10 = (CheckBox)view.findViewById(R.id.time10);
        time11 = (CheckBox)view.findViewById(R.id.time11);
        time12 = (CheckBox)view.findViewById(R.id.time12);
        time13 = (CheckBox)view.findViewById(R.id.time13);
        btn = (Button)view.findViewById(R.id.btn_next_timetime);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(time1.isChecked()){
                    myref.child("time1").setValue(1);
                }
                else{
                    myref.child("time1").setValue(0);
                }
                if(time2.isChecked()){
                    myref.child("time2").setValue(1);
                }
                else{
                    myref.child("time2").setValue(0);
                }
                if(time3.isChecked()){
                    myref.child("time3").setValue(1);
                }
                else{
                    myref.child("time3").setValue(0);
                }
                if(time4.isChecked()){
                    myref.child("time4").setValue(1);
                }
                else{
                    myref.child("time4").setValue(0);
                }
                if(time5.isChecked()){
                    myref.child("time5").setValue(1);
                }
                else{
                    myref.child("time5").setValue(0);
                }
                if(time6.isChecked()){
                    myref.child("time6").setValue(1);
                }
                else{
                    myref.child("time6").setValue(0);
                }
                if(time7.isChecked()){
                    myref.child("time7").setValue(1);
                }
                else{
                    myref.child("time7").setValue(0);
                }
                if(time8.isChecked()){
                    myref.child("time8").setValue(1);
                }
                else{
                    myref.child("time8").setValue(0);
                }
                if(time9.isChecked()){
                    myref.child("time9").setValue(1);
                }
                else{
                    myref.child("time9").setValue(0);
                }
                if(time10.isChecked()){
                    myref.child("time10").setValue(1);
                }
                else{
                    myref.child("time10").setValue(0);
                }
                if(time11.isChecked()){
                    myref.child("time11").setValue(1);
                }
                else{
                    myref.child("time11").setValue(0);
                }
                if(time12.isChecked()){
                    myref.child("time12").setValue(1);
                }
                else{
                    myref.child("time12").setValue(0);
                }
                if(time13.isChecked()){
                    myref.child("time13").setValue(1);
                }
                else{
                    myref.child("time13").setValue(0);
                }

                Navigation.findNavController(v).navigate(R.id.action_time4_to_barber_main);
            }
        });
    }
}
