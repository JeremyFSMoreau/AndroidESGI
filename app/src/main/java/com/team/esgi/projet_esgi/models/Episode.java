package com.team.esgi.projet_esgi.models;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class Episode extends RealmObject {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

//Episode episode = new Episode;
//
//Realm.init(this);

//
//Episode episode = new Episode();
//Episode.getInstance();
