package com.np.monkeypoxtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MonkeypoxtrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonkeypoxtrackerApplication.class, args);
	}

}
