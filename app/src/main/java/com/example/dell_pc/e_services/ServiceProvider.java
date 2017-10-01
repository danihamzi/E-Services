package com.example.dell_pc.e_services;

/**
 * Created by DELL-PC on 9/30/2017.
 */

public class ServiceProvider {

    String ServiceProviderId;
    String ServiceProviderName;
    String ServiceProviderCity;
    String ServiceProviderPhoneno;

    public ServiceProvider(String id, String name, String city, String phoneno, String occupation)
    {

    }

    public ServiceProvider(String serviceProviderCity, String serviceProviderId, String serviceProviderName, String serviceProviderPhoneno) {
        this.ServiceProviderCity = serviceProviderCity;
        this.ServiceProviderId = serviceProviderId;
        this.ServiceProviderName = serviceProviderName;
        this.ServiceProviderPhoneno = serviceProviderPhoneno;
    }

    public String getServiceProviderCity() {
        return ServiceProviderCity;
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
}
