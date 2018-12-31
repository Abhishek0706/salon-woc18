package com.example.abhishekpatil.salon_woc_18;

import android.arch.lifecycle.ViewModelProviders;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.abhishekpatil.salon_woc_18.viewModels.Sign_in_verify_view_model;
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

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;


public class Sign_in_verify extends Fragment {
    private EditText otp;
    private Button btn;
    private FirebaseAuth mAuth;
    private String verificationId;
    private ProgressBar pb;
    private Sign_in_verify_view_model mviewmodel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in_verify, container, false);

        mAuth = FirebaseAuth.getInstance();
        otp = (EditText) view.findViewById(R.id.text_otp_signin);
        btn = (Button) view.findViewById(R.id.btn_verify_signin);
        pb = (ProgressBar) view.findViewById(R.id.progress_signinverify);

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        mviewmodel = ViewModelProviders.of(this).get(Sign_in_verify_view_model.class);
        mviewmodel.setV(getView());
        String phonenumber = Sign_up_verifyArgs.fromBundle(getArguments()).getPhonenumber();
        mviewmodel.setPhonenumber(phonenumber);
        sendVerificationCode("+" + phonenumber);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = otp.getText().toString();


                if (otp.length() < 6) {
                    otp.setError("Enter code");
                    otp.requestFocus();
                    return;
                }
                verifyCode(code);

            }
        });
    }

    private void verifyCode(String code) {
        pb.setVisibility(View.VISIBLE);
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }


    private void sendVerificationCode(String number) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mcallbacks
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mcallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
            btn.setVisibility(View.VISIBLE);

        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            otp.setError("Invalid Code");
            otp.requestFocus();


        }
    };

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful() == true) {
                    mviewmodel.navigatetodestination();
                }
                else{
                    Toast.makeText(getContext(),"Invalid OTP", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
