package com.inventra.inventra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {
		"com.inventra.inventra.entity",
		"com.inventra.inventra.model"
})
public class InventraApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventraApplication.class, args);
	}
}
