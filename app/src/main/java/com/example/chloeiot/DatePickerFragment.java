package com.example.chloeiot;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chloeiot.Adapter.AdapterHistory;
import com.example.chloeiot.Model.ModelHistory;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatePickerFragment extends DialogFragment {
    public Button btApplyCalendar;
    public TextView tvHasilCalendar;
    public CalendarView cvCalendar;
    public RecyclerView recyclerView;
    public String Date;
    private FirebaseRecyclerOptions<ModelHistory> options;
    private FirebaseRecyclerAdapter<ModelHistory, AdapterHistory> adapter;
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("History");
    @Nullable


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        View v=inflater.inflate(R.layout.fragment_calendar,container,false);

        cvCalendar=v.findViewById(R.id.cvCalendar);
        btApplyCalendar = v.findViewById(R.id.calendarApply);
        tvHasilCalendar = v.findViewById(R.id.tvisiTanggal);
        recyclerView = v.findViewById(R.id.recyclerViewHistory);

        cvCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                i1+=1;
                String i2f=""+i2;
                String i1f=""+(i1);
                if(i2<10){
                    i2f="0"+i2;
                }
                if(i1<10){
                    i1f="0"+i1;
                }


                Date =i2f+"-"+i1f+"-" + i;
                tvHasilCalendar.setText(Date);
            }
        });

        btApplyCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(getContext(),HistoryActivity.class);
                intent.putExtra("date",Date);
                startActivity(intent);


            }
        });
        return v;
    }

    }

