package com.kafka;

import org.apache.spark.Partitioner;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * focus Create in 13:54 2018/11/20
 */
public class KafkaProducer {
    private int maxThreadCount;
    private ExecutorService executorService;
    private Properties properties;
    private List<KafkaMessage> messageQueue;

    public KafkaProducer(String brokers, int maxThreadCount) {
        this.maxThreadCount = maxThreadCount;
        this.properties = new Properties();
        properties.put("bootstrap.servers", brokers);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        properties.put("acks", "1");
        properties.put("retries", Integer.valueOf(3));

        this.messageQueue = new LinkedList<>();
        this.executorService = new ThreadPoolExecutor(this.maxThreadCount, this.maxThreadCount,0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    public boolean sendMessage(String topic, byte[] message) {
        return this.sendMessage(topic, Long.toString(System.currentTimeMillis()), message);
    }

    public boolean sendMessage(String topic, String key, byte[] message) {
        try {
            synchronized (this) {
                if (this.messageQueue != null) {
                    this.messageQueue.add(new KafkaMessage(key, topic, message));
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    public List<KafkaMessage> getMessageQueue() {
        return messageQueue;
    }

    public void setMessageQueue(List<KafkaMessage> messageQueue) {
        this.messageQueue = messageQueue;
    }

    public boolean start() {
        try {
            for (int i = 0; i < this.maxThreadCount; i++) {
                executorService.submit(new KafkaExcutor(this));
            }
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
