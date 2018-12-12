package com.example.abhishekpatil.salon_woc_18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TimeTime extends AppCompatActivity {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_time);

        final String phone = getIntent().getStringExtra("phone");
        myref =  FirebaseDatabase.getInstance().getReference().child("barber").child(phone).child("Time");

        time1 = (CheckBox)findViewById(R.id.time1);
        time2 = (CheckBox)findViewById(R.id.time2);
        time3 = (CheckBox)findViewById(R.id.time3);
        time4 = (CheckBox)findViewById(R.id.time4);
        time5 = (CheckBox)findViewById(R.id.time5);
        time6 = (CheckBox)findViewById(R.id.time6);
        time7 = (CheckBox)findViewById(R.id.time7);
        time8 = (CheckBox)findViewById(R.id.time8);
        time9 = (CheckBox)findViewById(R.id.time9);
        time10 = (CheckBox)findViewById(R.id.time10);
        time11 = (CheckBox)findViewById(R.id.time11);
        time12 = (CheckBox)findViewById(R.id.time12);
        time13 = (CheckBox)findViewById(R.id.time13);
        btn = (Button)findViewById(R.id.btn_next_timetime);

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
                Intent intent = new Intent(TimeTime.this,Barber_main.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });




    }
}
