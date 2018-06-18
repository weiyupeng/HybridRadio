package com.example.weiyupeng.hybridradio.db;

import java.security.PrivateKey;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DBServiceGroup extends RealmObject{
    @PrimaryKey
    private String id;
    private String shortName;
    private String mediumName;
    private String LongName;
    private String description;
    private String MaxPicURL;
    private String Country;
    private RealmList<DBService> services;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getMediumName() {
        return mediumName;
    }

    public void setMediumName(String mediumName) {
        this.mediumName = mediumName;
    }

    public String getLongName() {
        return LongName;
    }

    public void setLongName(String longName) {
        LongName = longName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxPicURL() {
        return MaxPicURL;
    }

    public void setMaxPicURL(String maxPicURL) {
        MaxPicURL = maxPicURL;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public RealmList<DBService> getServices() {
        return services;
    }

    public void setServices(RealmList<DBService> services) {
        this.services = services;
    }
}
