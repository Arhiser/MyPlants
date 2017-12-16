package com.alia.myplants.ui.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alia.myplants.ui.fragment.AddPlantDialog;
import com.alia.myplants.ui.adapter.PlantAdapter;
import com.alia.myplants.R;
import com.alia.myplants.model.Plant;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements AddPlantDialog.ChangeRealmDataListener {
    private static final String TAG = "MainActivity";
    private static final String DIALOG_TAG = "addDialog";
    public static final String EXTRA_PLANT_ID = "MainActivity.plant.id";

    private PlantAdapter adapter;
    private Realm realm;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.empty)
    TextView emptyView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setLogo(R.drawable.logo);

        realm = Realm.getDefaultInstance();

        int columns = getResources().getInteger(R.integer.columns_count);
        recyclerView.setLayoutManager(new GridLayoutManager(this, columns));

        RealmResults<Plant> plantsList = realm.where(Plant.class).findAll();

        if (plantsList.size() == 0) {
            showEmptyView();
        } else {
            showPlants();
            adapter = new PlantAdapter(plantsList, plant -> createIntent(plant));
            recyclerView.setAdapter(adapter);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bar_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_plant:
                showAddDialog();
                return true;
            case R.id.menu_search_plant:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onAddPlant(Plant plant) {
        realm.beginTransaction();
        realm.copyToRealm(plant);
        realm.commitTransaction();
    }

    private void createIntent(Plant plant) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(EXTRA_PLANT_ID, plant.getId());
        startActivity(intent);
    }

    private void showEmptyView() {
        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    private void showPlants() {
        recyclerView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
    }

    private void showAddDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        AddPlantDialog dialog = AddPlantDialog.newInstance();
        dialog.show(fragmentManager, DIALOG_TAG);

    }

}
