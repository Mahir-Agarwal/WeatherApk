package com.example.weatherpromax;

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

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.ViewHolder> {
    private Context context;
    private ArrayList<hour> item;

    public HourlyAdapter(ArrayList<hour> item) {
        this.item = item;
    }

    @NonNull
    @Override
    public HourlyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_hourly, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.hourtxt.setText(item.get(position).getHour());

        // Convert int temp to String before setting it
        holder.temptxt.setText(item.get(position).getTemp() + "°C");

        int drawableResourceId = holder.itemView.getResources()
                .getIdentifier(item.get(position).getPicPath(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(context).load(drawableResourceId).into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView hourtxt, temptxt;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hourtxt = itemView.findViewById(R.id.hourTxt);
            temptxt = itemView.findViewById(R.id.tempTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
