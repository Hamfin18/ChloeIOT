package com.example.chloeiot;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TaskActivity extends AppCompatActivity {
        private Button buttCekSuhu,buttSiram ;
        private ProgressBar progBar;
        private TextView angka_progress,soil_moisture;
        DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //bikin tombol back

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Task</font>")); //SET TOP NAV TITLE

        buttCekSuhu =(Button)findViewById(R.id.buttCekHumidity);
        progBar =(ProgressBar)findViewById(R.id.progress_bar);
        angka_progress=(TextView)findViewById(R.id.angka_progress);
        soil_moisture =(TextView)findViewById(R.id.soil_moisture);
        buttSiram=(Button)findViewById(R.id.buttSiram);


        buttCekSuhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                                   //ON CLICK BUTTON SCAN

                if(isConnected(TaskActivity.this) == false){
                    angka_progress.setText("No Internet");
                }else{
                    angka_progress.setText("Loading");
                    setKata();
                }
            }

        });
        buttSiram.setOnClickListener(new View.OnClickListener() {                       //ON CLICK SIRAM
            @Override
            public void onClick(View view) {
                
                reff= FirebaseDatabase.getInstance().getReference().child("InWatering").child("Value");

                reff.setValue(2);

                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        final String Status=dataSnapshot.getValue().toString();
                        if (Status.equals("2")){
                            Context context = getApplicationContext();
                            CharSequence text = "Sedang Menyiram";
                            Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (Status.equals("1")){
                                    Context context = getApplicationContext();
                                    CharSequence text = "Penyimaran Berhasil";
                                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                                    toast.show();
                                }else{
                                    Context context = getApplicationContext();
                                    CharSequence text = "Penyiraman gagal";
                                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            }
                        }, 5000);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
            }

        public void setKata(){                                                                          //AMBIL DATA DARI FIREBASE

            reff= FirebaseDatabase.getInstance().getReference().child("DRealtime");
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String Suhu = dataSnapshot.child("humidity").getValue().toString();
//
                    progBar.setProgress(Integer.valueOf(Suhu));
                    angka_progress.setText(Suhu+"%");
                    soil_moisture.setText("Soil Dryness");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    angka_progress.setText("ERROR");
                    soil_moisture.setText("No Internet");

                }
            });

        }

    public boolean onOptionsItemSelected(MenuItem item) {   // ADD BACK BUTTON
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {     // ADD BACK BUTTON
        return true;
    }

    private boolean isConnected(TaskActivity taskActivity){
        ConnectivityManager connectivityManager = (ConnectivityManager) taskActivity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return (wifiConn !=null &&wifiConn.isConnected()) || (mobileConn !=null &&mobileConn.isConnected());
    }



}

