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
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;


public class Sign_in extends Fragment {
    private TextView mphone;
    private Button mbtn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        mbtn = (Button) view.findViewById(R.id.btn_sendotp);
        mphone = (EditText) view.findViewById(R.id.phonenumber);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mphone.length()!=10){
                    mphone.setError("Enter code");
                    mphone.requestFocus();
                    return;
                }

                String phonenumber = "91";
                phonenumber += mphone.getText().toString();

                Sign_inDirections.ActionSignInToSignInVerify action = Sign_inDirections.actionSignInToSignInVerify();
                action.setPhonenumber(phonenumber);
                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.sign_in, true)
                        .build();
                Navigation.findNavController(v).navigate(action,navOptions);

            }
        });
    }
}
