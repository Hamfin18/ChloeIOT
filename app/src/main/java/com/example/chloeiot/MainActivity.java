package com.example.chloeiot;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static int Jeda = 2000;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //DELAY 
    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {

            Intent intent =new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            //animasi fade in fade out
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            finish();
        }
    },Jeda);
    }
}