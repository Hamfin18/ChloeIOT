package com.example.chloeiot;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TargetHelp extends AppCompatActivity {

    private TextView judulHelp,isiHelp;
    private DatabaseReference ref;
    private Button buttonFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_help);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>FAQ</font>")); //SET TOP NAV TITLE
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //bikin tombol back

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

        buttonFeedback = (Button)findViewById(R.id.feedback_t_help);

        buttonFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Intent.ACTION_SEND);
                String[] recipients={"chloe@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Subject text here...");
                intent.putExtra(Intent.EXTRA_TEXT,"Body of the content here...");
                intent.putExtra(Intent.EXTRA_CC,"mailcc@gmail.com");
                intent.setType("text/html");
                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent, "Send mail"));

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