package com.example.weiyupeng.hybridradio.StationInformationModule;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;


/*
xml parser class
* */

@XStreamAlias("multimedia")
public class Multimedia {
    @XStreamAlias("height")
    @XStreamAsAttribute
    String height;
    @XStreamAlias("mimeValue")
    @XStreamAsAttribute
    String mimeValue;
    @XStreamAlias("url")
    @XStreamAsAttribute
    String url;
    @XStreamAlias("width")
    @XStreamAsAttribute
    String width;

    public String toString(){
        return "multimedia: + \t" + "height = " + height + "\t"+ "mimeValue = " + mimeValue + "\t" + "url = " + url + "\t" + "width = " + width + "\n" ;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMimeValue() {
        return mimeValue;
    }

    public void setMimeValue(String mimeValue) {
        this.mimeValue = mimeValue;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}
