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

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;


public class Sign_in_verify extends Fragment {
    private EditText otp;
    private Button btn;
    private FirebaseAuth mAuth;
    private DatabaseReference customerref;
    private DatabaseReference barberref;
    FirebaseDatabase firebaseDatabase;
    private String verificationId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in_verify,container,false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        customerref = firebaseDatabase.getReference().child("customer");
        barberref = firebaseDatabase.getReference().child("barber");
        mAuth = FirebaseAuth.getInstance();
        otp = (EditText)view.findViewById(R.id.text_otp_signin);
        btn = (Button)view.findViewById(R.id.btn_verify_signin);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        String phonenumber = Sign_up_verifyArgs.fromBundle(getArguments()).getPhonenumber();

        sendVerificationCode("+"+phonenumber);

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
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();


        }
    };
    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()== true){

                    final String phonenumber = Sign_up_verifyArgs.fromBundle(getArguments()).getPhonenumber();

                    customerref.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if(dataSnapshot.child(phonenumber).exists()){
                                Toast.makeText(getContext(),"Hello customer",Toast.LENGTH_LONG).show();
                                NavOptions navOptions = new NavOptions.Builder()
                                        .setPopUpTo(R.id.sign_in_verify, true)
                                        .build();
                                String city= dataSnapshot.child(phonenumber).child("city").getValue().toString();
                                Sign_up_verifyDirections.ActionSignUpVerifyToCustomerMain action = Sign_up_verifyDirections.actionSignUpVerifyToCustomerMain();
                                action.setPhonenumber(phonenumber);
                                action.setCity(city);


                                Navigation.findNavController(getView()).navigate(action,navOptions);

                            }
                            else{
                                Toast.makeText(getContext(),"Hello barber",Toast.LENGTH_LONG).show();
                                NavOptions navOptions = new NavOptions.Builder()
                                        .setPopUpTo(R.id.sign_in_verify, true)
                                        .build();
                                Navigation.findNavController(getView()).navigate(R.id.action_sign_in_verify_to_barber_main,null,navOptions);
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
