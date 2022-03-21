package com.udacity.eurekaserver;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.udacity.eurekaserver.domain.Price;
import com.udacity.eurekaserver.domain.PriceRepository;

@SpringBootApplication
@EnableEurekaClient
public class PricingServiceApplication {

	
	public static void main(String[] args) {
		
		SpringApplication.run(PricingServiceApplication.class, args);
	}

}
