package com.example.abhishekpatil.salon_woc_18.viewModels;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.abhishekpatil.salon_woc_18.City;
import com.example.abhishekpatil.salon_woc_18.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

public class Home_view_model extends ViewModel {
    private DatabaseReference customerref = FirebaseDatabase.getInstance().getReference().child("customer");
    private DatabaseReference barberref = FirebaseDatabase.getInstance().getReference().child("barber");
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    View v;

    public View getV() {
        return v;
    }

    public void setV(View v) {
        this.v = v;
    }

    public String getPhonenumber() {
        final String phonenumber = user.getPhoneNumber().substring(1);
        return phonenumber;
    }

    public int finduser() {

        if (user == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public DatabaseReference getBarberref() {
        return barberref;
    }

    public DatabaseReference getCustomerref() {
        return customerref;
    }

    public void setdetail(String phonenumber, String city, String name) {
        City.setCity(city);
        City.setPhonenumber(phonenumber);
        City.setName(name);
    }

    public void setInitialData() {
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String today = day + month + year;
        for (int i = 1; i <= 13; i++) {

            String s = String.valueOf(i);
            s = "time" + s;
            barberref.child(getPhonenumber()).child(today).child(s).child("name").setValue("----");
            barberref.child(getPhonenumber()).child(today).child(s).child("service").setValue("----");
            barberref.child(getPhonenumber()).child(today).child(s).child("status").setValue("0");
            barberref.child(getPhonenumber()).child(today).child(s).child("phonenumber").setValue("0");
            City.setPhonenumber(getPhonenumber());
        }

    }

    public void navigateCustomer() {
        Navigation.findNavController(getV()).navigate(R.id.action_home_to_customer_main, null, new NavOptions.Builder()
                .setPopUpTo(R.id.home, true)
                .build());
    }

    public void navigateBarber() {
        Navigation.findNavController(getV()).navigate(R.id.action_home_to_barber_main, null, new NavOptions.Builder()
                .setPopUpTo(R.id.home, true)
                .build());
    }

    public void navigateSignup() {
        NavOptions navOptions = new NavOptions.Builder()
                .setPopUpTo(R.id.home, true)
                .build();
        Navigation.findNavController(getV()).navigate(R.id.action_home_to_sign_up, null, navOptions);
    }

}
