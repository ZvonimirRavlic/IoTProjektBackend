package com.example.iot.dto;

public class TempUpdate {

    public int dob;
    public double minTemperatura;
    public double maxTemperatura;
    public boolean autoTemperatura;

    public int getDob() {
        return dob;
    }

    public void setDob(int dob) {
        this.dob = dob;
    }

    public double getMinTemperatura() {
        return minTemperatura;
    }

    public void setMinTemperatura(double minTemperatura) {
        this.minTemperatura = minTemperatura;
    }

    public double getMaxTemperatura() {
        return maxTemperatura;
    }

    public void setMaxTemperatura(double maxTemperatura) {
        this.maxTemperatura = maxTemperatura;
    }

    public boolean isAutoTemperatura() {
        return autoTemperatura;
    }

    public void setAutoTemperatura(boolean autoTemperatura) {
        this.autoTemperatura = autoTemperatura;
    }
}
