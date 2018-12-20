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
    FirebaseDatabase firebaseDatabase;
    DatabaseReference customerref;
    DatabaseReference barberref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_verify,container,false);

        motp = (EditText)view.findViewById(R.id.text_otp);
        btn_verify_barber = (Button)view.findViewById(R.id.btn_verify_barber);
        btn_verify_customer = (Button)view.findViewById(R.id.btn_verify_customer);

        firebaseDatabase = FirebaseDatabase.getInstance();
        customerref = firebaseDatabase.getReference().child("customer");
        barberref = firebaseDatabase.getReference().child("barber");
        mAuth = FirebaseAuth.getInstance();


        return view;
    }



    @Override
    public void onStart() {
        super.onStart();

        Sign_up_verifyArgs args = Sign_up_verifyArgs.fromBundle(getArguments());
        String phonenumber = args.getPhonenumber();

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
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();


        }
    };

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()== true){

                    Sign_up_verifyArgs args = Sign_up_verifyArgs.fromBundle(getArguments());
                    String phonenumber = args.getPhonenumber();
                    String name = args.getName();
                    String city = args.getCity();

                    Toast.makeText(getContext(),"hello",Toast.LENGTH_LONG).show();


                    Calendar calendar = Calendar.getInstance();
                    String year = String.valueOf(calendar.get(Calendar.YEAR));
                    String month = String.valueOf(calendar.get(Calendar.MONTH));
                    String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                    String today = day+month+year;

                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.add(Calendar.DATE,1);
                    String year2 = String.valueOf(calendar1.get(Calendar.YEAR));
                    String month2 = String.valueOf(calendar1.get(Calendar.MONTH));
                    String day2 = String.valueOf(calendar1.get(Calendar.DAY_OF_MONTH));
                    String tomorrow = day2+month2+year2;


                    if(i==1){
                        customerref.child(phonenumber).child("name").setValue(name);
                        customerref.child(phonenumber).child("city").setValue(city);
                        NavOptions navOptions = new NavOptions.Builder()
                                .setPopUpTo(R.id.sign_up_verify, true)
                                .build();

                      Navigation.findNavController(getView()).navigate(R.id.action_sign_up_verify_to_customer_main,null,navOptions);


                    }
                    else{


                        barberref.child(phonenumber).child("name").setValue(name);
                        barberref.child(phonenumber).child("city").setValue(city);

                        for(int i=1;i<=13;i++){
                            String s = String.valueOf(i);
                            s = "time"+s;
                            barberref.child(phonenumber).child(today).child(s).child("name").setValue("----");
                            barberref.child(phonenumber).child(today).child(s).child("service").setValue("----");
                            barberref.child(phonenumber).child(today).child(s).child("status").setValue("0");

                        }

                        for(int i=1;i<=13;i++){
                            String s = String.valueOf(i);
                            s = "time"+s;
                            barberref.child(phonenumber).child(tomorrow).child(s).child("name").setValue("----");
                            barberref.child(phonenumber).child(tomorrow).child(s).child("service").setValue("----");
                            barberref.child(phonenumber).child(tomorrow).child(s).child("status").setValue("1");

                        }

                        Toast.makeText(getContext(),"FIRSTLY CHECK YOUR APPOINTMENTS",Toast.LENGTH_LONG);

                        Sign_up_verifyDirections.ActionSignUpVerifyToServicesByBarber action = Sign_up_verifyDirections.actionSignUpVerifyToServicesByBarber();
                        action.setPhonenumber(phonenumber);

                        Navigation.findNavController(getView()).navigate(action);

                    }
                }

            }
        });



    }
}
