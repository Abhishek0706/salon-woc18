package com.example.abhishekpatil.salon_woc_18.viewModels;

import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.example.abhishekpatil.salon_woc_18.R;
import com.example.abhishekpatil.salon_woc_18.Sign_inDirections;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

public class Sign_in_view_model extends ViewModel {
    View v;
    private String phonenumber;
    private DatabaseReference customerref = FirebaseDatabase.getInstance().getReference().child("customer");
    private DatabaseReference barberref = FirebaseDatabase.getInstance().getReference().child("barber");

    public DatabaseReference getCustomerref() {
        return customerref;
    }

    public DatabaseReference getBarberref() {
        return barberref;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setV(View v) {
        this.v = v;
    }

    public void navigateSignup() {
        NavOptions navOptions = new NavOptions.Builder()
                .setPopUpTo(R.id.sign_up, true)
                .build();
        Navigation.findNavController(v).navigate(R.id.action_sign_in_to_sign_up, null, navOptions);
    }

    public void navigateSigninverify() {
        Sign_inDirections.ActionSignInToSignInVerify action = Sign_inDirections.actionSignInToSignInVerify();
        action.setPhonenumber(getPhonenumber());
        NavOptions navOptions = new NavOptions.Builder()
                .setPopUpTo(R.id.sign_in, true)
                .build();
        Navigation.findNavController(v).navigate(action, navOptions);
    }
}
