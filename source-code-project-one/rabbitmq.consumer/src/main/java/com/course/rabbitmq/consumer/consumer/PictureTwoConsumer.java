package com.course.rabbitmq.consumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.course.rabbitmq.consumer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Service
public class PictureTwoConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(PictureTwoConsumer.class);

	@Autowired
	private ObjectMapper objectMapper;

	@RabbitListener(queues = { "q.picture.image", "q.picture.vector", "q.picture.filter", "q.picture.log" })
	public void listen(Message message) throws JsonMappingException, JsonProcessingException {
		var jsonString = new String(message.getBody());
		var p = objectMapper.readValue(jsonString, Picture.class);

		LOG.info("Consuming : {} with routing key : {}", p, message.getMessageProperties().getReceivedRoutingKey());
	}

}
