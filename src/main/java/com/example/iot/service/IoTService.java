package com.example.iot.service;

import com.example.iot.dto.TempDto;
import com.example.iot.dto.TempUpdate;
import com.example.iot.entiteti.TemperaturaVlaga;


public interface IoTService {


    TempDto getTemp(Integer id);

    TempDto updateTemp(Integer id, TempUpdate tempUpdate);

}
