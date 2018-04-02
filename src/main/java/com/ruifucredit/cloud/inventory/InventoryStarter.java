package com.ruifucredit.cloud.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryStarter {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(InventoryStarter.class, args);
	}
	
}
