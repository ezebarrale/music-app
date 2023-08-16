package com.dicsys.tfinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan( basePackages = {"com.dicsys.tfinal"} )
public class TfinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TfinalApplication.class, args);
	}

}
