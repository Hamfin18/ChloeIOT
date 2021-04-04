package com.example.chloeiot.Model;

public class ModelHistory {

    String  date,time,soilMoisture;

    public ModelHistory() {
    }

    public ModelHistory(String date, String time, String soilMoisture) {
        this.date = date;
        this.time = time;
        this.soilMoisture = soilMoisture;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSoilMoisture() {
        return soilMoisture;
    }

    public void setSoilMoisture(String soilMoisture) {
        this.soilMoisture = soilMoisture;
    }
}
