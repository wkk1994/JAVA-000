package com.wkk.learn.java.activemq.demo;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.TransactionContext;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

/**
 * @Description 对ActiveMQ 消息的生产和消费使用示例
 * @Author Wangkunkun
 * @Date 2021/1/13 14:09
 */
public class ActiveMQDemo {

    public static String brokerUrl = "tcp://127.0.0.1:61616";

    private static final String queueName = "queue.test";

    private static final String topicName = "topic.test";

    public static void main(String[] args) throws InterruptedException {
        queueTest();
        topicTest();
    }

    /**
     * queue测试
     */
    public static void queueTest() {
        sendQueueMessage();
        consumerQueueMessage();
    }

    /**
     * topic测试
     */
    public static void topicTest() {
        new Thread(() -> {consumerTopicMessage();}).run();
        new Thread(() -> {consumerTopicMessage();}).run();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendTopicMessage();
    }

    /**
     * 发送queue消息示例
     */
    public static void sendQueueMessage() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(queueName);

            //发送消息
            for (int i = 0; i < 10; i++) {
                MessageProducer producer = session.createProducer(queue);
                TextMessage textMessage = new ActiveMQTextMessage();
                textMessage.setText("test message" + i);
                producer.send(textMessage);
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 消费queue消息示例
     */
    public static void consumerQueueMessage() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            // 设置为手动ack
            ActiveMQSession session = (ActiveMQSession)connection.createSession(true, Session.SESSION_TRANSACTED);
            TransactionContext transactionContext = session.getTransactionContext();
            Queue queue = session.createQueue(queueName);
            MessageConsumer consumer = session.createConsumer(queue);
            consumer.setMessageListener(message -> {
                try {
                    transactionContext.begin();
                    String text = ((TextMessage) message).getText();
                    System.out.println("message：" + text);
                    transactionContext.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        transactionContext.rollback();
                    } catch (JMSException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 发送topic消息示例
     */
    public static void sendTopicMessage() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(topicName);

            //发送消息
            for (int i = 0; i < 10; i++) {
                MessageProducer producer = session.createProducer(topic);
                TextMessage textMessage = new ActiveMQTextMessage();
                textMessage.setText("test message" + i);
                producer.send(textMessage);
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 消费topic消息示例
     */
    public static void consumerTopicMessage() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            // 设置为手动ack
            ActiveMQSession session = (ActiveMQSession)connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(topicName);
            MessageConsumer consumer = session.createConsumer(topic);
            consumer.setMessageListener(message -> {
                try {
                    String text = ((TextMessage) message).getText();
                    System.out.println("message：" + text);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
        }
    }
}
