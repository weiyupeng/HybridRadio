package com.example.weiyupeng.hybridradio.StationInformationModule;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/*
xml parser class
* */

@XStreamAlias("link")
public class Link {
    @XStreamAlias("uri")
    @XStreamAsAttribute
    String uri;
    @XStreamAlias("mimeValue")
    @XStreamAsAttribute
    String mimeValue;
    @XStreamAlias("description")
    @XStreamAsAttribute
    String description;

    public String toString(){
        if(uri == null){
            uri = "";
        }
        if(mimeValue == null){
            mimeValue = "";
        }
        if(description == null){
            description = "";
        }
        return "link: + \t" + "uri = " + uri + "\t"+ "mimeValue = " + mimeValue + "\t" + "description = " + description + "\n" ;
    }
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMimeValue() {
        return mimeValue;
    }

    public void setMimeValue(String mimeValue) {
        this.mimeValue = mimeValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
