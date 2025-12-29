package com.creazy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CreaZyApplication {

	public static void main(String[] args) {

		SpringApplication.run(CreaZyApplication.class, args);
	}
	@GetMapping("/creaZy")
	public String CreaZy(){


        return "Welcome to creaZy 👋";
    }

}
