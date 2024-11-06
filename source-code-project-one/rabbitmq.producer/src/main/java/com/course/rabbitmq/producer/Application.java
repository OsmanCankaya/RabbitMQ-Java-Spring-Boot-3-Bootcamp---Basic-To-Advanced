package com.course.rabbitmq.producer;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.rabbitmq.producer.entity.Employee;
import com.course.rabbitmq.producer.producer.SpringEmployeeJsonProducer;

//@EnableScheduling
@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private SpringEmployeeJsonProducer producer;

	@Override
	public void run(String... args) throws Exception {
		var emp = new Employee("emp-spring", null, LocalDate.now());

		producer.sendMessage(emp);
	}

}
