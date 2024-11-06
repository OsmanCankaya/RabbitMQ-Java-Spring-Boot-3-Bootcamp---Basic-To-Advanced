package com.course.rabbitmq.consumer.stream.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.stream.Message;
import com.rabbitmq.stream.MessageHandler.Context;

//@Service
public class NumberConsumerFirst {

	private static final Logger LOG = LoggerFactory.getLogger(NumberConsumerFirst.class);

//	@RabbitListener(queues = RabbitmqStreamConfig.STREAM_NUMBER, containerFactory = "firstContainerFactoryOne")
	public void firstOne(Message message, Context context) {
		LOG.info("first 1 : {}, on offset {}", message.getBody(), context.offset());
	}

//	@RabbitListener(queues = RabbitmqStreamConfig.STREAM_NUMBER, containerFactory = "firstContainerFactoryTwo")
	public void firstTwo(Message message, Context context) {
		LOG.info("first 2 : {}, on offset {}", message.getBody(), context.offset());
	}

//	@RabbitListener(queues = RabbitmqStreamConfig.STREAM_NUMBER, containerFactory = "firstContainerFactoryThree")
	public void firstThree(Message message, Context context) {
		LOG.info("first 3 : {}, on offset {}", message.getBody(), context.offset());

		context.storeOffset();
	}

}
