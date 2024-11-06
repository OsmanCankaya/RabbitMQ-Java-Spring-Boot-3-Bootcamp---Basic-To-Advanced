package com.course.rabbitmq.consumer.stream.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.stream.Message;
import com.rabbitmq.stream.MessageHandler.Context;

//@Service
public class NumberConsumerNext {

	private static final Logger LOG = LoggerFactory.getLogger(NumberConsumerNext.class);

//	@RabbitListener(queues = RabbitmqStreamConfig.STREAM_NUMBER, containerFactory = "nextContainerFactoryOne")
	public void nextOne(Message message, Context context) {
		LOG.info("next 1 : {}, on offset {}", message.getBody(), context.offset());
	}

//	@RabbitListener(queues = RabbitmqStreamConfig.STREAM_NUMBER, containerFactory = "nextContainerFactoryTwo")
	public void nextTwo(Message message, Context context) {
		LOG.info("next 2 : {}, on offset {}", message.getBody(), context.offset());
	}

}
