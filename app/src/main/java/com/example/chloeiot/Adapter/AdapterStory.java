package com.example.chloeiot.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chloeiot.R;

import java.util.ArrayList;

public class AdapterStory extends RecyclerView.ViewHolder {

    public TextView textViewJudul,textViewIsi;
    public ImageView imageViewFotonya,imageViewFoto1;
    public View view;

    public AdapterStory(@NonNull View itemView) {
        super(itemView);

        textViewJudul=itemView.findViewById(R.id.judulnya);
        textViewIsi =itemView.findViewById(R.id.isian);
        view = itemView;
        imageViewFotonya=itemView.findViewById(R.id.fotonya);
        imageViewFoto1=itemView.findViewById(R.id.foto1);
    }
}
