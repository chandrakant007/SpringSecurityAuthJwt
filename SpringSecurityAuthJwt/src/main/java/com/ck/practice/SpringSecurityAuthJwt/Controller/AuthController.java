package com.ck.practice.SpringSecurityAuthJwt.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ck.practice.SpringSecurityAuthJwt.DAO.UserDetailsDaoImpl;
import com.ck.practice.SpringSecurityAuthJwt.Services.JwtService;
import com.ck.practice.SpringSecurityAuthJwt.proxy.AuthRequest;
import com.ck.practice.SpringSecurityAuthJwt.proxy.AuthResponse;

//AuthController.java
@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final UserDetailsDaoImpl userDetailsService;
	private final JwtService jwtService;

	public AuthController(AuthenticationManager authenticationManager, UserDetailsDaoImpl userDetailsService,
			JwtService jwtService) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwtService = jwtService;
	}

	// JWT grant: username/password → JWT access token
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getUsername(),
				request.getPassword());

		Authentication authentication = authenticationManager.authenticate(authToken);

		// If we get here, user is authenticated
		// Optionally store it in the context (not mandatory for login itself)
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String jwt = jwtService.generateToken(userDetails);

		return ResponseEntity.ok(new AuthResponse(jwt));

//		System.out.println("==============> request.getUsername()::"+request.getUsername());
//		System.out.println("==============> request.getPassword()::"+request.getPassword());
//		Authentication authentication = new UsernamePasswordAuthenticationToken(request.getUsername(),
//				request.getPassword());
//		
//		System.out.println("==============> authentication::"+authentication);
//
//		// Delegates to AuthenticationProvider (with UserDetailsDaoImpl)
//		authenticationManager.authenticate(authentication);
//		
//		System.out.println("==============> Before authorization");
//
//		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
//		
//		System.out.println("==============> After authorization");
//		
//		System.out.println("==============> userDetails.getUsername::"+userDetails.getUsername());
//		System.out.println("==============> userDetails.getPassword()::"+userDetails.getPassword());
//
//		String jwt = jwtService.generateToken(userDetails);
//		
//		System.out.println("==============> jwt::"+jwt);
//
//		return ResponseEntity.ok(new AuthResponse(jwt));
	}

	@GetMapping("/login/test")
	public String loginTest() {

		System.out.println("==============> loginTest calling...");

		return "OK";
	}

}
