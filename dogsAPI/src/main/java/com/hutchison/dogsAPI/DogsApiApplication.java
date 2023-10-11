package com.hutchison.dogsAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DogsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DogsApiApplication.class, args);
	}

}
