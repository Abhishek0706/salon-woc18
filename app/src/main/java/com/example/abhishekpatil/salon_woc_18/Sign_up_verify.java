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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.abhishekpatil.salon_woc_18.viewModels.Sign_in_verify_view_model;
import com.example.abhishekpatil.salon_woc_18.viewModels.Sign_up_verify_view_model;
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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

public class Sign_up_verify extends Fragment {

    private FirebaseAuth mAuth;
    private String verificationId;
    private Button btn_verify_customer;
    private Button btn_verify_barber;
    private EditText motp;
    private static int i = 2;
    private ProgressBar pb;
    private Sign_up_verify_view_model mviewmodel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_verify, container, false);
        pb = (ProgressBar) view.findViewById(R.id.progress_signupverify);
        motp = (EditText) view.findViewById(R.id.text_otp);
        btn_verify_barber = (Button) view.findViewById(R.id.btn_verify_barber);
        btn_verify_customer = (Button) view.findViewById(R.id.btn_verify_customer);
        mAuth = FirebaseAuth.getInstance();
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        mviewmodel = ViewModelProviders.of(this).get(Sign_up_verify_view_model.class);
        mviewmodel.setV(getView());

        Sign_up_verifyArgs args = Sign_up_verifyArgs.fromBundle(getArguments());
        String phonenumber = args.getPhonenumber();
        mviewmodel.setPhonenumber(phonenumber);
        mviewmodel.setAddress(args.getFulladdress());
        mviewmodel.setCity(args.getCity());
        mviewmodel.setName(args.getName());

        sendVerificationCode("+" + phonenumber);

        btn_verify_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = motp.getText().toString();
                i = 1;
                if (otp.length() < 6) {
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
                i = 2;
                String otp = motp.getText().toString();
                if (otp.length() < 6) {
                    motp.setError("Enter code");
                    motp.requestFocus();
                    return;
                }
                verifyCode(otp);

            }
        });


    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);


    }


    private void sendVerificationCode(String number) {
        pb.setVisibility(View.VISIBLE);
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
            btn_verify_barber.setVisibility(View.VISIBLE);
            btn_verify_customer.setVisibility(View.VISIBLE);

        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                //    verifyCode(code); removed autoverification here because, it is automatially verifyinig as barber
            }
        }
        @Override
        public void onVerificationFailed(FirebaseException e) {

        }
    };

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful() == true) {
                    mviewmodel.navigatetodestination(i);
                    Toast.makeText(getContext(), "FIRSTLY CHECK YOUR APPOINTMENTS", Toast.LENGTH_LONG);
                }
                else{
                    motp.setError("Invalid Code");
                    motp.requestFocus();
                }
            }
        });


    }
}
