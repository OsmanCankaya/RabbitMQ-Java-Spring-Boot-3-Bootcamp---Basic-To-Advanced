package com.course.rabbitmq.consumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.course.rabbitmq.consumer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Service
public class EmployeeJsonConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeJsonConsumer.class);

	@Autowired
	private ObjectMapper objectMapper;

	@RabbitListener(queues = "course.employee")
	public void listen(String message) throws JsonMappingException, JsonProcessingException {
		var emp = objectMapper.readValue(message, Employee.class);

		LOG.info("Employee is {}", emp);
	}

}
