package com.example.sweta.edonation.pojoclasses;

public class Organization {

    String orgId;
    String orgFullName;
    String orgEmailID;
    String orgPassword;
    String orgLocation;
    int orgPhone;
    String orgWebsite;
    int orgPan;
    String currentlyLooking;
    String describeItems;
    int status;

    /*boolean check1;
    boolean check2;
    boolean check3;
    boolean check4;*/
    public Organization() {

    }

    public Organization(String orgId, String orgFullName, String orgEmailID, String orgPassword, String orgLocation, int orgPhone, String orgWebsite, int orgPan, String currentlyLooking, String describeItems, int status) {

        this.orgId = orgId;
        this.orgFullName = orgFullName;
        this.orgEmailID = orgEmailID;
        this.orgPassword = orgPassword;
        this.orgLocation = orgLocation;
        this.orgWebsite = orgWebsite;
        this.orgPhone = orgPhone;
        this.orgPan = orgPan;
       /* this.check1 = check1;
        this.check2 = check2;
        this.check3 = check3;
        this.check4 = check4;*/
        this.currentlyLooking = currentlyLooking;
        this.describeItems = describeItems;
        this.status = status;
    }



    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgFullName() {
        return orgFullName;
    }

    public void setOrgFullName(String orgFullName) {
        this.orgFullName = orgFullName;
    }

    public String getOrgEmailID() {
        return orgEmailID;
    }

    public void setOrgEmailID(String orgEmailID) {
        this.orgEmailID = orgEmailID;
    }

    public String getOrgPassword() {
        return orgPassword;
    }

    public void setOrgPassword(String orgPassword) {
        this.orgPassword = orgPassword;
    }


    public String getOrgLocation() {
        return orgLocation;
    }

    public void setOrgLocation(String orgLocation) {
        this.orgLocation = orgLocation;
    }

    public int getOrgPhone() {
        return orgPhone;
    }

    public void setOrgPhone(int orgPhone) {
        this.orgPhone = orgPhone;
    }

    public String getOrgWebsite() {
        return orgWebsite;
    }

    public void setOrgWebsite(String orgWebsite) {
        this.orgWebsite = orgWebsite;
    }

    public int getOrgPan() {
        return orgPan;
    }

    public void setOrgPan(int orgPan) {
        this.orgPan = orgPan;
    }

    public String getCurrentlyLooking() {
        return currentlyLooking;
    }

    public void setCurrentlyLooking(String currentlyLooking) {
        this.currentlyLooking = currentlyLooking;
    }

    public String getDescribeItems() {
        return describeItems;
    }

    public void setDescribeItems(String describeItems) {
        this.describeItems = describeItems;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}















