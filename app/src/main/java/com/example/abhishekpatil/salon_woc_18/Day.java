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

public class Day extends Fragment {
    private DatabaseReference myref;
    private CheckBox ch_Sunday;
    private CheckBox ch_Monday;
    private CheckBox ch_Tuesday;
    private CheckBox ch_Wednesday;
    private CheckBox ch_Thursday;
    private CheckBox ch_Friday;
    private CheckBox ch_Saturday;

    private Button btn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day,container,false);


        String phonenumber = DayArgs.fromBundle(getArguments()).getPhonenumber();
        myref =  FirebaseDatabase.getInstance().getReference().child("barber").child(phonenumber).child("Day");
        ch_Sunday = (CheckBox)view.findViewById(R.id.ch_Sunday);
        ch_Monday = (CheckBox)view.findViewById(R.id.ch_Monday);
        ch_Tuesday = (CheckBox)view.findViewById(R.id.ch_Tuesday);
        ch_Wednesday= (CheckBox)view.findViewById(R.id.ch_Wednesday);
        ch_Thursday = (CheckBox)view.findViewById(R.id.ch_Thursday);
        ch_Friday = (CheckBox)view.findViewById(R.id.ch_Friday);
        ch_Saturday = (CheckBox)view.findViewById(R.id.ch_Saturday);
        btn = (Button)view.findViewById(R.id.btn_next_time);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ch_Sunday.isChecked()){
                    myref.child("Sunday").setValue(1);
                }
                else{
                    myref.child("Sunday").setValue(0);
                }
                if(ch_Monday.isChecked()){
                    myref.child("Monday").setValue(1);
                }
                else{
                    myref.child("Monday").setValue(0);
                }
                if(ch_Tuesday.isChecked()){
                    myref.child("Tuesday").setValue(1);
                }
                else{
                    myref.child("Tuesday").setValue(0);
                }
                if(ch_Wednesday.isChecked()){
                    myref.child("Wednesday").setValue(1);
                }
                else{
                    myref.child("Wednesday").setValue(0);
                }
                if(ch_Thursday.isChecked()){
                    myref.child("Thurdday").setValue(1);
                }
                else{
                    myref.child("Thursday").setValue(0);
                }
                if(ch_Friday.isChecked()){
                    myref.child("Friday").setValue(1);
                }
                else{
                    myref.child("Friday").setValue(0);
                }
                if(ch_Saturday.isChecked()){
                    myref.child("Saturday").setValue(1);
                }
                else{
                    myref.child("Saturday").setValue(0);
                }
                String phonenumber = DayArgs.fromBundle(getArguments()).getPhonenumber();
                DayDirections.ActionDayToTime4 action = DayDirections.actionDayToTime4();
                action.setPhonenumber(phonenumber);
                Navigation.findNavController(v).navigate(action);
            }
        });
    }
}
