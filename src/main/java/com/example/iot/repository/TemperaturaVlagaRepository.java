package com.example.iot.repository;

import com.example.iot.entiteti.TemperaturaVlaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TemperaturaVlagaRepository extends JpaRepository<TemperaturaVlaga, Integer> {

    TemperaturaVlaga findFirstBySensorIdOrderByVrijemeDesc(Integer sensorId);

    List<TemperaturaVlaga> findBySensorIdOrderByVrijemeAsc(Integer sensorId);


    @Query(
            value = """
                    select dense_rank() over (order by date_trunc('minute', vrijeme)) as id, date_trunc('minute', vrijeme) as vrijeme, avg(temperatura) as temperatura, avg(vlaga) as vlaga, 1 as sensor_id
                    from temperatura_vlaga
                    where sensor_id =:sensorId
                    group by date_trunc('minute', vrijeme)
                    order by date_trunc('minute', vrijeme)
                    """,
            nativeQuery = true)
    List<TemperaturaVlaga> findPovijestSenzora(Integer sensorId);

}
