package com.example.weiyupeng.hybridradio.StationInformationModule;

//import java.util.ArrayList;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/*
xml parser class
* */

@XStreamAlias("service")
public class Service {
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
    private List<MediaDescription> mediaDescriptionList;
    @XStreamImplicit
    private List<Genre> genreList;
    @XStreamImplicit
    private List<Link> linkList;
    @XStreamImplicit
    private List<Bearer> bearerList;
    @XStreamAlias("serviceGroupMember")
    private ServiceGroupMember serviceGroupMember;
    @XStreamAlias("radiodns")
    private Radiodns radiodns;

    public String toString(){
        if(shortName == null){
            shortName = "";
        }
        if(mediumName == null){
            mediumName = "";
        }
        if(longName == null){
            longName = "";
        }
        String str = "Service: + \t" + "shortName = " + shortName + "\t"+ "mediumName = " + mediumName + "\t"+ "longName = " + longName + "\n" ;
        if(mediaDescriptionList!=null)
        for(MediaDescription each : mediaDescriptionList){
            str += each.toString();
        }
        if(genreList!=null)
        for(Genre each : genreList){
            str += each.toString();
        }
        if(linkList!=null)
        for(Link each : linkList){
            str += each.toString();
        }
        if(bearerList!=null)
        for(Bearer each : bearerList){
            str += each.toString();
        }
        if(serviceGroupMember!=null) str += "serviceGroupMember: + \n" + serviceGroupMember.toString();
        if (radiodns!=null) str +=   "radiodns = " + radiodns.toString() + "\n";
        return str;
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

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Link> linkList) {
        this.linkList = linkList;
    }

    public ServiceGroupMember getServiceGroupMember() {
        return serviceGroupMember;
    }

    public void setServiceGroupMember(ServiceGroupMember serviceGroupMember) {
        this.serviceGroupMember = serviceGroupMember;
    }

    public List<Bearer> getBearerList() {
        return bearerList;
    }

    public void setBearerList(List<Bearer> bearerList) {
        this.bearerList = bearerList;
    }

    public Radiodns getRadiodns() {
        return radiodns;
    }

    public void setRadiodns(Radiodns radiodns) {
        this.radiodns = radiodns;
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
}
