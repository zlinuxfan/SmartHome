package com.schkv.smarthome.web.servises;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class MqttService implements MqttCallback {

    private final int qos = 0;
    private final String topic;
    private final MqttClient client;
    private final BlockingQueue<String> messages;

    public MqttService(String url, int port, String topic, BlockingQueue<String> messages) throws MqttException {
        String host = String.format("tcp://%s:%d", url, port);
        String clientId = "MQTT-Java-web" + topic;
        this.topic = topic;
        this.messages = messages;
        MqttConnectOptions conOpt = new MqttConnectOptions();
        conOpt.setCleanSession(true);

        this.client = new MqttClient(host, clientId, new MemoryPersistence());
        this.client.setCallback(this);
        this.client.connect(conOpt);

        this.client.subscribe(this.topic, qos);
    }

    public void close() throws MqttException {
        this.client.unsubscribe(topic);
        this.client.disconnect();
        this.client.close();
    }

    public void sendMessage(String payload) throws MqttException {
        MqttMessage message = new MqttMessage(payload.getBytes());
        message.setQos(qos);
        this.client.publish(this.topic, message); // Blocking publish
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("Connection lost because: " + cause);
        System.exit(1);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        messages.add(new String(message.getPayload()));
    }

    public static void main(String[] args) throws MqttException, InterruptedException {
        BlockingQueue<String> messages = new ArrayBlockingQueue<>(10);
        MqttService s = new MqttService(
                "192.168.31.77", 1883, "test", messages);

        s.sendMessage("Hello");
        s.sendMessage("Hello 2");

        String message;
        while (! (message = messages.take()).equals("exit")) {
            System.out.println(message);
        }
        s.close();
    }
}
