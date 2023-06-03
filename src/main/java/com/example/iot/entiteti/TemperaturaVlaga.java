package com.example.iot.entiteti;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TemperaturaVlaga {

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "temperatura_vlaga_id_seq"
    )
    @SequenceGenerator(
            name = "temperatura_vlaga_id_seq",
            sequenceName = "temperatura_vlaga_id_seq",
            allocationSize = 1
    )
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "vrijeme", nullable = false)
    private LocalDateTime vrijeme;

    @Column(name = "temperatura", nullable = false)
    private Double temperatura;

    @Column(name = "vlaga", nullable = false)
    private Double vlaga;

    @Column(name = "sensor_id", nullable = false)
    private Integer sensorId;

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(LocalDateTime vrijeme) {
        this.vrijeme = vrijeme;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getVlaga() {
        return vlaga;
    }

    public void setVlaga(Double vlaga) {
        this.vlaga = vlaga;
    }
}
