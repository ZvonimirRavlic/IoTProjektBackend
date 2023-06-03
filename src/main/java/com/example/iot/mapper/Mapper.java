package com.example.iot.mapper;

import com.example.iot.dto.TempDto;
import com.example.iot.entiteti.MinMaxDob;
import com.example.iot.entiteti.TemperaturaVlaga;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {


    public TempDto map(MinMaxDob minMaxDob, TemperaturaVlaga temperaturaVlaga, List<TemperaturaVlaga> povijest) {
        final TempDto tempDto = new TempDto();
        tempDto.setDob(minMaxDob.getDob());
        tempDto.setTrenutnaTemperatura(temperaturaVlaga.getTemperatura());
        tempDto.setTrenutnaVlaga(temperaturaVlaga.getVlaga());
        tempDto.setMin(minMaxDob.getMin());
        tempDto.setMax(minMaxDob.getMax());
        tempDto.setAuto(minMaxDob.isAuto());
        tempDto.setPovijestTemperature(povijest.stream().map(this::map).toList());
        return tempDto;
    }

    private TempDto.PovijestTemperature map(TemperaturaVlaga temperaturaVlaga) {
        final TempDto.PovijestTemperature povijestTemperature = new TempDto.PovijestTemperature();
        povijestTemperature.setDatumVrijeme(temperaturaVlaga.getVrijeme());
        povijestTemperature.setTemperatura(temperaturaVlaga.getTemperatura());
        povijestTemperature.setVlaga(temperaturaVlaga.getVlaga());
        return povijestTemperature;
    }
}
