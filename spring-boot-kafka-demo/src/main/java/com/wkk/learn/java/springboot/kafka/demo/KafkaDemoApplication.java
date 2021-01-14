package com.wkk.learn.java.springboot.kafka.demo;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2021/1/14 12:56
 */
@SpringBootApplication
public class KafkaDemoApplication implements CommandLineRunner {

    public static Logger logger = LoggerFactory.getLogger(KafkaDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoApplication.class, args);
    }

    @Autowired
    private KafkaTemplate<String, String> template;

    private final CountDownLatch latch = new CountDownLatch(3);

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 1000; i++) {
            this.template.send("myTopic", 0, "1", "foo1");
            this.template.send("myTopic", 1, "2","foo2");
            this.template.send("myTopic", 2, "3","foo3");
        }
        latch.await(60, TimeUnit.SECONDS);
        logger.info("All received");
    }

    /**
     * 创建topic
     * @return
     */
    @Bean
    public NewTopic myTopic() {
        return new NewTopic("myTopic", 3, (short) 2);
    }

    /*@KafkaListener(topics = "myTopic", groupId = "test", topicPartitions = {
            @TopicPartition(topic = "myTopic", partitions = {"0"})
    })*/
    @KafkaListener(topics = "myTopic", groupId = "test")
    public void listen1(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info("1： {}", cr.toString());
        latch.countDown();
    }

    /*@KafkaListener(topics = "myTopic", groupId = "test", topicPartitions = {
            @TopicPartition(topic = "myTopic", partitions = {"1"})
    })*/
    @KafkaListener(topics = "myTopic", groupId = "test")
    public void listen2(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info("2： {}", cr.toString());
        latch.countDown();
    }

    /*@KafkaListener(topics = "myTopic", groupId = "test", topicPartitions = {
            @TopicPartition(topic = "myTopic", partitions = {"2"})
    })*/
    @KafkaListener(topics = "myTopic", groupId = "test")
    public void listen3(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info("3： {}", cr.toString());
        latch.countDown();
    }


    /*@KafkaListener(topics = "myTopic", groupId = "test", topicPartitions = {
            @TopicPartition(topic = "myTopic", partitions = {"2"})
    })*/
    @KafkaListener(topics = "myTopic", groupId = "test")
    public void listen4(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info("4： {}", cr.toString());
        latch.countDown();
    }
}
