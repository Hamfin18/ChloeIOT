package com.example.chloeiot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TargetStory extends AppCompatActivity {

    TextView judulnya,isinya;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_story);

        judulnya=(TextView)findViewById(R.id.isinya);
        isinya=(TextView)findViewById(R.id.judulnya);

        ref = FirebaseDatabase.getInstance().getReference().child("Artikel");

        String key=getIntent().getStringExtra("key");

        ref.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String judul = snapshot.child("judul").getValue().toString();
                String isi  = snapshot.child("isi").getValue().toString();

                judulnya.setText(judul);
                isinya.setText(isi);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}