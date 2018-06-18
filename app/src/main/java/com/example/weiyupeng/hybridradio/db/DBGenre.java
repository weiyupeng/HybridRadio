package com.example.weiyupeng.hybridradio.db;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DBGenre extends RealmObject {
    @PrimaryKey
    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
