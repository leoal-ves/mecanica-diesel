package com.projeto2.mecanica_diesel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MecanicaDieselApplication {

	public static void main(String[] args) {
		SpringApplication.run(MecanicaDieselApplication.class, args);
	}

}
