package com.alia.myplants;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Alyona on 06.12.2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("myplantsrealm.realm").build();
        Realm.setDefaultConfiguration(config);


    }
}
