package com.example.abhishekpatil.salon_woc_18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Time extends AppCompatActivity {
    private DatabaseReference myref;
    private CheckBox ch_Sunday;
    private CheckBox ch_Monday;
    private CheckBox ch_Tuesday;
    private CheckBox ch_Wednesday;
    private CheckBox ch_Thursday;
    private CheckBox ch_Friday;
    private CheckBox ch_Saturday;

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        final String phone = getIntent().getStringExtra("phone");
        myref =  FirebaseDatabase.getInstance().getReference().child("barber").child(phone).child("Day");
        ch_Sunday = (CheckBox)findViewById(R.id.ch_Sunday);
        ch_Monday = (CheckBox)findViewById(R.id.ch_Monday);
        ch_Tuesday = (CheckBox)findViewById(R.id.ch_Tuesday);
        ch_Wednesday= (CheckBox)findViewById(R.id.ch_Wednesday);
        ch_Thursday = (CheckBox)findViewById(R.id.ch_Thursday);
        ch_Friday = (CheckBox)findViewById(R.id.ch_Friday);
        ch_Saturday = (CheckBox)findViewById(R.id.ch_Saturday);
        btn = (Button)findViewById(R.id.btn_next_time);

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

                Intent intent = new Intent(Time.this,TimeTime.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
            }
        });

    }
}
