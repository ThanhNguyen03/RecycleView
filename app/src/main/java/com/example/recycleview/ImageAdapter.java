package com.example.recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {
    List<String> listText;
    List<Integer> listImageIds;

    public ImageAdapter(List<String> listImageNames, List<Integer> listImageIds) {
        this.listText = listImageNames;
        this.listImageIds = listImageIds;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView.setImageResource(listImageIds.get(position));
        holder.textView.setText(listText.get(position));
    }

    @Override
    public int getItemCount() {
        return listText.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        private ImageView imageView;
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.imageText);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
