package com.example.dell_pc.e_services;

/**
 * Created by DELL-PC on 9/30/2017.
 */

public class ServiceProvider {

    String ServiceProviderId;
    String ServiceProviderName;
    String ServiceProviderCity;
    String ServiceProviderPhoneno;
    String ServiceProviderOccupation;



    public ServiceProvider( String serviceProviderId, String serviceProviderName, String serviceProviderPhoneno, String serviceProviderCity,String serviceProviderOccupation) {

        this.ServiceProviderId = serviceProviderId;
        this.ServiceProviderName = serviceProviderName;
        this.ServiceProviderPhoneno = serviceProviderPhoneno;
        this.ServiceProviderCity = serviceProviderCity;
        this.ServiceProviderOccupation = serviceProviderOccupation;
    }



    public String getServiceProviderId() {
        return ServiceProviderId;
    }

    public String getServiceProviderName() {
        return ServiceProviderName;
    }

    public String getServiceProviderPhoneno() {
        return ServiceProviderPhoneno;
    }

    public String getServiceProviderCity() {return ServiceProviderCity; }

    public String getServiceProviderOccupation() {
        return ServiceProviderOccupation;
    }
}

