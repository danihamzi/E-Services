package com.example.dell_pc.e_services;

/**
 * Created by DELL-PC on 9/30/2017.
 */

public class ServiceProvider {

    private String ServiceProviderId;
    private String ServiceProviderName;
    private String ServiceProviderFathername;

    private String ServiceProviderPhoneno;

    private String ServiceProviderIdcardno;

    private String ServiceProviderCity;

    private String ServiceProviderAddress;

    private String ServiceProviderStartingtime;
    private String ServiceProviderFinishingtime;

    private String ServiceProviderOccupation;
    private String ServiceProviderExpertise;
    private String ServiceProviderRate;


    public ServiceProvider(){

    }


    public ServiceProvider(String serviceProviderId,String serviceProviderName,String serviceProviderFathername,
                           String serviceProviderPhoneno,String serviceProviderIdcardno,String serviceProviderCity,
                           String serviceProviderAddress,String serviceProviderStartingtime,String serviceProviderFinishingtime,
                           String serviceProviderOccupation,String serviceProviderExpertise,String serviceProviderRate) {

        ServiceProviderId = serviceProviderId;
        ServiceProviderName = serviceProviderName;
        ServiceProviderFathername= serviceProviderFathername;

        ServiceProviderPhoneno = serviceProviderPhoneno;
        ServiceProviderIdcardno = serviceProviderIdcardno;

        ServiceProviderCity = serviceProviderCity;
        ServiceProviderAddress= serviceProviderAddress;
        ServiceProviderStartingtime= serviceProviderStartingtime;
        ServiceProviderFinishingtime= serviceProviderFinishingtime;
        ServiceProviderOccupation = serviceProviderOccupation;
        ServiceProviderExpertise= serviceProviderExpertise;
        ServiceProviderRate= serviceProviderRate;
    }


   public String getServiceProviderId() {
        return ServiceProviderId;
    }

    public void setServiceProviderId(String serviceProviderId) {
        ServiceProviderId = serviceProviderId;
    }

    public String getServiceProviderName() {
        return ServiceProviderName;
    }

    public void setServiceProviderName(String serviceProviderName) {
        ServiceProviderName = serviceProviderName;
    }

    public String getServiceProviderFathername() {
        return ServiceProviderFathername;
    }

    public void setServiceProviderFathername(String serviceProviderFathername) {
        ServiceProviderFathername = serviceProviderFathername;
    }

    public String getServiceProviderPhoneno() {
        return ServiceProviderPhoneno;
    }

    public void setServiceProviderPhoneno(String serviceProviderPhoneno) {
        ServiceProviderPhoneno = serviceProviderPhoneno;
    }

    public String getServiceProviderIdcardno() {
        return ServiceProviderIdcardno;
    }

    public void setServiceProviderIdcardno(String serviceProviderIdcardno) {
        ServiceProviderIdcardno = serviceProviderIdcardno;
    }


    public String getServiceProviderCity() {
        return ServiceProviderCity;
    }

    public void setServiceProviderCity(String serviceProviderCity) {
        ServiceProviderCity = serviceProviderCity;
    }

    public void setServiceProviderAddress(String serviceProviderAddress) {
        ServiceProviderAddress = serviceProviderAddress;
    }

    public String getServiceProviderAddress() {
        return ServiceProviderAddress;
    }

    public String getServiceProviderStartingtime() {
        return ServiceProviderStartingtime;
    }

    public void setServiceProviderStartingtime(String serviceProviderStartingtime) {
        ServiceProviderStartingtime = serviceProviderStartingtime;
    }

    public String getServiceProviderFinishingtime() {
        return ServiceProviderFinishingtime;
    }

    public void setServiceProviderFinishingtime(String serviceProviderFinishingtime) {
        ServiceProviderFinishingtime = serviceProviderFinishingtime;
    }

    public String getServiceProviderOccupation() {
        return ServiceProviderOccupation;
    }

    public void setServiceProviderOccupation(String serviceProviderOccupation) {
        ServiceProviderOccupation = serviceProviderOccupation;
    }

    public String getServiceProviderExpertise() {
        return ServiceProviderExpertise;
    }

    public void setServiceProviderExpertise(String serviceProviderExpertise) {
        ServiceProviderExpertise = serviceProviderExpertise;
    }

    public String getServiceProviderRate() {
        return ServiceProviderRate;
    }

    public void setServiceProviderRate(String serviceProviderRate) {
        ServiceProviderRate = serviceProviderRate;
    }
}

