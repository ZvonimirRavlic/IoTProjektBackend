package com.example.iot.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TempDto {

    public int dob;
    public double trenutnaTemperatura;
    public double trenutnaVlaga;
    public double min;
    public double max;
    public boolean auto;
    public List<PovijestTemperature> povijestTemperature;

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public boolean isAuto() {
        return auto;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    public double getTrenutnaVlaga() {
        return trenutnaVlaga;
    }

    public void setTrenutnaVlaga(double trenutnaVlaga) {
        this.trenutnaVlaga = trenutnaVlaga;
    }

    public int getDob() {
        return dob;
    }

    public void setDob(int dob) {
        this.dob = dob;
    }

    public double getTrenutnaTemperatura() {
        return trenutnaTemperatura;
    }

    public void setTrenutnaTemperatura(double trenutnaTemperatura) {
        this.trenutnaTemperatura = trenutnaTemperatura;
    }

    public List<PovijestTemperature> getPovijestTemperature() {
        return povijestTemperature;
    }

    public void setPovijestTemperature(List<PovijestTemperature> povijestTemperature) {
        this.povijestTemperature = povijestTemperature;
    }

    public static class PovijestTemperature {
        public double temperatura;
        public double vlaga;
        public LocalDateTime datumVrijeme;

        public double getTemperatura() {
            return temperatura;
        }

        public void setTemperatura(double temperatura) {
            this.temperatura = temperatura;
        }

        public double getVlaga() {
            return vlaga;
        }

        public void setVlaga(double vlaga) {
            this.vlaga = vlaga;
        }

        public LocalDateTime getDatumVrijeme() {
            return datumVrijeme;
        }

        public void setDatumVrijeme(LocalDateTime datumVrijeme) {
            this.datumVrijeme = datumVrijeme;
        }
    }
}
