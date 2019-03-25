package com.spring.RPC;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class RPCClient {
    /*private Connection connection ;
    private Channel channel ;
    private String requestQueueName = " rpc queue ";
    private String replyQueueName ;
    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 5672;//RabbitMQ 服务端默认端口号为5672
    //private QueueingConsumer consumer ;
    public RPCClient() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(IP_ADDRESS);
        connectionFactory.setPort(PORT);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        Connection connection = connectionFactory.newConnection(); // 创建连接
        Channel channel = connection.createChannel(); // 创建信道
        replyQueueName = channel.queueDeclare().getQueue();
        //consumer = new QueueingConsumer(channel);
        channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel));
    }

    public String call(String message) throws IOException {
        String response = null;
        String corrId = UUID.randomUUID().toString();
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();
        channel.basicPublish("", requestQueueName, props, message.getBytes());
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            if (delivery.getProperties().getCorrelationld().equals(corrld)) {
                response = new String(deliverygetBody());
                break;
            }
        }
        return response;
    }

    public void close() throws IOException {
        connection.close();
    }

    public static void main( String[] args ) throws IOException, TimeoutException {
        RPCClient fibRpc = new RPCClient();
        System.out.println(" [x) Requesting fib(30)");
        String response = fibRpc.call("30");
        System.out.println(" [.) Got '" +response+ "'" );
        fibRpc.close() ;
    }*/
}
