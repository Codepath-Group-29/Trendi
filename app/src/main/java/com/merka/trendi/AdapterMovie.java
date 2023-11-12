package com.merka.trendi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.MyViewHolder> {
    private Context mContex;
    private ArrayList<MovieClass> mData;

    public AdapterMovie(Context mContex, ArrayList<MovieClass> mData) {
        this.mContex = mContex;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater infalter = LayoutInflater.from(mContex);
        v = infalter.inflate(R.layout.item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(mData.get(position).getName());
        holder.id.setText(mData.get(position).getId());


        // Using Glide to display image

        Glide.with(mContex)
                .load(mData.get(position).getImage())
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id;
        TextView name;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id_text);
            name = itemView.findViewById(R.id.movie_name);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}
