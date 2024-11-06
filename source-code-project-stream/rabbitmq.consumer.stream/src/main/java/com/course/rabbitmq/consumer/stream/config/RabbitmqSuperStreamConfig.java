package com.course.rabbitmq.consumer.stream.config;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.rabbit.stream.listener.StreamListenerContainer;

import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.OffsetSpecification;

//@Configuration
public class RabbitmqSuperStreamConfig {

	private static final Logger LOG = LoggerFactory.getLogger(RabbitmqSuperStreamConfig.class);

//	@Bean
	StreamListenerContainer superStreamNumberContainer() {
		var env = Environment.builder().maxConsumersByConnection(1).build();
		var container = new StreamListenerContainer(env);

		container.setConsumerCustomizer((id, builder) -> builder.offset(OffsetSpecification.first()));
		container.superStream("s.super.number", "my-super-stream-number-consumer");
		container.setupMessageListener(msg -> {
			LOG.info(new String(msg.getBody()));

			try {
				TimeUnit.SECONDS.sleep(4);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		return container;
	}

}
