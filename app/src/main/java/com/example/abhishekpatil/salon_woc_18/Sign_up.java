package com.example.abhishekpatil.salon_woc_18;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Sign_up extends AppCompatActivity {

    private EditText mphone;
    private EditText mname;
    private EditText mcity;
    private Button mbtn;

   public  FirebaseDatabase database;
   public DatabaseReference customerref;
   public DatabaseReference barberref;
   static int exists = 0;
   private Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        database = FirebaseDatabase.getInstance();
        customerref = database.getReference().child("customer");
        barberref = database.getReference().child("barber");

        mphone = (EditText)findViewById(R.id.txt_phone);
        mname = (EditText)findViewById(R.id.txt_name);
        mcity = (EditText)findViewById(R.id.txt_city);
        mbtn = (Button)findViewById(R.id.btn_sendotp);

        signin = (Button)findViewById(R.id.btn_signin);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sign_up.this, Sign_in.class);
                startActivity(i);
            }
        });




        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phone = mphone.getText().toString();
                final String phonenumber = "+91"+phone;
                final String name = mname.getText().toString();
                final String city = mcity.getText().toString();


                customerref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child(phonenumber).exists()){
                            Toast.makeText(Sign_up.this,"user is already registered. Please sing up",Toast.LENGTH_LONG).show();
                                exists = 1;
                                String s = String.valueOf(exists);
                                Toast.makeText(Sign_up.this,s,Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                barberref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child(phonenumber).exists()){
                            Toast.makeText(Sign_up.this,"user is already registered. Please sign up",Toast.LENGTH_LONG).show();
                                exists = 1;

                        }
                        if(exists ==0){
                            Intent i = new Intent(Sign_up.this,Sign_up_verify.class);
                            i.putExtra("name" ,name);
                            i.putExtra("city",city);
                            i.putExtra("phonenumber",phonenumber);
                            startActivity(i);
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });






    }
}
