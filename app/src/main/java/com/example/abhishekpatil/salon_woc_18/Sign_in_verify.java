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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class Sign_in_verify extends AppCompatActivity {
    private EditText otp;
    private Button btn;
    private FirebaseAuth mAuth;
    private DatabaseReference customerref;
    private DatabaseReference barberref;
    FirebaseDatabase firebaseDatabase;
    private String verificationId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_verify);

        firebaseDatabase = FirebaseDatabase.getInstance();
        customerref = firebaseDatabase.getReference().child("customer");
        barberref = firebaseDatabase.getReference().child("barber");
        mAuth = FirebaseAuth.getInstance();
        otp = (EditText)findViewById(R.id.text_otp_signin);
        btn = (Button)findViewById(R.id.btn_verify_signin);
        String phonenumber = getIntent().getStringExtra("phone");


        sendVerificationCode(phonenumber);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = otp.getText().toString();

                if(otp.length()<6){
                    otp.setError("Enter code");
                    otp.requestFocus();
                    return;
                }
                verifyCode(code);
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
            Toast.makeText(Sign_in_verify.this, e.getMessage(), Toast.LENGTH_LONG).show();


        }
    };
    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()== true){

                    final String phonenumber = getIntent().getStringExtra("phone");

                    customerref.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if(dataSnapshot.child(phonenumber).exists()){
                                Toast.makeText(Sign_in_verify.this,"Hello customer",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Sign_in_verify.this, Customer_main.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                            }
                            else{
                                Toast.makeText(Sign_in_verify.this,"Hello barber",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Sign_in_verify.this, Barber_main.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

            }
        });

    }

}

