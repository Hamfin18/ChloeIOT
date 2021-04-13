package com.example.chloeiot;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chloeiot.Adapter.AdapterHistory;
import com.example.chloeiot.Model.ModelHistory;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HistoryActivity extends AppCompatActivity {

    Button buttonDate,buttonReset;
    RecyclerView recyclerView;
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("History");
    private FirebaseRecyclerOptions<ModelHistory> options;
    private FirebaseRecyclerAdapter<ModelHistory, AdapterHistory> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        overridePendingTransition(0,0);
        String tanggal=getIntent().getStringExtra("date");

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>History</font>")); //SET TOP NAV TITLE

        recyclerView=(RecyclerView)findViewById(R.id.recyclerViewHistory);
        buttonDate =(Button)findViewById(R.id.buttonDate);
        buttonReset=(Button)findViewById(R.id.buttonReset);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        SET TERIMA DATA TERBALIK
        recyclerView.setLayoutManager(linearLayoutManager);

        if(tanggal!=null){
            options = new FirebaseRecyclerOptions.Builder< ModelHistory>().setQuery(db.orderByChild("date").equalTo(tanggal), ModelHistory.class).build();
            adapter = new FirebaseRecyclerAdapter<ModelHistory, AdapterHistory>(options) {
                @Override
                protected void onBindViewHolder(@NonNull AdapterHistory holder, int position, @NonNull ModelHistory modelHistory) {

                    holder.tvSoilMoisture.setText(""+modelHistory.getSoilMoisture());
                    holder.tvTime.setText(""+modelHistory.getTime());
                    holder.tvDate.setText(""+modelHistory.getDate());
                }

                @NonNull
                @Override
                public AdapterHistory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item,parent,false);

                    return new AdapterHistory(v);
                }
            };
            adapter.startListening();
            recyclerView.setAdapter(adapter);
        }else{
            options = new FirebaseRecyclerOptions.Builder< ModelHistory>().setQuery(db, ModelHistory.class).build();
            adapter = new FirebaseRecyclerAdapter<ModelHistory, AdapterHistory>(options) {
                @Override
                protected void onBindViewHolder(@NonNull AdapterHistory holder, int position, @NonNull ModelHistory modelHistory) {

//                final String key =getRef(position).getKey();

//                holder.view.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent =new Intent(getApplicationContext(),TargetStory.class);
//                        intent.putExtra("key",key);
//                        startActivity(intent);
//                    }
//                });
                    holder.tvSoilMoisture.setText(""+modelHistory.getSoilMoisture());
                    holder.tvTime.setText(""+modelHistory.getTime());
                    holder.tvDate.setText(""+modelHistory.getDate());
                }

                @NonNull
                @Override
                public AdapterHistory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item,parent,false);

                    return new AdapterHistory(v);
                }
            };
            adapter.startListening();
            recyclerView.setAdapter(adapter);
        };



        // initialization
        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.navigation_notifications);
        //perform item selected listener on navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_notifications:
                        return true;
                    case R.id.navigation_home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.navigation_setting:
                        startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }
                return false;
            }
        });
//            TOMBOL DATE
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(),"MyFragment");
            }
        });

//            TOMBOL RESET
        buttonReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                options = new FirebaseRecyclerOptions.Builder< ModelHistory>().setQuery(db, ModelHistory.class).build();
                adapter = new FirebaseRecyclerAdapter<ModelHistory, AdapterHistory>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull AdapterHistory holder, int position, @NonNull ModelHistory modelHistory) {

                        holder.tvSoilMoisture.setText(""+modelHistory.getSoilMoisture());
                        holder.tvTime.setText(""+modelHistory.getTime());
                        holder.tvDate.setText(""+modelHistory.getDate());
                    }

                    @NonNull
                    @Override
                    public AdapterHistory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item,parent,false);

                        return new AdapterHistory(v);
                    }
                };
                adapter.startListening();
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        overridePendingTransition(0,0);

    }
}