package com.example.abhishekpatil.salon_woc_18;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abhishekpatil.salon_woc_18.viewModels.Tomorrow_view_model;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Observable;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

public class Tomorrow extends Fragment {
    private DatabaseReference myref;

    private String phonenumber;
    private Switch switch1;
    private TextView name1;
    private TextView service1;
    private Switch switch2;
    private TextView name2;
    private TextView service2;
    private Switch switch3;
    private TextView name3;
    private TextView service3;
    private Switch switch4;
    private TextView name4;
    private TextView service4;
    private Switch switch5;
    private TextView name5;
    private TextView service5;
    private Switch switch6;
    private TextView name6;
    private TextView service6;
    private Switch switch7;
    private TextView name7;
    private TextView service7;
    private Switch switch8;
    private TextView name8;
    private TextView service8;
    private Switch switch9;
    private TextView name9;
    private TextView service9;
    private Switch switch10;
    private TextView name10;
    private TextView service10;
    private Switch switch11;
    private TextView name11;
    private TextView service11;
    private Switch switch12;
    private TextView name12;
    private TextView service12;
    private Switch switch13;
    private TextView name13;
    private TextView service13;
    private Button migrate;
    private TextView phone1;
    private TextView phone2;
    private TextView phone3;
    private TextView phone4;
    private TextView phone5;
    private TextView phone6;
    private TextView phone7;
    private TextView phone8;
    private TextView phone9;
    private TextView phone10;
    private TextView phone11;
    private TextView phone12;
    private TextView phone13;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tomorrow, container, false);
        phonenumber = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().substring(1);

