package com.ck.practice.SpringSecurityAuthJwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityAuthJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAuthJwtApplication.class, args);
	}

}

/**
*
* @SpringBootApplication is combination of 3 annotation
* 
* @SpringBootConfiguration          // Configuration क्लास
* @EnableAutoConfiguration         // Auto‑configuration enable
* @ComponentScan                   // Components scan (same package + sub‑packages)
*
*
*/