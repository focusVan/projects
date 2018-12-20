package com.kafka;

import com.avro.AvroTest;
import com.avro.User;

import java.io.IOException;

/**
 * focus Create in 14:24 2018/11/21
 */
public class KafkaDemo {
    public static void main(String[] args) throws IOException {
        KafkaProducer kafkaProducer = new KafkaProducer("192.168.11.129:9092,192.168.11.130:9092,192.168.11.131:9092", 8);
        kafkaProducer.start();

        User.Builder user1 = User.newBuilder().setName("cloud").setFavoriteNumber(13).setFavoriteColor("white");
        User user2 = User.newBuilder().setName("focus").setFavoriteNumber(5).setFavoriteColor("black").build();
        User user3 = new User("name", 5, "color");
        kafkaProducer.sendMessage("test", String.valueOf(System.currentTimeMillis()), AvroTest.getAvroBytes(user3));
    }
}
