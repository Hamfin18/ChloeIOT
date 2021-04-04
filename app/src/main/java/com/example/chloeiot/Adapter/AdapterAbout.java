package com.example.chloeiot.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chloeiot.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

public class AdapterAbout extends RecyclerView.ViewHolder {

   public TextView TVQuestion;
    public View view;

    public AdapterAbout(@NonNull View itemView) {
        super(itemView);

        view=itemView;
        TVQuestion=itemView.findViewById(R.id.pertanyaan);
    }

}
