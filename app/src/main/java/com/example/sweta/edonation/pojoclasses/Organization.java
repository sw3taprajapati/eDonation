package com.example.sweta.edonation.pojoclasses;

public class Organization {

    String orgId;
    String orgFullName;
    String orgEmailID;
    String orgLocation;
    int orgPhone;
    String orgWebsite;
    int orgPan;
    int status;

    public Organization(){

    }


    public Organization(String orgId, String orgFullName, String orgEmailID, String orgLocation, int orgPhone, String orgWebsite, int orgPan, int status) {
        this.orgId = orgId;
        this.orgFullName = orgFullName;
        this.orgEmailID = orgEmailID;
        this.orgLocation = orgLocation;
        this.orgWebsite = orgWebsite;
        this.orgPhone = orgPhone;
        this.orgPan = orgPan;
        this.status = status;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public void setOrgFullName(String orgFullName) {
        this.orgFullName = orgFullName;
    }

    public void setOrgEmailID(String orgEmailID) {
        this.orgEmailID = orgEmailID;
    }

    public void setOrgLocation(String orgLocation) {
        this.orgLocation = orgLocation;
    }

    public void setOrgPhone(int orgPhone) {
        this.orgPhone = orgPhone;
    }

    public void setOrgWebsite(String orgWebsite) {
        this.orgWebsite = orgWebsite;
    }

    public void setOrgPan(int orgPan) {
        this.orgPan = orgPan;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getOrgFullName() {
        return orgFullName;
    }

    public String getOrgEmailID() {
        return orgEmailID;
    }

    public String getOrgLocation() {
        return orgLocation;
    }

    public String getOrgWebsite() {
        return orgWebsite;
    }

    public int getOrgPhone() {
        return orgPhone;
    }

    public int getOrgPan() {
        return orgPan;
    }

    public int getStatus(){
        return status;
    }
}
