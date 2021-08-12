package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class status_setting extends AppCompatActivity {
    private Toolbar mtoolbar;
    private Button save;
    private EditText put_status;
    DatabaseReference database;
    FirebaseUser currentuser;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_setting);
        currentuser= FirebaseAuth.getInstance().getCurrentUser();
        String uid=currentuser.getUid();
        database= FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        Intent intent=getIntent();
        String old_status=intent.getStringExtra("status_value");
        save=findViewById(R.id.save_status);
        put_status=findViewById(R.id.type_status);
        mtoolbar=findViewById(R.id.staus_setting_bar);
        put_status.setText(old_status);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Set Your Status");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_status=put_status.getText().toString();
                if(new_status.length()>20){
                    Toast.makeText(status_setting.this, "You Can Put Max 20 Words!", Toast.LENGTH_SHORT).show();
                }
                else{
                    progressDialog=new ProgressDialog(status_setting.this);
                    progressDialog.setTitle("Saving Changes");
                    progressDialog.setMessage("Please Wait While We Make The Changes...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                  database.child("status").setValue(new_status).addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                      public void onComplete(@NonNull @NotNull Task<Void> task) {
                     if(task.isSuccessful()){
                         progressDialog.dismiss();
                         Toast.makeText(status_setting.this, "Your Status Is Updated", Toast.LENGTH_SHORT).show();
                     }
                     else{
                         progressDialog.hide();
                         Toast.makeText(status_setting.this, "Some Unexpected Error Has Occured", Toast.LENGTH_SHORT).show();
                     }
                      }
                  });
                }

            }
        });
    }
}