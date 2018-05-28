package com.learn.currencyconversionservice;


import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


/*
* This application demonstrates how to call another micro service from here using RestTemplate and FeignClients.
* @EnableFeignClients is not required if we call other MS through RESTTemplate.
* */

@SpringBootApplication
@EnableFeignClients("com.learn.currencyconversionservice")
@EnableDiscoveryClient //Register with Name Server
@EnableHystrix // Hystrix enabling
public class CurrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}


    //Sleuth -Assign a unique id to each request
    @Bean
    public Sampler defaultSampler(){
        return Sampler.ALWAYS_SAMPLE;
    }
}