//        logout = (Button) view.findViewById(R.id.btn_logout_barber2);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

        String date = day + month + year;


        myref = FirebaseDatabase.getInstance().getReference().child("barber").child(phonenumber).child(date);

        switch1 = (Switch) view.findViewById(R.id.tomorrow_switch1);
        name1 = (TextView) view.findViewById(R.id.tomorrow_status_time1);
        service1 = (TextView) view.findViewById(R.id.tomorrow_service_time1);
        switch2 = (Switch) view.findViewById(R.id.tomorrow_switch2);
        name2 = (TextView) view.findViewById(R.id.tomorrow_status_time2);
        service2 = (TextView) view.findViewById(R.id.tomorrow_service_time2);
        switch3 = (Switch) view.findViewById(R.id.tomorrow_switch3);
        name3 = (TextView) view.findViewById(R.id.tomorrow_status_time3);
        service3 = (TextView) view.findViewById(R.id.tomorrow_service_time3);
        switch4 = (Switch) view.findViewById(R.id.tomorrow_switch4);
        name4 = (TextView) view.findViewById(R.id.tomorrow_status_time4);
        service4 = (TextView) view.findViewById(R.id.tomorrow_service_time4);
        switch5 = (Switch) view.findViewById(R.id.tomorrow_switch5);
        name5 = (TextView) view.findViewById(R.id.tomorrow_status_time5);
        service5 = (TextView) view.findViewById(R.id.tomorrow_service_time5);
        switch6 = (Switch) view.findViewById(R.id.tomorrow_switch6);
        name6 = (TextView) view.findViewById(R.id.tomorrow_status_time6);
        service6 = (TextView) view.findViewById(R.id.tomorrow_service_time6);
        switch7 = (Switch) view.findViewById(R.id.tomorrow_switch7);
        name7 = (TextView) view.findViewById(R.id.tomorrow_status_time7);
        service7 = (TextView) view.findViewById(R.id.tomorrow_service_time7);
        switch8 = (Switch) view.findViewById(R.id.tomorrow_switch8);
        name8 = (TextView) view.findViewById(R.id.tomorrow_status_time8);
        service8 = (TextView) view.findViewById(R.id.tomorrow_service_time8);
        switch9 = (Switch) view.findViewById(R.id.tomorrow_switch9);
        name9 = (TextView) view.findViewById(R.id.tomorrow_status_time9);
        service9 = (TextView) view.findViewById(R.id.tomorrow_service_time9);
        switch10 = (Switch) view.findViewById(R.id.tomorrow_switch10);
        name10 = (TextView) view.findViewById(R.id.tomorrow_status_time10);
        service10 = (TextView) view.findViewById(R.id.tomorrow_service_time10);
        switch11 = (Switch) view.findViewById(R.id.tomorrow_switch11);
        name11 = (TextView) view.findViewById(R.id.tomorrow_status_time11);
        service11 = (TextView) view.findViewById(R.id.tomorrow_service_time11);
        switch12 = (Switch) view.findViewById(R.id.tomorrow_switch12);
        name12 = (TextView) view.findViewById(R.id.tomorrow_status_time12);
        service12 = (TextView) view.findViewById(R.id.tomorrow_service_time12);
        switch13 = (Switch) view.findViewById(R.id.tomorrow_switch13);
        name13 = (TextView) view.findViewById(R.id.tomorrow_status_time13);
        service13 = (TextView) view.findViewById(R.id.tomorrow_service_time13);
        migrate = (Button) view.findViewById(R.id.btn_today);
        phone1 = (TextView) view.findViewById(R.id.tomorrow_phone_time1);
        phone2 = (TextView) view.findViewById(R.id.tomorrow_phone_time2);
        phone3 = (TextView) view.findViewById(R.id.tomorrow_phone_time3);
        phone4 = (TextView) view.findViewById(R.id.tomorrow_phone_time4);
        phone5 = (TextView) view.findViewById(R.id.tomorrow_phone_time5);
        phone6 = (TextView) view.findViewById(R.id.tomorrow_phone_time6);
        phone7 = (TextView) view.findViewById(R.id.tomorrow_phone_time7);
        phone8 = (TextView) view.findViewById(R.id.tomorrow_phone_time8);
        phone9 = (TextView) view.findViewById(R.id.tomorrow_phone_time9);
        phone10 = (TextView) view.findViewById(R.id.tomorrow_phone_time10);
        phone11 = (TextView) view.findViewById(R.id.tomorrow_phone_time11);
        phone12 = (TextView) view.findViewById(R.id.tomorrow_phone_time12);
        phone13 = (TextView) view.findViewById(R.id.tomorrow_phone_time13);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        migrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.tomorrow, true)
                        .build();
                Navigation.findNavController(getView()).navigate(R.id.action_tomorrow_to_barber_main, null, navOptions);
            }
        });

        Tomorrow_view_model view_model = ViewModelProviders.of(this).get(Tomorrow_view_model.class);
        LiveData<DataSnapshot> liveData = view_model.getDataSnapshotLiveDatatomorrow();
        liveData.observe(this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(@Nullable DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    if (dataSnapshot.child("time1").child("status").getValue().toString().equals("2")) {
                        switch1.setChecked(true);
                        switch1.setVisibility(View.INVISIBLE);
                        name1.setText(dataSnapshot.child("time1").child("name").getValue().toString());
                        service1.setText(dataSnapshot.child("time1").child("service").getValue().toString());
                        phone1.setText(dataSnapshot.child("time1").child("phonenumber").getValue().toString());
                    } else if (dataSnapshot.child("time1").child("status").getValue().toString().equals("1")) {
                        switch1.setChecked(true);
                        service1.setText("Available");
                        name1.setText(" ");
                        phone1.setText(" ");
                    } else {
                        switch1.setChecked(false);
                        service1.setText("Closed");
                        name1.setText(" ");
                        phone1.setText(" ");
                    }
                    if (dataSnapshot.child("time2").child("status").getValue().toString().equals("2")) {
                        switch2.setChecked(true);
                        switch2.setVisibility(View.INVISIBLE);
                        name2.setText(dataSnapshot.child("time2").child("name").getValue().toString());
                        service2.setText(dataSnapshot.child("time2").child("service").getValue().toString());
                        phone2.setText(dataSnapshot.child("time2").child("phonenumber").getValue().toString());
                    } else if (dataSnapshot.child("time2").child("status").getValue().toString().equals("1")) {
                        switch2.setChecked(true);
                        service2.setText("Available");
                        name2.setText(" ");
                        phone2.setText(" ");
                    } else {
                        switch2.setChecked(false);
                        service2.setText("Closed");
                        name2.setText(" ");
                        phone2.setText(" ");
                    }
                    if (dataSnapshot.child("time3").child("status").getValue().toString().equals("2")) {
                        switch3.setChecked(true);
                        switch3.setVisibility(View.INVISIBLE);
                        name3.setText(dataSnapshot.child("time3").child("name").getValue().toString());
                        service3.setText(dataSnapshot.child("time3").child("service").getValue().toString());
                        phone3.setText(dataSnapshot.child("time3").child("phonenumber").getValue().toString());
                    } else if (dataSnapshot.child("time3").child("status").getValue().toString().equals("1")) {
                        switch3.setChecked(true);
                        service3.setText("Available");
                        name3.setText(" ");
                        phone3.setText(" ");
                    } else {
                        switch3.setChecked(false);
                        service3.setText("Closed");
                        name3.setText(" ");
                        phone3.setText(" ");
                    }
                    if (dataSnapshot.child("time4").child("status").getValue().toString().equals("2")) {
                        switch4.setChecked(true);
                        switch4.setVisibility(View.INVISIBLE);
                        name4.setText(dataSnapshot.child("time4").child("name").getValue().toString());
                        service4.setText(dataSnapshot.child("time4").child("service").getValue().toString());
                        phone4.setText(dataSnapshot.child("time4").child("phonenumber").getValue().toString());
                    } else if (dataSnapshot.child("time4").child("status").getValue().toString().equals("1")) {
                        switch4.setChecked(true);
                        service4.setText("Available");
                        name4.setText(" ");
                        phone4.setText(" ");
                    } else {
                        switch4.setChecked(false);
                        service4.setText("Closed");
                        name4.setText(" ");
                        phone4.setText(" ");
                    }
                    if (dataSnapshot.child("time5").child("status").getValue().toString().equals("2")) {
                        switch5.setChecked(true);
                        switch5.setVisibility(View.INVISIBLE);
                        name5.setText(dataSnapshot.child("time5").child("name").getValue().toString());
                        service5.setText(dataSnapshot.child("time5").child("service").getValue().toString());
                        phone5.setText(dataSnapshot.child("time5").child("phonenumber").getValue().toString());
                    } else if (dataSnapshot.child("time5").child("status").getValue().toString().equals("1")) {
                        switch5.setChecked(true);
                        service5.setText("Available");
                        name5.setText(" ");
                        phone5.setText(" ");
                    } else {
                        switch5.setChecked(false);
                        service5.setText("Closed");
                        name5.setText(" ");
                        phone5.setText(" ");
                    }
                    if (dataSnapshot.child("time6").child("status").getValue().toString().equals("2")) {
                        switch6.setChecked(true);
                        switch6.setVisibility(View.INVISIBLE);
                        name6.setText(dataSnapshot.child("time6").child("name").getValue().toString());
                        service6.setText(dataSnapshot.child("time6").child("service").getValue().toString());
                        phone6.setText(dataSnapshot.child("time6").child("phonenumber").getValue().toString());
                    } else if (dataSnapshot.child("time6").child("status").getValue().toString().equals("1")) {
                        switch6.setChecked(true);
                        service6.setText("Available");
                        name6.setText(" ");
                        phone6.setText(" ");
                    } else {
                        switch6.setChecked(false);
                        service6.setText("Closed");
                        name6.setText(" ");
                        phone6.setText(" ");
                    }
                    if (dataSnapshot.child("time7").child("status").getValue().toString().equals("2")) {
                        switch7.setChecked(true);
                        switch7.setVisibility(View.INVISIBLE);
                        name7.setText(dataSnapshot.child("time7").child("name").getValue().toString());
                        service7.setText(dataSnapshot.child("time7").child("service").getValue().toString());
                        phone7.setText(dataSnapshot.child("time7").child("phonenumber").getValue().toString());
                    } else if (dataSnapshot.child("time7").child("status").getValue().toString().equals("1")) {
                        switch7.setChecked(true);
                        service7.setText("Available");
                        name7.setText(" ");
                        phone7.setText(" ");
                    } else {
                        switch7.setChecked(false);
                        service7.setText("Closed");
                        name7.setText(" ");
                        phone7.setText(" ");
                    }
                    if (dataSnapshot.child("time8").child("status").getValue().toString().equals("2")) {
                        switch8.setChecked(true);
                        switch8.setVisibility(View.INVISIBLE);
                        name8.setText(dataSnapshot.child("time8").child("name").getValue().toString());
                        service8.setText(dataSnapshot.child("time8").child("service").getValue().toString());
                        phone8.setText(dataSnapshot.child("time8").child("phonenumber").getValue().toString());
                    } else if (dataSnapshot.child("time8").child("status").getValue().toString().equals("1")) {
                        switch8.setChecked(true);
                        service8.setText("Available");
                        name8.setText(" ");
                        phone8.setText(" ");
                    } else {
                        switch8.setChecked(false);
                        service8.setText("Closed");
                        name8.setText(" ");
                        phone8.setText(" ");
                    }
                    if (dataSnapshot.child("time9").child("status").getValue().toString().equals("2")) {
                        switch9.setChecked(true);
                        switch9.setVisibility(View.INVISIBLE);
                        name9.setText(dataSnapshot.child("time9").child("name").getValue().toString());
                        service9.setText(dataSnapshot.child("time9").child("service").getValue().toString());
                        phone9.setText(dataSnapshot.child("time9").child("phonenumber").getValue().toString());
                    } else if (dataSnapshot.child("time9").child("status").getValue().toString().equals("1")) {
                        switch9.setChecked(true);
                        service9.setText("Available");
                        name9.setText(" ");
                        phone9.setText(" ");
                    } else {
                        switch9.setChecked(false);
                        service9.setText("Closed");
                        name9.setText(" ");
                        phone9.setText(" ");
                    }
                    if (dataSnapshot.child("time10").child("status").getValue().toString().equals("2")) {
                        switch10.setChecked(true);
                        switch10.setVisibility(View.INVISIBLE);
                        name10.setText(dataSnapshot.child("time10").child("name").getValue().toString());
                        service10.setText(dataSnapshot.child("time10").child("service").getValue().toString());
                        phone10.setText(dataSnapshot.child("time10").child("phonenumber").getValue().toString());
                    } else if (dataSnapshot.child("time10").child("status").getValue().toString().equals("1")) {
                        switch10.setChecked(true);
                        service10.setText("Available");
                        name10.setText(" ");
                        phone10.setText(" ");
                    } else {
                        switch10.setChecked(false);
                        service10.setText("Closed");
                        name10.setText(" ");
                        phone10.setText(" ");
                    }
                    if (dataSnapshot.child("time11").child("status").getValue().toString().equals("2")) {
                        switch11.setChecked(true);
                        switch11.setVisibility(View.INVISIBLE);
                        name11.setText(dataSnapshot.child("time11").child("name").getValue().toString());
                        service11.setText(dataSnapshot.child("time11").child("service").getValue().toString());
                        phone11.setText(dataSnapshot.child("time11").child("phonenumber").getValue().toString());
                    } else if (dataSnapshot.child("time11").child("status").getValue().toString().equals("1")) {
                        switch11.setChecked(true);
                        service11.setText("Available");
                        name11.setText(" ");
                        phone11.setText(" ");
                    } else {
                        switch11.setChecked(false);
                        service11.setText("Closed");
                        name11.setText(" ");
                        phone11.setText(" ");
                    }
                    if (dataSnapshot.child("time12").child("status").getValue().toString().equals("2")) {
                        switch12.setChecked(true);
                        switch12.setVisibility(View.INVISIBLE);
                        name12.setText(dataSnapshot.child("time12").child("name").getValue().toString());
                        service12.setText(dataSnapshot.child("time12").child("service").getValue().toString());
                        phone12.setText(dataSnapshot.child("time12").child("phonenumber").getValue().toString());
                    } else if (dataSnapshot.child("time12").child("status").getValue().toString().equals("1")) {
                        switch12.setChecked(true);
                        service12.setText("Available");
                        name12.setText(" ");
                        phone12.setText(" ");
                    } else {
                        switch12.setChecked(false);
                        service12.setText("Closed");
                        name12.setText(" ");
                        phone12.setText(" ");
                    }
                    if (dataSnapshot.child("time13").child("status").getValue().toString().equals("2")) {
                        switch13.setChecked(true);
                        switch13.setVisibility(View.INVISIBLE);
                        name13.setText(dataSnapshot.child("time13").child("name").getValue().toString());
                        service13.setText(dataSnapshot.child("time13").child("service").getValue().toString());
                        phone13.setText(dataSnapshot.child("time13").child("phonenumber").getValue().toString());
                    } else if (dataSnapshot.child("time13").child("status").getValue().toString().equals("1")) {
                        switch13.setChecked(true);
                        service13.setText("Available");
                        name13.setText(" ");
                        phone13.setText(" ");
                    } else {
                        switch13.setChecked(false);
                        service13.setText("Closed");
                        name13.setText(" ");
                        phone13.setText(" ");
                    }
                }
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time1").child("status").setValue("1");
                } else {
                    myref.child("time1").child("status").setValue("0");
                }
            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time2").child("status").setValue("1");
                } else {
                    myref.child("time2").child("status").setValue("0");
                }
            }
        });
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time3").child("status").setValue("1");
                } else {
                    myref.child("time3").child("status").setValue("0");
                }
            }
        });
        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time4").child("status").setValue("1");
                } else {
                    myref.child("time4").child("status").setValue("0");
                }
            }
        });
        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time5").child("status").setValue("1");
                } else {
                    myref.child("time5").child("status").setValue("0");
                }
            }
        });
        switch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time6").child("status").setValue("1");
                } else {
                    myref.child("time6").child("status").setValue("0");
                }
            }
        });
        switch7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time7").child("status").setValue("1");
                } else {
                    myref.child("time7").child("status").setValue("0");
                }
            }
        });
        switch8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time8").child("status").setValue("1");
                } else {
                    myref.child("time8").child("status").setValue("0");
                }
            }
        });
        switch9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time9").child("status").setValue("1");
                } else {
                    myref.child("time9").child("status").setValue("0");
                }
            }
        });
        switch10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time10").child("status").setValue("1");
                } else {
                    myref.child("time10").child("status").setValue("0");
                }
            }
        });
        switch11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time11").child("status").setValue("1");
                } else {
                    myref.child("time11").child("status").setValue("0");
                }
            }
        });
        switch12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time12").child("status").setValue("1");
                } else {
                    myref.child("time12").child("status").setValue("0");
                }
            }
        });
        switch13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myref.child("time13").child("status").setValue("1");
                } else {
                    myref.child("time13").child("status").setValue("0");
                }
            }
        });


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_barber, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.barber_menu_edit_profile:
                TomorrowDirections.ActionTomorrowToEditProfile action = TomorrowDirections.actionTomorrowToEditProfile();
                action.setPhonenumber(phonenumber);
                action.setType(1);
                Navigation.findNavController(getView()).navigate(action);
                return true;
            case R.id.barber_menu_edit_rates:
                TomorrowDirections.ActionTomorrowToEditRates action1 = TomorrowDirections.actionTomorrowToEditRates();
                action1.setPhonenumber(phonenumber);
                Navigation.findNavController(getView()).navigate(action1);
                return true;
            case R.id.barber_menu_logout:
                FirebaseAuth.getInstance().signOut();
                Navigation.findNavController(getView()).navigate(R.id.action_tomorrow_to_home);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
