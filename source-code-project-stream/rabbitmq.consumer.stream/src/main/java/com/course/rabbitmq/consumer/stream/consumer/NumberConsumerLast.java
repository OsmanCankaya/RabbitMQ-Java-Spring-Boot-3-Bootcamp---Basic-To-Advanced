package com.course.rabbitmq.consumer.stream.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.stream.Message;
import com.rabbitmq.stream.MessageHandler.Context;

//@Service
public class NumberConsumerLast {

	private static final Logger LOG = LoggerFactory.getLogger(NumberConsumerLast.class);

//	@RabbitListener(queues = RabbitmqStreamConfig.STREAM_NUMBER, containerFactory = "lastContainerFactoryOne")
	public void lastOne(Message message, Context context) {
		LOG.info("last 1 : {}, on offset {}", message.getBody(), context.offset());
	}

//	@RabbitListener(queues = RabbitmqStreamConfig.STREAM_NUMBER, containerFactory = "lastContainerFactoryTwo")
	public void lastTwo(Message message, Context context) {
		LOG.info("last 2 : {}, on offset {}", message.getBody(), context.offset());
	}

}
