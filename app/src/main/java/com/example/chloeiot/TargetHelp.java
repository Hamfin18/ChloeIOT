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

public class TargetHelp extends AppCompatActivity {

    private TextView judulHelp,isiHelp;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_help);

        judulHelp=(TextView)findViewById(R.id.judulHelp);
        isiHelp=(TextView)findViewById(R.id.isiHelp);

        ref = FirebaseDatabase.getInstance().getReference().child("Pertanyaan");

        String key=getIntent().getStringExtra("key");

        ref.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String judulhelp= snapshot.child("pertanyaan").getValue().toString();
                String isihelp =snapshot.child("isi").getValue().toString();

                judulHelp.setText(judulhelp);
                isiHelp.setText(isihelp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}