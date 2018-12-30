package com.example.abhishekpatil.salon_woc_18.viewModels;

import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.example.abhishekpatil.salon_woc_18.City;
import com.example.abhishekpatil.salon_woc_18.R;
import com.example.abhishekpatil.salon_woc_18.Sign_upDirections;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

public class Sign_up_view_model extends ViewModel {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference customerref = database.getReference().child("customer");
    private String phonenumber;
    private String name;
    private  String city;
    private String address;
    private View v;

    public View getV() {
        return v;
    }

    public void setV(View v) {
        this.v = v;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public DatabaseReference getCustomerref() {
        return customerref;
    }

    public DatabaseReference getBarberref() {
        return barberref;
    }

    private DatabaseReference barberref= database.getReference().child("barber");

    public void setdetail(String phonenumber, String name, String city){
        City.setCity(city);
        City.setName(name);
        City.setPhonenumber(phonenumber);
        this.phonenumber = phonenumber;
        this.name = name;
        this.city = city;
    }
    public void navigateSignupverify(){

        Sign_upDirections.ActionSignUpToSignUpVerify action = Sign_upDirections.actionSignUpToSignUpVerify();
        action.setPhonenumber(getPhonenumber());
        action.setName(getName());
        action.setCity(getCity());
        action.setFulladdress(getAddress());
        NavOptions navOptions = new NavOptions.Builder()
                .setPopUpTo(R.id.sign_up, true)
                .build();
        Navigation.findNavController(v).navigate(action, navOptions);
    }
}
