package com.course.rabbitmq.producer.stream.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.QueueBuilder;

//@Configuration
public class RabbitmqSchemaConfig {

//	@Bean
	Declarables rabbitmqSchema() {
		var exchange = ExchangeBuilder.fanoutExchange("x.number").durable(true).build();
		var queue = QueueBuilder.durable("s.number").stream().build();
		var binding = BindingBuilder.bind(queue).to(exchange).with("").noargs();

		return new Declarables(exchange, queue, binding);
	}

}
