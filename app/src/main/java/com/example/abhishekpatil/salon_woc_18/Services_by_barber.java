package com.example.abhishekpatil.salon_woc_18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Services_by_barber extends AppCompatActivity {
    private CheckBox box_haircut; private EditText rate_haircut;
    private CheckBox box_hairspa;private EditText rate_hairspa;
    private CheckBox box_haircolor;private EditText rate_haircolor;
    private CheckBox box_massage;private EditText rate_massage;
    private CheckBox box_facial;private EditText rate_facial;
    private CheckBox box_bleach;private EditText rate_bleach;
    private Button btn;

    private DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_by_barber);

        final String phone = getIntent().getStringExtra("phoneno");
        myref =  FirebaseDatabase.getInstance().getReference().child("barber").child(phone).child("services");

        box_haircut = (CheckBox)findViewById(R.id.cb_haircut); rate_haircut = (EditText)findViewById(R.id.rate_haircut);
        box_hairspa = (CheckBox)findViewById(R.id.cb_hairspa); rate_hairspa = (EditText)findViewById(R.id.rate_hairspa);
        box_haircolor = (CheckBox)findViewById(R.id.cb_haircolor); rate_haircolor = (EditText)findViewById(R.id.rate_haircolor);
        box_massage = (CheckBox)findViewById(R.id.cb_massage); rate_massage = (EditText)findViewById(R.id.rate_massage);
        box_facial = (CheckBox)findViewById(R.id.cb_facial); rate_facial = (EditText)findViewById(R.id.rate_facial);
        box_bleach = (CheckBox)findViewById(R.id.cb_bleach); rate_bleach = (EditText)findViewById(R.id.rate_bleach);

        btn  = (Button)findViewById(R.id.btn_next_services);


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

                Intent intent  = new Intent(Services_by_barber.this,Time.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
            }
        });

    }
}
