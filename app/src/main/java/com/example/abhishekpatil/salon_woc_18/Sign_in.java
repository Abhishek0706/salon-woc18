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
import android.widget.TextView;
import android.widget.Toast;

import com.example.abhishekpatil.salon_woc_18.viewModels.Sign_in_view_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;


public class Sign_in extends Fragment {
    private TextView mphone;
    private Button mbtn;
    private TextView signup;
    private FirebaseDatabase database;
    private DatabaseReference customerref;
    private DatabaseReference barberref;
    private static int exist = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        mbtn = (Button) view.findViewById(R.id.btn_sendotp);
        mphone = (EditText) view.findViewById(R.id.phonenumber);
        signup = (TextView) view.findViewById(R.id.text_signup);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        final Sign_in_view_model mviewmodel = ViewModelProviders.of(this).get(Sign_in_view_model.class);
        mviewmodel.setV(getView());

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mviewmodel.navigateSignup();
            }
        });
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (mphone.length() != 10) {
                    mphone.setError("Enter code");
                    mphone.requestFocus();
                    return;
                }

                String phonenumber = "91";
                phonenumber += mphone.getText().toString();
                mviewmodel.setPhonenumber(phonenumber);

                barberref = mviewmodel.getBarberref();
                barberref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(mviewmodel.getPhonenumber()).exists()) {
                            exist = 1;

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                customerref = mviewmodel.getCustomerref();
                customerref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(mviewmodel.getPhonenumber()).exists()) {
                            exist = 1;
                        }
                        if (exist == 1) {
                            mviewmodel.navigateSigninverify();
                        } else {
                            Toast.makeText(getContext(), "No account found!!", Toast.LENGTH_LONG).show();
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
