package com.ck.practice.SpringSecurityAuthJwt.DAO;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ck.practice.SpringSecurityAuthJwt.Repo.UserRepository;
import com.ck.practice.SpringSecurityAuthJwt.Services.AppUserDetails;
import com.ck.practice.SpringSecurityAuthJwt.model.UserEntity;

//UserDetailsDaoImpl.java
@Service
public class UserDetailsDaoImpl implements UserDetailsService {

	private final UserRepository userRepository;

	public UserDetailsDaoImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// Called by AuthenticationProvider during username/password auth
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

		return new AppUserDetails(user);
	}
}
