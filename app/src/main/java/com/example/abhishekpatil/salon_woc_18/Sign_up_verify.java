package com.example.abhishekpatil.salon_woc_18;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class Sign_up_verify extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String verificationId;
    private Button btn_verify_customer;
    private Button btn_verify_barber;
    private EditText motp;
    private static int i = 2;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference customerref;
    DatabaseReference barberref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_verify);

        firebaseDatabase = FirebaseDatabase.getInstance();
        customerref = firebaseDatabase.getReference().child("customer");
        barberref = firebaseDatabase.getReference().child("barber");
        mAuth = FirebaseAuth.getInstance();
        motp = (EditText)findViewById(R.id.text_otp);
        btn_verify_customer = (Button)findViewById(R.id.btn_verify_customer);
        btn_verify_barber = (Button)findViewById(R.id.btn_verify_barber);

       String phonenumber = getIntent().getStringExtra("phonenumber");



        sendVerificationCode(phonenumber);

        btn_verify_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = motp.getText().toString();
                i =1;
                if(otp.length()<6){
                    motp.setError("Enter code");
                    motp.requestFocus();
                    return;
                }
                verifyCode(otp);
            }
        });
        btn_verify_barber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i =2;
                String otp = motp.getText().toString();
                if(otp.length()<6){
                    motp.setError("Enter code");
                    motp.requestFocus();
                    return;
                }
                verifyCode(otp);
            }
        });


    }
    private void verifyCode(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);



    }


    private void sendVerificationCode(String number){

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mcallbacks
        );
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mcallbacks= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId =s;

        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if(code !=null){
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(Sign_up_verify.this, e.getMessage(), Toast.LENGTH_LONG).show();


        }
    };

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()== true){
                    String name = getIntent().getStringExtra("name");
                    String city = getIntent().getStringExtra("city");
                    String phonenumber = getIntent().getStringExtra("phonenumber");


                    if(i==1){
                        customerref.child(phonenumber).child("name").setValue(name);
                        customerref.child(phonenumber).child("city").setValue(city);


                        Intent intent = new Intent(Sign_up_verify.this, Customer_main.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else{
                        barberref.child(phonenumber).child("name").setValue(name);
                        barberref.child(phonenumber).child("city").setValue(city);

                        Intent intent = new Intent(Sign_up_verify.this, Services_by_barber.class);
                        intent.putExtra("phoneno",phonenumber);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }

            }
        });

    }

}
