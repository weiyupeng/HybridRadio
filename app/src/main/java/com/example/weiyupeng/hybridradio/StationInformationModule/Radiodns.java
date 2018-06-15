package com.example.weiyupeng.hybridradio.StationInformationModule;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;


/*
xml parser class
* */

@XStreamAlias("radiodns")
public class Radiodns {
    @XStreamAlias("fqdn")
    @XStreamAsAttribute
    private String fqdn;
    @XStreamAlias("serviceIdentifier")
    @XStreamAsAttribute
    private String serviceIdentifier;


    public String toString(){
        if(fqdn == null){
            fqdn = "";
        }
        if(serviceIdentifier == null){
            serviceIdentifier = "";
        }
        return "Radiodns: + \t" + "fqdn = " + fqdn + "\t"+ "serviceIdentifier = " + serviceIdentifier + "\n" ;
    }


    public String getFqdn() {
        return fqdn;
    }

    public void setFqdn(String fqdn) {
        this.fqdn = fqdn;
    }

    public String getServiceIdentifier() {
        return serviceIdentifier;
    }

    public void setServiceIdentifier(String serviceIdentifier) {
        this.serviceIdentifier = serviceIdentifier;
    }
}
