package com.example.weiyupeng.hybridradio.StationInformationModule;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by PengW on 3/14/2017.
 */
@XStreamAlias("serviceProvider")
public class ServiceProvider {
    @XStreamAlias("shortName")
    private String shortName;
    @XStreamAlias("mediumName")
    private String mediumName;
    @XStreamAlias("longName")
    private String longName;
    @XStreamAlias("shortDescription")
    private String shortDescription;
    @XStreamAlias("mediumDescription")
    private String mediumDescription;
    @XStreamAlias("longDescription")
    private String longDescription;


    @XStreamImplicit
    private List<MediaDescription> mediaDescriptions;

    @XStreamImplicit
    private List<Link> linkList;

    @XStreamAlias("geolocation")
    private Geolocation geolocation;

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

    public List<MediaDescription> getMediaDescriptions() {
        return mediaDescriptions;
    }

    public void setMediaDescriptions(List<MediaDescription> mediaDescriptions) {
        this.mediaDescriptions = mediaDescriptions;
    }

    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Link> linkList) {
        this.linkList = linkList;
    }

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }
}
