package com.ck.practice.SpringSecurityAuthJwt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SpringSecurityAuthJwtApplicationTests {

	@Test
	void contextLoads() {
	}

	
	@Test
	void checkPassword() {
	    PasswordEncoder encoder = new BCryptPasswordEncoder();
	    System.out.println(encoder.matches("password123",
	            "$2a$10$dM4sX9q1dPHOQADCB2.4eu8DfbnLnUxeRDZ2GOXgENIbtPGkWgLKu"));
	    
	    System.out.println(encoder.encode("password123"));//$2a$10$dM4sX9q1dPHOQADCB2.4eu8DfbnLnUxeRDZ2GOXgENIbtPGkWgLKu
	}
}
