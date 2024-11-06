package com.course.rabbitmq.consumer.stream.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.stream.Message;
import com.rabbitmq.stream.MessageHandler.Context;

//@Service
public class NumberConsumerAbsolute {

	private static final Logger LOG = LoggerFactory.getLogger(NumberConsumerAbsolute.class);

//	@RabbitListener(queues = RabbitmqStreamConfig.STREAM_NUMBER, containerFactory = "absoluteContainerFactoryOne")
	public void absoluteOne(Message message, Context context) {
		LOG.info("absolute 1 : {}, on offset {}", message.getBody(), context.offset());
	}

//	@RabbitListener(queues = RabbitmqStreamConfig.STREAM_NUMBER, containerFactory = "absoluteContainerFactoryTwo")
	public void absoluteTwo(Message message, Context context) {
		LOG.info("absolute 2 : {}, on offset {}", message.getBody(), context.offset());
	}

}
