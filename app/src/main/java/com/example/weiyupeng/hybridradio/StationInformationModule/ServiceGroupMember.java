package com.example.weiyupeng.hybridradio.StationInformationModule;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/*
xml parser class
* */

@XStreamAlias("serviceGroupMember")
public class ServiceGroupMember {
    @XStreamAlias("id")
    @XStreamAsAttribute
    private String id;

    public String toString(){
        if(id!=null)
            return id + "\n";
        else
            return "";
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
