package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class Setting_activity extends AppCompatActivity {
DatabaseReference databaseReference;
FirebaseUser currentuser;
TextView name_dis,status_dis;
CircleImageView pic;
Button set_status,set_image;
private static final int GALLERY_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        name_dis=findViewById(R.id.name);
        status_dis=findViewById(R.id.status);
        pic=findViewById(R.id.circleImageView);
        set_status=findViewById(R.id.change_status);
        set_image=findViewById(R.id.change_img);
        currentuser= FirebaseAuth.getInstance().getCurrentUser();
        String uid=currentuser.getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                //Toast.makeText(Setting_activity.this, snapshot.toString(), Toast.LENGTH_SHORT).show();
                String name=snapshot.child("name").getValue().toString();
                String image=snapshot.child("image").getValue().toString();
                String status=snapshot.child("status").getValue().toString();
                String thumb_image=snapshot.child("thumb image").getValue().toString();
                name_dis.setText(name);
                status_dis.setText(status);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        set_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String statusvalue=status_dis.getText().toString();
                Intent intent=new Intent(Setting_activity.this,status_setting.class);
                intent.putExtra("status_value",statusvalue);
                startActivity(intent);
            }
        });
        set_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery=new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery,"Select Image"),GALLERY_CODE);
            }
        });
    }
}