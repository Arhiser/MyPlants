package com.alia.myplants.ui.adapter;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by Alyona on 04.12.2017.
 */

public class PlantAdapter extends RealmRecyclerViewAdapter<Plant, PlantAdapter.PlantHolder> {
    public PlantAdapter(OrderedRealmCollection<Plant> data) {
        super(data, true);
    }

    @Override
    public PlantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item, parent, false);
        return new PlantHolder(v);
    }

    @Override
    public void onBindViewHolder(PlantHolder holder, int position) {
        Plant plant = getItem(position);
        holder.bind(plant);

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
            waterView.setText(String.valueOf(plant.getWater()));
            Picasso.with(imageView.getContext())
                    .load(R.drawable.flowers_1x)
                    .into(imageView);
        }
    }

}
