package com.example.iot.entiteti;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AutoTemp {

    @Id
    @Column(name = "dob", nullable = false)
    private Integer dob;

    @Column(name = "min", nullable = false)
    private Double min;

    @Column(name = "max", nullable = false)
    private Double max;


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
