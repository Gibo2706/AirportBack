package com.airport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("model")
public class AirportBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportBackApplication.class, args);
	}

}
