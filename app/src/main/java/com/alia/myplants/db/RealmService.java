package com.alia.myplants.db;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Alyona on 13.12.2017.
 */

public class RealmService {
    private static final String DB_NAME = "myplantsrealm.realm";
    private static RealmService instance;

    private RealmService(Application context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().name(DB_NAME).build();
        Realm.setDefaultConfiguration(config);
    }

    public static void initRealm(Application context) {
        if (instance == null) {
            instance = new RealmService(context);
        }
    }

    public RealmService getInstance() {
        return instance;
    }
}
