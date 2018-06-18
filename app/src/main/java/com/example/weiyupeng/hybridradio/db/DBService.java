package com.example.weiyupeng.hybridradio.db;

import com.example.weiyupeng.hybridradio.StationInformationModule.Genre;



import io.realm.RealmList;
import io.realm.RealmObject;

public class DBService extends RealmObject{
    private String shortName;
    private String mediumName;
    private String LongName;
    private String description;
    private String MaxPicURL;
    private RealmList<DBGenre> genreList;
    private String audioSourceURL;
    private String GroupID;


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

    public RealmList<DBGenre> getGenreList() {
        return genreList;
    }

    public void setGenreList(RealmList<DBGenre> genreList) {
        this.genreList = genreList;
    }

    public String getAudioSourceURL() {
        return audioSourceURL;
    }

    public void setAudioSourceURL(String audioSourceURL) {
        this.audioSourceURL = audioSourceURL;
    }

    public String getGroupID() {
        return GroupID;
    }

    public void setGroupID(String groupID) {
        GroupID = groupID;
    }


}
