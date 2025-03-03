package com.example.weatherpromax;

public class hour {
    private String hour;
    private int temp;
    private String picPath;

    public hour(String hour, String picPath, int temp) {
        this.hour = hour;
        this.picPath = picPath;
        this.temp = temp;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
}
