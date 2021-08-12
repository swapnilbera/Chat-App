package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;
import androidx.appcompat.widget.Toolbar;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;

public class Register_Activity extends AppCompatActivity {
     private EditText display_name,email,password;
     private Button create_account;
    private FirebaseAuth mAuth;
    private Toolbar mtoolbar;
   ProgressDialog mproggesbar;
DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        display_name=findViewById(R.id.dis_name);
        email=findViewById(R.id.mail);
        password=findViewById(R.id.pass);
        create_account=findViewById(R.id.creat_acc);
        mAuth = FirebaseAuth.getInstance();
        mtoolbar=findViewById(R.id.register_toolbar);
        setSupportActionBar(mtoolbar);
        mproggesbar=new ProgressDialog(this);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nick_name=display_name.getText().toString();
                String mail_acc=email.getText().toString();
                String pass_acc=password.getText().toString();
                if(!TextUtils.isEmpty(nick_name)||!TextUtils.isEmpty(mail_acc)||!TextUtils.isEmpty(pass_acc)) {
                    mproggesbar.setTitle("Registering User");
                    mproggesbar.setMessage("Please Wait while we Creating!");
                    mproggesbar.setCanceledOnTouchOutside(false);
                    mproggesbar.show();
                    reg_user(nick_name, mail_acc, pass_acc);
                }

            }
        });
    }

    private void reg_user(String nick_name, String mail_acc, String pass_acc) {
        mAuth.createUserWithEmailAndPassword(mail_acc,pass_acc).addOnCompleteListener(task ->{
          //  @Override
           // public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                    String uid=user.getUid();
                    database=FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
                    HashMap<String,String> map=new HashMap<>();
                    map.put("name",nick_name);
                    map.put("status","Hi ! There I Am Using Chat App.");
                    map.put("image","default");
                    map.put("thumb image","default");
                   database.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                            if(task.isSuccessful()){
                             mproggesbar.dismiss();
                                Toast.makeText(Register_Activity.this, "Account Created!", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Register_Activity.this,MainActivity.class);
                               intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(Register_Activity.this, "A error is occured", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else{
                    mproggesbar.hide();
                    Toast.makeText(Register_Activity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
      //      }
        });

    }
}