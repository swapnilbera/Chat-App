package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class Login_Activity extends AppCompatActivity {
Button do_login;
EditText get_email,get_password;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        get_email=findViewById(R.id.email);
        get_password=findViewById(R.id.password);
        do_login=findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();
        do_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=get_email.getText().toString();
                String pass=get_password.getText().toString();
                if(!TextUtils.isEmpty(mail)||!TextUtils.isEmpty(pass)){
                    loginuser(mail,pass);
                }
            }
        });
    }

    private void loginuser(String mail, String pass) {
        mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(Login_Activity.this, "Error is occured!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}