package com.mobiquity.mobtravelapp;

import com.mobiquity.mobtravelapp.service.NsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MobtravelappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobtravelappApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
