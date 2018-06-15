package com.example.weiyupeng.hybridradio.StationInformationModule;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/*
xml parser class
* */

@XStreamAlias("mediaDescription")
public class MediaDescription {
    @XStreamAlias("shortDescription")
    private String shortDescription;
    @XStreamAlias("mediumDescription")
    private String mediumDescription;
    @XStreamAlias("longDescription")
    private String longDescription;
    @XStreamAlias("multimedia")
    private Multimedia  multimedia;





    public String toString(){
        if(shortDescription == null){
            shortDescription = "";
        }
        if(mediumDescription == null){
            mediumDescription = "";
        }
        if(longDescription == null){
            longDescription = "";
        }
        String str = "";
        str = "MediaDescription: + \t" + "shortDescription = " + shortDescription + "\t"+ "mediumDescription = " + mediumDescription + "\t" + "longDescription = " + longDescription + "\n"  ;
        if (multimedia!= null) str +=  "multimedia = " + multimedia.toString() + "\n";
        return str;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getMediumDescription() {
        return mediumDescription;
    }

    public void setMediumDescription(String mediumDescription) {
        this.mediumDescription = mediumDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Multimedia getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Multimedia multimedia) {
        this.multimedia = multimedia;
    }
}
