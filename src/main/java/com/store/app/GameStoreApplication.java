package com.store.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class GameStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameStoreApplication.class, args);
	}

}
