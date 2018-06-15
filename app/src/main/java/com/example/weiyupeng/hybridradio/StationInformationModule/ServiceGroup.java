package com.example.weiyupeng.hybridradio.StationInformationModule;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/*
xml parser class
* */

@XStreamAlias("serviceGroup")
public class ServiceGroup {
    @XStreamAlias("id")
    @XStreamAsAttribute
    private String id;
    @XStreamAlias("shortName")
    private String shortName;
    @XStreamAlias("mediumName")
    private String mediumName;
    @XStreamAlias("longName")
    private String longName;
    @XStreamImplicit
    private List<MediaDescription> mediaDescriptionList;
    @XStreamImplicit
    private List<Link> linkList;


    public String toString() {
        if(shortName == null){
            shortName = "";
        }
        if(mediumName == null){
            mediumName = "";
        }
        if(longName == null){
            longName = "";
        }
        if(id == null){
            id ="";
        }
        String str = "ServiceGroup: + \t" + "id = " + id + "\t" + "shortName = " + shortName + "\t" + "mediumName = " + mediumName + "\t" + "longName = " + longName + "\n";
        if(mediaDescriptionList!=null)
        for (MediaDescription each : mediaDescriptionList) {
            str += each.toString();
        }
        if(linkList!=null)
        for (Link each : linkList) {
            str += each.toString();
        }


        return str;
    }

    public ServiceGroup(String id, String shortName, String mediumName, String longName, List<MediaDescription> mediaDescriptionList, List<Link> linkList) {
        this.id = id;
        this.shortName = shortName;
        this.mediumName = mediumName;
        this.longName = longName;
        this.mediaDescriptionList = mediaDescriptionList;
        this.linkList = linkList;
    }

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
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public List<MediaDescription> getMediaDescriptionList() {
        return mediaDescriptionList;
    }

    public void setMediaDescriptionList(List<MediaDescription> mediaDescriptionList) {
        this.mediaDescriptionList = mediaDescriptionList;
    }

    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Link> linkList) {
        this.linkList = linkList;
    }
}
