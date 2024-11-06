package com.course.rabbitmq.producer.stream.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rabbitmq.stream.Environment;

@Configuration
public class RabbitmqStreamJsonConfig {

	public static final String STREAM_INVOICE_NAME = "s.invoice";

//	@Bean
	Declarables rabbitmqSchema() {
		var exchange = ExchangeBuilder.fanoutExchange("x.invoice").durable(true).build();
		var queue = QueueBuilder.durable(STREAM_INVOICE_NAME).stream().build();
		var binding = BindingBuilder.bind(queue).to(exchange).with("").noargs();

		return new Declarables(exchange, queue, binding);
	}

	@Bean
	ObjectMapper objectMapper() {
		return JsonMapper.builder().findAndAddModules().build();
	}

	@Bean
	Jackson2JsonMessageConverter converter(@Autowired ObjectMapper objectMapper) {
		return new Jackson2JsonMessageConverter(objectMapper);
	}

//	@Bean
	RabbitStreamTemplate streamInvoiceTemplate(Environment env, Jackson2JsonMessageConverter jsonConverter) {
		var template = new RabbitStreamTemplate(env, STREAM_INVOICE_NAME);
		template.setMessageConverter(jsonConverter);

		return template;
	}

}
