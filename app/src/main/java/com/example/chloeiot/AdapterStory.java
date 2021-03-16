package com.example.chloeiot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterStory extends RecyclerView.Adapter<AdapterStory.MyViewHolder> {

    ArrayList<Model> mList;
    Context context;

    public AdapterStory(Context context,ArrayList<Model> mList){

        this.mList= mList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.story_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model=mList.get(position);
        holder.judul.setText(model.getJudul());
        holder.isi.setText(model.getIsi());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView judul,isi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.judulnya);
            isi = itemView.findViewById(R.id.isian);
        }
    }
}
