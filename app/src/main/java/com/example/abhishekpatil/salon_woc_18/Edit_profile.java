package com.example.abhishekpatil.salon_woc_18;

import android.content.Context;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Edit_profile extends Fragment {
    String phonenumber;
    int type;
    private EditText editname;
    private Spinner editCity;
    private EditText editaddress;
    private Button editbutton;
    private DatabaseReference myref;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile,container,false);
        editname = (EditText) view.findViewById(R.id.edit_name);
        editCity = (Spinner) view.findViewById(R.id.spinner_city);
        editaddress = (EditText) view.findViewById(R.id.edit_address);
        editbutton = (Button) view.findViewById(R.id.btn_edit);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


        editCity.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,Cities.cityname));
        Edit_profileArgs args = Edit_profileArgs.fromBundle(getArguments());
        phonenumber = args.getPhonenumber();
        type = args.getType();



        if(type==0){
            editaddress.setVisibility(View.INVISIBLE);
            myref = FirebaseDatabase.getInstance().getReference().child("customer").child(phonenumber);
        }
        else{
            myref = FirebaseDatabase.getInstance().getReference().child("barber").child(phonenumber);
        }

        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editname.length()==0){
                    editname.setError("Enter name");
                    editname.requestFocus();
                    return;
                }

                myref.child("name").setValue(editname.getText().toString());


                myref.child("city").setValue(Cities.cityname[editCity.getSelectedItemPosition()].trim().toLowerCase());
                if(type==1&&editaddress.length()!=0){
                    myref.child("address").setValue(editaddress.getText().toString());
                }
                editbutton.setEnabled(false);

                Toast.makeText(getContext(),"Changes will be applies next time you open the app!\n Press back to go to Home screen",Toast.LENGTH_LONG).show();
            }
        });


    }
}
