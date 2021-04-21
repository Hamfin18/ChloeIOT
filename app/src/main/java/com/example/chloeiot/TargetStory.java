package com.example.chloeiot;

import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TargetStory extends AppCompatActivity {

    TextView judulnya,isinya;
    ImageView fotonya;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_story);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'></font>")); //SET TOP NAV TITLE
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //bikin tombol back

        judulnya=(TextView)findViewById(R.id.judulnya);
        isinya=(TextView)findViewById(R.id.isinya);
        fotonya=(ImageView)findViewById(R.id.fotonya);

        ref = FirebaseDatabase.getInstance().getReference().child("Artikel");

        String key=getIntent().getStringExtra("key");

        ref.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String judul = snapshot.child("judul").getValue().toString();
                String isi  = snapshot.child("isi").getValue().toString();
                String foto = snapshot.child("foto").getValue().toString();
                //set judul
                judulnya.setText(judul);
//                set isi
                isinya.setText(isi);
//                set foto
                jadi(foto);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
//        Untuk menampilkan foto dari firebase
    public  void jadi(String foto){
        Glide.with(this).load(foto).into(fotonya);
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


