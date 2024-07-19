package com.example.ready;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ReadyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadyApplication.class, args);
	}
}
