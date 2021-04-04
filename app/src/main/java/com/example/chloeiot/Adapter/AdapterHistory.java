package com.example.chloeiot.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chloeiot.R;

public class AdapterHistory extends RecyclerView.ViewHolder {

    public TextView tvDate,tvTime,tvSoilMoisture;
    public View view;

    public AdapterHistory(@NonNull View itemView) {
        super(itemView);

        tvDate=itemView.findViewById(R.id.date);
        tvTime=itemView.findViewById(R.id.time);
        tvSoilMoisture=itemView.findViewById(R.id.soil_moisture_history);
        view= itemView;
    }
}
