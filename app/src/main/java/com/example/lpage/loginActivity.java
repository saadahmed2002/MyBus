package com.example.lpage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class loginActivity extends AppCompatActivity {
    private TextView backtoreg;
    private EditText inputuseremail,inputuserpassword;
    private Button buttonregister;
    ProgressDialog progressDialog;
    FirebaseAuth bAuth;
    FirebaseUser buser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        backtoreg = findViewById(R.id.backtoreg);
        backtoreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(loginActivity.this,RegisterActivity.class);
                startActivity(i2);
            }
        });
    }
}