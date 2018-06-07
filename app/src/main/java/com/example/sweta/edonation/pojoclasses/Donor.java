package com.example.sweta.edonation.pojoclasses;

public class Donor {
    String donorId;
    String orgName;
    String donorName;
    String donorEmail;
    String donorLocation;
    Long donorPhone;
    public  CurrentlyLooking currentlyLooking;
    String describeItems;

    public Donor(){

    }

    public Donor(String donorId, String orgName, String donorName, String donorEmail,
                 String donorLocation, Long donorPhone,
                 CurrentlyLooking donatedItems, String describeItems) {
        this.donorId = donorId;
        this.orgName = orgName;
        this.donorName = donorName;
        this.donorEmail = donorEmail;
        this.donorLocation = donorLocation;
        this.donorPhone = donorPhone;
        this.currentlyLooking = donatedItems;
        this.describeItems = describeItems;
    }

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorEmail() {
        return donorEmail;
    }

    public void setDonorEmail(String donorEmail) {
        this.donorEmail = donorEmail;
    }

    public String getDonorLocation() {
        return donorLocation;
    }

    public void setDonorLocation(String donorLocation) {
        this.donorLocation = donorLocation;
    }

    public Long getDonorPhone() {
        return donorPhone;
    }

    public void setDonorPhone(Long donorPhone) {
        this.donorPhone = donorPhone;
    }

    public CurrentlyLooking getCurrentlyLooking() {
        return currentlyLooking;
    }

    public void setCurrentlyLooking(CurrentlyLooking currentlyLooking) {
        this.currentlyLooking = currentlyLooking;
    }

    public String getDescribeItems() {
        return describeItems;
    }

    public void setDescribeItems(String describeItems) {
        this.describeItems = describeItems;
    }
}
