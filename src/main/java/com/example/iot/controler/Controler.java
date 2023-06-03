package com.example.iot.controler;

import com.example.iot.dto.TempDto;
import com.example.iot.dto.TempUpdate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public interface Controler {

    @GetMapping("/sensor/{id}")
    TempDto getTemp(@PathVariable Integer id);

    @PostMapping(value = "/sensor/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    TempDto updateTemp(@PathVariable Integer id, @RequestBody TempUpdate predmetCreate);

}
