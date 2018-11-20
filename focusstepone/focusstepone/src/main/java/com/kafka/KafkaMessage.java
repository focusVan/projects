package com.kafka;

/**
 * focus Create in 13:56 2018/11/20
 */
public class KafkaMessage {
    private String topic;
    private byte[] message;
    private String key;

    public KafkaMessage(String topic, String key, byte[] message) {
        this.topic = topic;
        this.key = key;
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
