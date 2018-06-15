package com.example.weiyupeng.hybridradio.StationInformationModule;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("services")
public class Services {
    @XStreamAlias("serviceProvider")
    private ServiceProvider serviceProvider;

    @XStreamImplicit
    private List<Service> serviceList;


    public String toString(){
        String str = "";
        for (Service each : serviceList) {
            str += each.toString();
        }
        return str;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }
}
