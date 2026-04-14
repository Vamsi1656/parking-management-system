package com.example.city_service.City_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CityServiceApplication {

	@Bean
	public RestTemplate restTemplate(){
	return new RestTemplate();
	}
	public static void main(String[] args) {

		SpringApplication.run(CityServiceApplication.class, args);
	}
}
