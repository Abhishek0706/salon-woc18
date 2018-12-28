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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

public class Sign_up extends Fragment {

    private EditText mphone;
    private EditText mname;
    private EditText fulladdress;
    private Button sendotp;
    public FirebaseDatabase database;
    public DatabaseReference customerref;
    public DatabaseReference barberref;
    static int exists = 0;
    private Spinner spinner;
    private TextView signin;
    private ProgressBar pb;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        database = FirebaseDatabase.getInstance();
        customerref = database.getReference().child("customer");
        barberref = database.getReference().child("barber");
        spinner = (Spinner)view.findViewById(R.id.spinner);
        mphone = (EditText) view.findViewById(R.id.txt_phone);
        mname = (EditText) view.findViewById(R.id.txt_name);

        sendotp = (Button) view.findViewById(R.id.btn_sendotp);
        signin = (TextView) view.findViewById(R.id.text_signin);
        fulladdress = (EditText) view.findViewById(R.id.txt_fulladdress);

        pb = (ProgressBar)view.findViewById(R.id.progress_signup);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        spinner.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,Cities.cityname));

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                if(mphone.length()!=10){
                    mphone.setError("Enter a valid phone number");
                    mphone.requestFocus();
                    return;
                }
                if(mname.length()<1){
                    mname.setError("Enter name");
                    mname.requestFocus();
                    return;
                }

                pb.setVisibility(View.VISIBLE);
                final String phonenumber = "91" + mphone.getText().toString();
                final String name = mname.getText().toString();
                final String city = Cities.cityname[spinner.getSelectedItemPosition()].trim().toLowerCase();


                customerref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(phonenumber).exists()) {
                            Toast.makeText(getContext(), "user is already registered. Please sing up", Toast.LENGTH_LONG).show();
                            exists = 1;

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                barberref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(phonenumber).exists()) {
                            Toast.makeText(getContext(), "user is already registered. Please sign up", Toast.LENGTH_LONG).show();
                            exists = 1;

                        }
                        if (exists == 0) {
                            String fulladd = fulladdress.getText().toString();

                            Sign_upDirections.ActionSignUpToSignUpVerify action = Sign_upDirections.actionSignUpToSignUpVerify();
                            action.setPhonenumber(phonenumber);
                            action.setName(name);
                            action.setCity(city);
                            action.setFulladdress(fulladd);
                            NavOptions navOptions = new NavOptions.Builder()
                                    .setPopUpTo(R.id.sign_up, true)
                                    .build();
                            Navigation.findNavController(v).navigate(action,navOptions);

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sign_upDirections.ActionSignUpToSignIn action = Sign_upDirections.actionSignUpToSignIn();
                Navigation.findNavController(v).navigate(action);
            }
        });


    }
}
