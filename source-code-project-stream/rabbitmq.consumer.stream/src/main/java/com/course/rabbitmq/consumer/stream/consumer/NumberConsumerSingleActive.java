package com.course.rabbitmq.consumer.stream.consumer;

import java.util.concurrent.TimeUnit;

import org.apache.qpid.proton.amqp.messaging.Data;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.course.rabbitmq.consumer.stream.config.RabbitmqStreamConfig;
import com.rabbitmq.stream.Message;
import com.rabbitmq.stream.MessageHandler.Context;

//@Service
public class NumberConsumerSingleActive {

	@RabbitListener(queues = RabbitmqStreamConfig.STREAM_NUMBER, containerFactory = "singleActiveContainerFactoryOne")
	public void singleActiveOne(Message message, Context context) throws InterruptedException {
		var data = (Data) message.getBody();
		var logStr = String.format("single active 1 : %s, offset : %d", data.getValue(), context.offset());

		System.out.println(logStr);

		TimeUnit.MILLISECONDS.sleep(500);
	}

}
