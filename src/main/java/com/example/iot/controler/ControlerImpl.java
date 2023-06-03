package com.example.iot.controler;

import com.example.iot.dto.TempDto;
import com.example.iot.dto.TempUpdate;
import com.example.iot.service.IoTService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControlerImpl implements Controler {

    private final IoTService ioTService;

    public ControlerImpl(IoTService ioTService) {
        this.ioTService = ioTService;
    }

    @Override
    public TempDto getTemp(Integer id) {
        return ioTService.getTemp(id);
    }

    @Override
    public TempDto updateTemp(Integer id, TempUpdate tempUpdate) {
        return ioTService.updateTemp(id, tempUpdate);
    }
}
