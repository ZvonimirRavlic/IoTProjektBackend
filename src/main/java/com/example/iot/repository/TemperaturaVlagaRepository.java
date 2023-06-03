package com.example.iot.repository;

import com.example.iot.entiteti.TemperaturaVlaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemperaturaVlagaRepository extends JpaRepository<TemperaturaVlaga, Integer> {

    TemperaturaVlaga findFirstBySensorIdOrderByVrijemeDesc(Integer sensorId);

    List<TemperaturaVlaga> findBySensorIdOrderByVrijemeAsc(Integer sensorId);

}
