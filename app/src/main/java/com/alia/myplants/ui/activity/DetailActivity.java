package com.alia.myplants.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.alia.myplants.R;
import com.alia.myplants.utils.CropSquareTransformation;
import com.alia.myplants.model.Plant;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    private String plantId;
    private Realm realm;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.image_view)
    ImageView imageView;

    @BindView(R.id.name_view)
    TextView nameView;

    @BindView(R.id.description_view)
    TextView descriptionView;

    @BindView(R.id.water_view)
    TextView waterView;

    @BindView(R.id.fertilizer_view)
    TextView fertilizerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        plantId = getIntent().getStringExtra(MainActivity.EXTRA_PLANT_ID);
        realm = Realm.getDefaultInstance();


        try {
            Plant plant = realm.where(Plant.class).equalTo("id", plantId).findFirst();
            if (plant.getImageName() != null) {
                Picasso picasso = Picasso.with(this);
                picasso.setIndicatorsEnabled(true);
                picasso.setLoggingEnabled(true);
                picasso.load("content://" + "com.alia.myplants.fileprovider/images/" + plant.getImageName())
                        .transform(new CropSquareTransformation())
                        .placeholder(R.drawable.ic_placeholder)
                        .into(imageView);
            }
            String path = plant.getImageName();
            descriptionView.setText(path);
            nameView.setText(plant.getName());
            int water = plant.getWater();
            waterView.setText(getString(R.string.next_watering, water));
            int fert = plant.getFertilizer();
            fertilizerView.setText(getString(R.string.next_fertilizing, "September", fert));
        } finally {
            realm.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bar_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit_plant:

                return true;
            case R.id.menu_delete_plant:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
