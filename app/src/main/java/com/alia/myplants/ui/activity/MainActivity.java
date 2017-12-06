package com.alia.myplants.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.alia.myplants.ui.fragment.AddPlantDialog;
import com.alia.myplants.ui.adapter.PlantAdapter;
import com.alia.myplants.R;
import com.alia.myplants.model.Plant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.app.ActionBar.DISPLAY_SHOW_TITLE;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private PlantAdapter mAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.empty)
    TextView mEmptyView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayOptions(0, DISPLAY_SHOW_TITLE);
        getSupportActionBar().setLogo(R.drawable.logo);
        List<Plant> plants = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            Plant plant = new Plant(i + 100, "Гриша", "Low");
            plants.add(plant);
        }

        int columns = getResources().getInteger(R.integer.columns_count);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, columns));
        mAdapter = new PlantAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.changeDataSet(plants);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar_menu, menu);
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

    private void showAddDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        AddPlantDialog dialog = AddPlantDialog.newInstance();
        dialog.show(fragmentManager, "dialog");
    }

}
