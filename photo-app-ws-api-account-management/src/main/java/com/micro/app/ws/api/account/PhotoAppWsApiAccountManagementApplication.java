package com.micro.app.ws.api.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PhotoAppWsApiAccountManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAppWsApiAccountManagementApplication.class, args);
	}

}
