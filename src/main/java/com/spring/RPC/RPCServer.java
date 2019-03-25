package com.spring.RPC;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RPCServer {
    private static final String RPC_QUEUE_NAME = " rpc_queue ";
    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 5672;//RabbitMQ 服务端默认端口号为5672

    public static void main(String args[]) throws Exception {
        Address[] address = new Address[]{new Address(IP_ADDRESS, PORT)};
        ConnectionFactory factory = new ConnectionFactory() ;
        factory.setUsername( "guest" );
        factory.setPassword( "guest");
        Connection connection = factory.newConnection(address);//创建连接
        final Channel channel = connection.createChannel();//创建信道
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery( String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body ) throws IOException {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(properties.getCorrelationId())
                        .build();
                String response = "";
                try {
                    String message = new String(body, "UTF-8");
                    int n = Integer.parseInt(message);
                    System.out.println(" [.] fib(" + message +")");
                    response += fib(n);
                } catch (UnsupportedEncodingException e) {
                    System.out.println(" [.] " + e.toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } finally {
                    channel.basicPublish("", properties.getReplyTo(), replyProps, response.getBytes("UTF-8"));
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        channel.basicConsume(RPC_QUEUE_NAME, false, consumer);
    }

    private static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }
}
