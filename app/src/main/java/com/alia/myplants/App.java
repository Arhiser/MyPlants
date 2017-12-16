package com.alia.myplants;

import android.app.Application;

import com.alia.myplants.db.RealmService;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Alyona on 06.12.2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RealmService.initRealm(this);


    }
}
