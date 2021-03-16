package com.example.chloeiot;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    private Button ButtonTask;
    private Button ButtonStory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Home</font>")); //SET TOP NAV TITLE
        // initialization
        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        //perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_notifications:
                        startActivity(new Intent(getApplicationContext(),NotificationWatering.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.navigation_home:
                        return true;
                    case R.id.navigation_setting:
                        startActivity(new Intent(getApplicationContext(),About.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }
                return false;
            }
        });

        ButtonTask=(Button)findViewById(R.id.ButtonCek);
        ButtonStory=(Button)findViewById(R.id.ButtonStory);

        ButtonTask.setOnClickListener(new View.OnClickListener() {           //KLIK TOMBOL TASK
            @Override
            public void onClick(View view) {
                openTask();
            }
        });

        ButtonStory.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                openStory();
            }
        });


    }
    public void openTask(){

        Intent intent =new Intent(this,cekManual.class);
        startActivity(intent);
    }
    public void openStory(){
        Intent intent =new Intent (this,StoryActivity.class);
        startActivity(intent);
    }
}