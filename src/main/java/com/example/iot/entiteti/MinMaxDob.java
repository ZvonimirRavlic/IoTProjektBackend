package com.example.iot.entiteti;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class MinMaxDob {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "dob", nullable = false)
    private Integer dob;

    @Column(name = "min", nullable = false)
    private Double min;

    @Column(name = "max", nullable = false)
    private Double max;
    @Column(name = "vrijeme_pocetka", nullable = false)
    private LocalDateTime vrijemePocetka;

    @Column(name = "auto", nullable = false)
    private boolean auto;

    public MinMaxDob(Integer id) {
        this.id = id;
        this.dob = 1;
        this.min = 24.0;
        this.max = 26.0;
        this.vrijemePocetka = LocalDateTime.now().minusDays(1);
        this.auto = true;
    }

    public MinMaxDob() {

    }

    public boolean isAuto() {
        return auto;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    public LocalDateTime getVrijemePocetka() {
        return vrijemePocetka;
    }

    public void setVrijemePocetka(LocalDateTime vrijemePocetka) {
        this.vrijemePocetka = vrijemePocetka;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDob() {
        return dob;
    }

    public void setDob(Integer dob) {
        this.dob = dob;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }
}
