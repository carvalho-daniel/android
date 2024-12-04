package com.example.recyclerview;

public class RocketModel {
    int imgFoguete;
    String rocketName;
    String launchDate;
    boolean launchSuccess;

    String payload;

    public RocketModel(int imgFoguete, String rocketName, String launchDate, boolean launchSuccess, String payload) {
        this.imgFoguete = imgFoguete;
        this.rocketName = rocketName;
        this.launchDate = launchDate;
        this.launchSuccess = launchSuccess;
        this.payload = payload;
    }

    public String getRocketName() {
        return this.rocketName;
    }

    public void setRocketName(String rocketName) {
        this.rocketName = rocketName;
    }

    public String getLaunchDate() {
        return this.launchDate;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    public boolean isLaunchSuccess() {
        return this.launchSuccess;
    }

    public void setLaunchSuccess(boolean launchSuccess) {
        this.launchSuccess = launchSuccess;
    }

    public String getPayload() {
        return this.payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setImgFoguete(int imgFoguete) {
        this.imgFoguete = imgFoguete;
    }

    public int getImgFoguete() {
        return this.imgFoguete;
    }

}
