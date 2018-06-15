package com.example.weiyupeng.hybridradio.StationInformationModule;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;


/*
xml parser class
* */

@XStreamAlias("genre")
@XStreamConverter(value=ToAttributedValueConverter.class, strings={"type"})
public class Genre {
    @XStreamAlias("href")
    @XStreamAsAttribute
    private String href;

    //@XStreamAlias("genre")
    private String type;

    public String toString(){
        if(href == null){
            href = "";
        }
        if(type == null){
            type = "";
        }
        return "genre:  \t" + "href = " + href + "\t"+ "type = " + type + "\n";
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
