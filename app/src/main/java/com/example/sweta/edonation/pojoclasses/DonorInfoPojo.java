package com.example.sweta.edonation.pojoclasses;

public class DonorInfoPojo {
    String donorId;


    String orgnName;



    String donorName;
    String donorEmail;
    String donorLocation;
    Long donorPhone;
    public  CurrentlyLooking currentlyLooking;


    DonorInfoPojo(){

    }

    public DonorInfoPojo(String donorId,String orgnName, String donorName, String donorEmail, String donorLocation, Long donorPhone, CurrentlyLooking currentlyLooking){
        this.orgnName=orgnName;
        this.donorId=donorId;
        this.donorName=donorName;
        this.donorEmail=donorEmail;
        this.donorLocation=donorLocation;
        this.donorPhone=donorPhone;
        this.currentlyLooking=currentlyLooking;
    }
    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public String getOrgnName() {
        return orgnName;
    }

    public void setOrgnName(String orgnName) {
        this.orgnName = orgnName;
    }

    public CurrentlyLooking getCurrentlyLooking() {
        return currentlyLooking;
    }

    public void setCurrentlyLooking(CurrentlyLooking currentlyLooking) {
        this.currentlyLooking = currentlyLooking;
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


}
