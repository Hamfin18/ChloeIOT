package com.example.chloeiot;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>About</font>")); //SET TOP NAV TITLE
        // initialization
        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.navigation_setting);
        //perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_notifications:
                        startActivity(new Intent(getApplicationContext(),NotificationWatering.class));
//                        overridePendingTransition(0,0);
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.navigation_home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.navigation_setting:
                        return true;
                }
                return false;
            }
        });

    }
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),Home.class));
        overridePendingTransition(0,0);

    }
}