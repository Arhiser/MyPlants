package com.alia.myplants.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alia.myplants.R;
import com.alia.myplants.model.Plant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alyona on 04.12.2017.
 */

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantHolder> {
    private Context mContext;
    private final List<Plant> mPlants;

    public PlantAdapter(Context context) {
        mContext = context;
        mPlants = new ArrayList<>();
    }

    @Override
    public PlantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item, parent, false);
        return new PlantHolder(v);
    }

    @Override
    public void onBindViewHolder(PlantHolder holder, int position) {
        Plant plant = mPlants.get(position);
        holder.bind(plant);

    }

    @Override
    public int getItemCount() {
        return mPlants.size();
    }
    public void changeDataSet(List<Plant> plants){
        mPlants.addAll(plants);
        notifyDataSetChanged();
    }

    class PlantHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.plant_thumbnail)
        ImageView imageView;
        @BindView(R.id.plant_name)
        TextView nameView;
        @BindView(R.id.plant_water)
        TextView waterView;

        public PlantHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(@NonNull Plant plant) {
            nameView.setText(plant.getName());
            waterView.setText(plant.getWater());
            Picasso.with(imageView.getContext())
                    .load(R.drawable.flowers_1x)
                    .into(imageView);


        }
    }

}
