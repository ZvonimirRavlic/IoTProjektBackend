package com.example.iot.service;

import com.example.iot.dto.TempDto;
import com.example.iot.dto.TempUpdate;
import com.example.iot.entiteti.AutoTemp;
import com.example.iot.entiteti.MinMaxDob;
import com.example.iot.entiteti.TemperaturaVlaga;
import com.example.iot.mapper.Mapper;
import com.example.iot.repository.AutoTempRepository;
import com.example.iot.repository.MinMaxDobRepository;
import com.example.iot.repository.TemperaturaVlagaRepository;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class IoTServiceImpl implements IoTService {

    private final TemperaturaVlagaRepository temperaturaVlagaRepository;
    private final AutoTempRepository autoTempRepository;
    private final MinMaxDobRepository minMaxDobRepository;
    private final Mapper mapper;

    public IoTServiceImpl(TemperaturaVlagaRepository temperaturaVlagaRepository,
                          AutoTempRepository autoTempRepository,
                          MinMaxDobRepository minMaxDobRepository,
                          Mapper mapper) {
        this.temperaturaVlagaRepository = temperaturaVlagaRepository;
        this.autoTempRepository = autoTempRepository;
        this.minMaxDobRepository = minMaxDobRepository;
        this.mapper = mapper;
    }

    @Override
    public TempDto getTemp(Integer id) {
        final MinMaxDob minMaxDob = minMaxDobRepository.findById(id).orElse(new MinMaxDob(id));
        final TemperaturaVlaga temperaturaVlaga = temperaturaVlagaRepository.findFirstBySensorIdOrderByVrijemeDesc(id);
        final List<TemperaturaVlaga> povijest = temperaturaVlagaRepository.findBySensorIdOrderByVrijemeAsc(id);
        final TempDto tempDto = mapper.map(minMaxDob, temperaturaVlaga, povijest);
        minMaxDobRepository.save(minMaxDob);
        return tempDto;
    }

    @Override
    public TempDto updateTemp(Integer id, TempUpdate tempUpdate) {
        if (tempUpdate.getMinTemperatura() > tempUpdate.maxTemperatura) {
            throw new IllegalArgumentException("Minimalna temperatura veca od maksimalne");
        }
        final MinMaxDob minMaxDob = minMaxDobRepository.findById(id).orElse(new MinMaxDob(id));
        minMaxDob.setDob(tempUpdate.getDob());
        minMaxDob.setMin(tempUpdate.getMinTemperatura());
        minMaxDob.setMax(tempUpdate.getMaxTemperatura());
        minMaxDob.setAuto(tempUpdate.isAutoTemperatura());
        minMaxDob.setVrijemePocetka(LocalDateTime.now().minusDays(tempUpdate.getDob()));
        changeMinMax(id);
        minMaxDobRepository.save(minMaxDob);
        return getTemp(id);
    }

    private void changeMinMax(Integer id) {
        final MinMaxDob minMaxDob = minMaxDobRepository.findById(id).orElseThrow(() -> new RuntimeException("Ne postoje postavke za taj uredjaj"));
        minMaxDob.setDob((int) ChronoUnit.DAYS.between(minMaxDob.getVrijemePocetka(), LocalDateTime.now()));
        if (minMaxDob.isAuto()) {
            final AutoTemp autoTemp = autoTempRepository.findFirstByDobGreaterThanEqualOrderByDobAsc(minMaxDob.getDob());
            minMaxDob.setMin(autoTemp.getMin());
            minMaxDob.setMax(autoTemp.getMax());
        }
        minMaxDobRepository.save(minMaxDob);

        String broker = "tcp://test.mosquitto.org:1883";
        String clientid = "controler"+ MqttAsyncClient.generateClientId();
        String topicMax = "pilici/" + id + "/maksimalnaTemperatura";
        String topicMin = "pilici/" + id + "/minimalnaTemperatura";

        int qos = 0;

        try {
            MqttClient client = new MqttClient(broker, clientid, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setConnectionTimeout(60);
            options.setKeepAliveInterval(60);
            // connect
            client.connect(options);
            MqttMessage messageMax = new MqttMessage(minMaxDob.getMax().toString().getBytes());
            messageMax.setQos(qos);
            // publish message
            client.publish(topicMax, messageMax);
            System.out.println("Message published");
            System.out.println("topic: " + topicMax);
            System.out.println("message content: " + minMaxDob.getMax().toString());

            MqttMessage messageMin = new MqttMessage(minMaxDob.getMin().toString().getBytes());
            messageMin.setQos(qos);
            // publish message
            client.publish(topicMin, messageMin);
            System.out.println("Message published");
            System.out.println("topic: " + topicMin);
            System.out.println("message content: " + minMaxDob.getMin().toString());
            // disconnect
            client.disconnect();
            // close client
            client.close();
        } catch (MqttException ignored) {
        }
    }

    @Scheduled(initialDelay = 0, fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    private void updateTemps() {
        System.out.println("Updejt svih temperatura u: " + LocalDateTime.now());
        List<MinMaxDob> minMaxDobs = minMaxDobRepository.findAll();
        minMaxDobs.forEach((minMaxDob) -> changeMinMax(minMaxDob.getId()));
    }
}
