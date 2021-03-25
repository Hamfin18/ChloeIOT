package com.example.chloeiot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HelpActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Pertanyaan");
    private Button feedbackButton;
    private FirebaseRecyclerOptions<ModelHelp> options;
    private FirebaseRecyclerAdapter<ModelHelp,AdapterAbout> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //bikin tombol back
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'></font>")); //SET TOP NAV TITLE

        feedbackButton =(Button)findViewById(R.id.feedback_help);
        recyclerView =(RecyclerView)findViewById(R.id.recyclerViewHelp);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        SET TERIMA DATA TERBALIK
        recyclerView.setLayoutManager(linearLayoutManager);

//        AMBIL DATA DARI FIREBASE
        options = new FirebaseRecyclerOptions.Builder<ModelHelp>().setQuery(db, ModelHelp.class).build();
        adapter =new FirebaseRecyclerAdapter<ModelHelp, AdapterAbout>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AdapterAbout holder, int position, @NonNull ModelHelp model) {

                final String key =getRef(position).getKey();
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =new Intent(getApplicationContext(),TargetHelp.class);
                        intent.putExtra("key",key);

                        startActivity(intent);
                    }
                });
                holder.TVQuestion.setText(""+ model.getPertanyaan());
            }

            @NonNull
            @Override
            public AdapterAbout onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.help_item,parent,false);

                return new AdapterAbout(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
//        BUKA EMAIL
        feedbackButton.setOnClickListener(new View.OnClickListener() {
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