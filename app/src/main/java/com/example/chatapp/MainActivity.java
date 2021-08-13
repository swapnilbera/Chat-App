package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Toolbar mtoolbar;
    private Toolbar supportActionBar;
    ViewPager viewPager;
    private SectionpageAdaptor madaptor;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=findViewById(R.id.viewPager3);
        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();
        mtoolbar= findViewById(R.id.main_page_toolbar);
        tabLayout=findViewById(R.id.main_page_tab);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Chat App");
        madaptor=new SectionpageAdaptor(getSupportFragmentManager());
        viewPager.setAdapter(madaptor);
        tabLayout.setupWithViewPager(viewPager);

    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            sendTostart();
        }
    }

    private void sendTostart() {
        Intent intent = new Intent(MainActivity.this, Start_Activity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);
         if(item.getItemId()==R.id.main_logout){
           FirebaseAuth.getInstance().signOut();
             sendTostart();
         }
         else if(item.getItemId()==R.id.setting){
             Intent intent=new Intent(MainActivity.this,Setting_activity.class);
             startActivity(intent);
         }
         return true;
    }
}