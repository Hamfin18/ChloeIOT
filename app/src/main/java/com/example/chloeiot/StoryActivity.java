package com.example.chloeiot;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chloeiot.Adapter.AdapterStory;
import com.example.chloeiot.Model.ModelStory;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StoryActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Artikel");

    private FirebaseRecyclerOptions<ModelStory> options;
    private FirebaseRecyclerAdapter<ModelStory, AdapterStory> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //bikin tombol back
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Story</font>")); //SET TOP NAV TITLE

        recyclerView= findViewById(R.id.recyclerViewStory);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        SET TERIMA DATA TERBALIK
        recyclerView.setLayoutManager(linearLayoutManager);


        options = new FirebaseRecyclerOptions.Builder<  ModelStory>().setQuery(db, ModelStory.class).build();
        adapter = new FirebaseRecyclerAdapter<ModelStory, AdapterStory>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AdapterStory holder, int position, @NonNull ModelStory modelStory) {

                final String key =getRef(position).getKey();

                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =new Intent(getApplicationContext(),TargetStory.class);
                        intent.putExtra("key",key);

                        startActivity(intent);
                    }
                });
                holder.textViewJudul.setText(""+ modelStory.getJudul());
                holder.textViewIsi.setText(""+ modelStory.getIsi());
                Glide.with(getApplicationContext()).load(modelStory.getFoto()).into(holder.imageViewFoto1);

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