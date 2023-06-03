package com.example.iot.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;

public class Mqtt implements Runnable {

    private final int sensorId;

    public Mqtt(int sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public void run() {
        System.out.println("Unutra");
        String broker = "tcp://test.mosquitto.org:1883";
        String clientid = "controler" + sensorId;
        int qos = 0;

        try {
            MqttClient client = new MqttClient(broker, clientid, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setConnectionTimeout(60);
            options.setKeepAliveInterval(60);
            client.setCallback(new MqttCallback() {

                public void connectionLost(Throwable cause) {
                    System.out.println("connectionLost: " + cause.toString());
                }

                public void messageArrived(String topic, MqttMessage message) {
                    System.out.println("Message arrived on sensor: " + sensorId);
                    System.out.println("Message content: " + new String(message.getPayload()));
                    save(new String(message.getPayload()));

                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("deliveryComplete: " + token.isComplete());
                }

            });
            client.connect(options);
            client.subscribe("pilici/" + sensorId + "/trenutneVrijednosti", qos);
            System.out.println("Klijent je povezan:" + client.isConnected());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void save(String tempVlaga) {

        String url = "jdbc:postgresql://localhost:5432/IoTProjekt";
        String user = "zvonimirravlic";
        String password = "1738";
        String[] arr = tempVlaga.split(",");
        final double temp = Double.parseDouble(arr[0]);
        final double vlaga = Double.parseDouble(arr[1]);
        int id = 0;
        String query = "INSERT INTO public.temperatura_vlaga (id, vrijeme, temperatura, vlaga, sensor_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT nextval('temperatura_vlaga_id_seq');")) {

            if (rs.next()) {
                id = Integer.parseInt(rs.getString(1));
            }

        } catch (SQLException ignored) {
        }

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            pst.setDouble(3, temp);
            pst.setDouble(4, vlaga);
            pst.setInt(5, sensorId);
            pst.executeUpdate();

        } catch (SQLException ignored) {
        }

    }
}