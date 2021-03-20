package com.example.chloeiot;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterAbout extends RecyclerView.ViewHolder {

    TextView TVQuestion;
    View view;
    public AdapterAbout(@NonNull View itemView) {
        super(itemView);

        view=itemView;
        TVQuestion=itemView.findViewById(R.id.pertanyaan);
    }
}
