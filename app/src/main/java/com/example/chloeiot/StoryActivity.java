package com.example.chloeiot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StoryActivity extends AppCompatActivity {

    TextView isiText;
    private RecyclerView recyclerView;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Artikel");

    private FirebaseRecyclerOptions<Model> options;
    private FirebaseRecyclerAdapter<Model,AdapterStory> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //bikin tombol back
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'></font>")); //SET TOP NAV TITLE
        setContentView(R.layout.activity_story);

        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(root,Model.class).build();
        adapter = new FirebaseRecyclerAdapter<Model, AdapterStory>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AdapterStory holder, int position, @NonNull Model model) {
                holder.textViewJudul.setText(""+model.getJudul());
                holder.textViewIsi.setText(""+model.getIsi());

            }

            @NonNull
            @Override
            public AdapterStory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.story_item,parent,false);

                return new AdapterStory(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }


    private void setIsi(){
        DatabaseReference reff;
        reff=FirebaseDatabase.getInstance().getReference().child("Artikel");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String kata=dataSnapshot.child("1").child("Isi").getValue().toString();
                isiText.setText(kata);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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