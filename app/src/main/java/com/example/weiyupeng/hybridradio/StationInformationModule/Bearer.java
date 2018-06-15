package com.example.weiyupeng.hybridradio.StationInformationModule;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/*
xml parser class
* */
@XStreamAlias("bearer")
public class Bearer {
    @XStreamAlias("bitrate")
    @XStreamAsAttribute
    private String bitrate;
    @XStreamAlias("cost")
    @XStreamAsAttribute
    private String cost;
    @XStreamAlias("id")
    @XStreamAsAttribute
    private String id;
    @XStreamAlias("mimeValue")
    @XStreamAsAttribute
    private String mimeValue;
    @XStreamAlias("offset")
    @XStreamAsAttribute
    private String offset;

    public String toString(){
        if(bitrate == null){
            bitrate = "";
        }
        if(id == null){
            id = "";
        }
        if(bitrate == null){
            bitrate = "";
        }
        if(mimeValue == null){
            mimeValue = "";
        }
        if(offset == null){
            offset = "";
        }
        return "bearer: \t" + "bitrate = " + bitrate + "\t" + "cost = " + cost + "\t" + "id = " + id + "\t" +  "offset = " + offset + "\n" ;
    }
    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMimeValue() {
        return mimeValue;
    }

    public void setMimeValue(String mimeValue) {
        this.mimeValue = mimeValue;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }
}
