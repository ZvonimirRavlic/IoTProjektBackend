package com.example.iot;

import com.example.iot.mqtt.Mqtt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableScheduling
public class IoTApplication {

    public static void main(String[] args) {
        Executor executor = Executors.newSingleThreadExecutor();
        Mqtt mqtt1 = new Mqtt(1);
        executor.execute(mqtt1);
        Mqtt mqtt2 = new Mqtt(2);
        executor.execute(mqtt2);
        Mqtt mqtt3 = new Mqtt(3);
        executor.execute(mqtt3);
        SpringApplication.run(IoTApplication.class, args);
    }

}
