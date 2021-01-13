package com.wkk.learn.java.activemq.demo;

import org.apache.activemq.junit.EmbeddedActiveMQBroker;
import org.junit.Rule;
import org.junit.Test;

/**
 * @Description 内嵌ActiveMQ执行单元测试
 * @Author Wangkunkun
 * @Date 2021/1/13 16:14
 */
public class ActiveMQTest {

    @Rule
    public EmbeddedActiveMQBroker broker = new EmbeddedActiveMQBroker();

    @Test
    public void queueTest() {
        ActiveMQDemo.brokerUrl="vm://embedded-broker?create=false";
        ActiveMQDemo.queueTest();
    }
}
