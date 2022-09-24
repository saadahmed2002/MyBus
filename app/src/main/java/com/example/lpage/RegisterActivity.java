package com.example.lpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
private TextView alreadyHaveAccount;
private EditText inputusername,inputuseremail,inputuserpassword;
private  Button buttonregister;
ProgressDialog progressDialog;
FirebaseAuth bAuth;
FirebaseUser buser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);
        inputusername=findViewById(R.id.inputusername);
        inputuseremail=findViewById(R.id.inputuseremail);
        inputuserpassword=findViewById(R.id.inputuserPassword);
        buttonregister = findViewById(R.id.buttonregister);
        progressDialog = new ProgressDialog(this);
        bAuth = FirebaseAuth.getInstance();
        buser = bAuth.getCurrentUser();
        buttonregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAuth();
            }
        });



        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(RegisterActivity.this,loginActivity.class);
               startActivity(i1);
            }
        });
    }

    private void PerforAuth() {
        String emp = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String email= inputuseremail.getText().toString();
        String password = inputuserpassword.getText().toString();
        if (!email.matches(emp)){
            inputuseremail.setError("Enter Correct Email");
        }
else if (password.isEmpty()|| password.length()<6){
    inputuserpassword.setError("Enter Proper Password");
}else{
    progressDialog.setMessage("Registration...");
    progressDialog.setTitle("Registration");
    progressDialog.setCanceledOnTouchOutside(false);
    progressDialog.show();
     bAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
         @Override
         public void onComplete(@NonNull Task<AuthResult> task) {
             if(task.isSuccessful()){
                 progressDialog.dismiss();
                 sendUserToNextActivity();
                 Toast.makeText(RegisterActivity.this, "Registration done successfully", Toast.LENGTH_SHORT).show();

             }
             else{
                 progressDialog.dismiss();
                 Toast.makeText(RegisterActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
             }
         }
     });



        }
    }

    private void sendUserToNextActivity() {
        Intent intent= new Intent(RegisterActivity.this,MapsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
