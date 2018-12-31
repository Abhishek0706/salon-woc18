package com.example.abhishekpatil.salon_woc_18.viewModels;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.abhishekpatil.salon_woc_18.City;
import com.example.abhishekpatil.salon_woc_18.R;
import com.example.abhishekpatil.salon_woc_18.Sign_in_verifyDirections;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

public class Sign_in_verify_view_model extends ViewModel {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference customerref = database.getReference().child("customer");
    private DatabaseReference barberref = database.getReference().child("barber");
    View v;
    private String phonenumber;

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public View getV() {
        return v;
    }

    public void setV(View v) {
        this.v = v;
    }

    public void navigatetodestination() {
        customerref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(getPhonenumber()).exists()) {
                    NavOptions navOptions = new NavOptions.Builder()
                            .setPopUpTo(R.id.sign_in_verify, true)
                            .build();
                    String city = dataSnapshot.child(getPhonenumber()).child("city").getValue().toString();
                    Sign_in_verifyDirections.ActionSignInVerifyToCustomerMain action = Sign_in_verifyDirections.actionSignInVerifyToCustomerMain();
                    action.setPhonenumber(getPhonenumber());
                    action.setCity(city);
                    City.setCity(city);
                    City.setPhonenumber(getPhonenumber());
                    City.setName(dataSnapshot.child(getPhonenumber()).child("name").getValue().toString());


                    Navigation.findNavController(getV()).navigate(action, navOptions);

                } else {

                    barberref.child(getPhonenumber()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Calendar calendar = Calendar.getInstance();
                            String year = String.valueOf(calendar.get(Calendar.YEAR));
                            String month = String.valueOf(calendar.get(Calendar.MONTH)+1);
                            String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                            if(day.length()==1){
                                day = "0"+day;
                            }
                            if(month.length()==1){
                                month = "0"+month;
                            }
                            String today = day + month + year;
                            if (!dataSnapshot.child(today).exists()) {
                                for (int i = 1; i <= 13; i++) {
                                    String s = String.valueOf(i);
                                    s = "time" + s;
                                    City.setPhonenumber(getPhonenumber());
                                    barberref.child(getPhonenumber()).child(today).child(s).child("name").setValue("----");
                                    barberref.child(getPhonenumber()).child(today).child(s).child("service").setValue("----");
                                    barberref.child(getPhonenumber()).child(today).child(s).child("status").setValue("0");
                                    barberref.child(getPhonenumber()).child(today).child(s).child("phonenumber").setValue("0");
                                }

                            }
                            NavOptions navOptions = new NavOptions.Builder()
                                    .setPopUpTo(R.id.sign_in_verify, true)
                                    .build();
                            Navigation.findNavController(getV()).navigate(R.id.action_sign_in_verify_to_barber_main, null, navOptions);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
