package com.kt.backendapp;

import com.kt.backendapp.config.GbisOpenApiProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableConfigurationProperties(GbisOpenApiProps.class)

public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
