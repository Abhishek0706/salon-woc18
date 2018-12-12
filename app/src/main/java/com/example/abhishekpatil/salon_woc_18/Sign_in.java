package com.example.abhishekpatil.salon_woc_18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Sign_in extends AppCompatActivity {
    private TextView mphone;
    private Button mbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mbtn = (Button)findViewById(R.id.btn_sendotp);
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mphone = (EditText)findViewById(R.id.phonenumber);
                String phone = "+91";
                phone += mphone.getText().toString();
                Intent intent = new Intent(Sign_in.this, Sign_in_verify.class);
                intent.putExtra("phone",phone);
                startActivity(intent);
            }
        });
    }
}
