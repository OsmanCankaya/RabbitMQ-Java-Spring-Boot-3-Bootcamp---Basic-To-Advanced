package com.course.rabbitmq.two.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

//@Configuration
public class RabbitmqSchemaConfig {

//	@Bean
//	FanoutExchange fanoutExchange() {
//		return new FanoutExchange("x.another-dummy", true, false, null);
//	}

//	@Bean
//	Queue queue() {
//		return new Queue("q.another-dummy");
//	}

//	@Bean
//	Binding binding() {
//		return new Binding("q.another-dummy", DestinationType.QUEUE, "x.another-dummy", "", null);
//		return BindingBuilder.bind(queue()).to(fanoutExchange());
//	}

	@Bean
	Declarables createRabbitmqSchema() {
		return new Declarables(new FanoutExchange("x.another-dummy", true, false, null), new Queue("q.another-dummy"),
				new Binding("q.another-dummy", DestinationType.QUEUE, "x.another-dummy", "", null));
	}

}
