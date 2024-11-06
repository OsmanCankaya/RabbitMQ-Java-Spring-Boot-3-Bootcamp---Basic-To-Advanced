package com.course.rabbitmq.two;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.rabbitmq.two.producer.StreamHelloProducer;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private StreamHelloProducer producer;

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 3; i++) {
			var str = "Hello stream XXXXX " + i;
			producer.sendHelloUsingExchange(str);
		}
	}

}
