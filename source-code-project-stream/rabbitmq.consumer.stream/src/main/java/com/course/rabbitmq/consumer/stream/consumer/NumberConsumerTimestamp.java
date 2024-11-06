package com.course.rabbitmq.consumer.stream.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.stream.Message;
import com.rabbitmq.stream.MessageHandler.Context;

//@Service
public class NumberConsumerTimestamp {

	private static final Logger LOG = LoggerFactory.getLogger(NumberConsumerTimestamp.class);

//	@RabbitListener(queues = RabbitmqStreamConfig.STREAM_NUMBER, containerFactory = "timestampContainerFactoryOne")
	public void timestampOne(Message message, Context context) {
		LOG.info("timestamp 1 : {}, on offset {}", message.getBody(), context.offset());
	}

//	@RabbitListener(queues = RabbitmqStreamConfig.STREAM_NUMBER, containerFactory = "timestampContainerFactoryTwo")
	public void timestampTwo(Message message, Context context) {
		LOG.info("timestamp 2 : {}, on offset {}", message.getBody(), context.offset());
	}

}
