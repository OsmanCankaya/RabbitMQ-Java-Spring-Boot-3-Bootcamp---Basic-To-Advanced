package com.course.rabbitmq.two.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

//@Service
//@EnableScheduling
public class RabbitmqScheduler {

	private static final Logger LOG = LoggerFactory.getLogger(RabbitmqScheduler.class);

	@Autowired
	private RabbitListenerEndpointRegistry registry;

	@Scheduled(cron = "0 15 6 * * *")
	public void stopAll() {
		registry.getListenerContainers().forEach(c -> {
			LOG.info("Stopping container {}", c);
			c.stop();
		});
	}

	@Scheduled(cron = "1 17 6 * * *")
	public void startAll() {
		registry.getListenerContainers().forEach(c -> {
			LOG.info("Starting container {}", c);
			c.start();
		});
	}

}
