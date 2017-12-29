package com.team.esgi.projet_esgi;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainApplication extends Application {
    Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        Realm.init(this);
        //RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm.realm").build();

        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm.realm")
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);
    }
}
