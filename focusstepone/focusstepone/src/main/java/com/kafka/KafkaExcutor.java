package com.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * focus Create in 14:29 2018/11/20
 */
public class KafkaExcutor implements Runnable{
    private KafkaProducer handle;
    private org.apache.kafka.clients.producer.KafkaProducer<String, byte[]> producer;

    public KafkaExcutor(KafkaProducer handle, Properties properties) {
        this.handle = handle;
        this.producer = new org.apache.kafka.clients.producer.KafkaProducer<String, byte[]>(properties);
    }

    public KafkaExcutor(KafkaProducer handle) {
        this.handle = handle;
        this.producer = new org.apache.kafka.clients.producer.KafkaProducer<String, byte[]>(handle.getProperties());
    }

    @Override
    public void run() {
        List messageQueue = null;

        while (!Thread.interrupted()) {
            try {
                messageQueue = this.handle.getMessageQueue();
                if (messageQueue != null && !messageQueue.isEmpty()) {
                    Iterator<KafkaMessage> iterator = messageQueue.iterator();
                    while (iterator.hasNext()) {
                        final KafkaMessage kafkaMessage = iterator.next();
                        ProducerRecord<String, byte[]> record = new ProducerRecord<String, byte[]>(kafkaMessage.getTopic(), kafkaMessage.getKey(), kafkaMessage.getMessage());
                        this.producer.send(record);
                        System.out.println("finish");
                    }
                } else {
                    Thread.sleep(10L);
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
