package com.example.weiyupeng.hybridradio.StationInformationModule;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.List;

/**
 * Created by PengW on 2/27/2017.
 */

/*
xml parser class
* */

@XStreamAlias("serviceInformation")
public class ServiceInformation {
    @XStreamAlias("originator")
    @XStreamAsAttribute
    private String originator;



    @XStreamAlias("services")
    private Services services;

    @XStreamAlias("serviceGroups")
    private List<ServiceGroup> serviceGroups;

    public String toString() {
        String str = services.toString();

        for (ServiceGroup each : serviceGroups) {
            str += each.toString();
        }

        return str;
    }




    public List<ServiceGroup> getServiceGroups() {
        return serviceGroups;
    }

    public void setServiceGroups(List<ServiceGroup> serviceGroups) {
        this.serviceGroups = serviceGroups;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

}

