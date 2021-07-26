package com.example.chloeiot;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
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
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TaskActivity extends AppCompatActivity {
        private NotificationManagerCompat notificationManager;
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel =new NotificationChannel("my notification","my notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

//        notifSuhu();

        buttCekSuhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                                   //ON CLICK BUTTON SCAN
                angka_progress.setText("Loading");
                setKata();

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

                        String Status=dataSnapshot.getValue().toString();
                        if (Status.equals("2")){
                            Context context = getApplicationContext();
                            CharSequence text = "Sedang Menyiram";
                            Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        if (Status.equals("1")){
                            Context context = getApplicationContext();
                            CharSequence text = "selesai";
                            Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                            toast.show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                        angka_progress.setText("ERROR");
//                        soil_moisture.setText("No Internet");
                    }
                });

            }
        });
            }

//        public void notifSuhu(){
//        reff = FirebaseDatabase.getInstance().getReference().child("DRealtime");
//        reff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String Suhu = snapshot.child("humidity").getValue().toString();
//
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        }

        public void setKata(){                                                                          //AMBIL DATA DARI FIREBASE
            reff= FirebaseDatabase.getInstance().getReference().child("DRealtime");
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String Suhu=dataSnapshot.child("humidity").getValue().toString();
//                    if (Suhu.equals("90")){
//                        NotificationCompat.Builder builder =new NotificationCompat.Builder(TaskActivity.this,"my notification");
//                        builder.setContentTitle("Text");
//                        builder.setContentText("Hayo siram");
//                        builder.setSmallIcon(R.drawable.notification);
//                        builder.setAutoCancel(true);
//                    }


                    progBar.setProgress(Integer.valueOf(Suhu));
                    angka_progress.setText(Suhu+"%");
                    soil_moisture.setText("Soil Moisture");
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
    }

