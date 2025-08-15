package com.jenish.Online.Food.Ordering.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jenish.Online.Food.Ordering.config.JwtProvider;
import com.jenish.Online.Food.Ordering.model.Cart;
import com.jenish.Online.Food.Ordering.model.USER_ROLE;
import com.jenish.Online.Food.Ordering.model.User;
import com.jenish.Online.Food.Ordering.repository.CartRepository;
import com.jenish.Online.Food.Ordering.repository.UserRepository;
import com.jenish.Online.Food.Ordering.request.LoginRequest;
import com.jenish.Online.Food.Ordering.response.AuthResponse;
import com.jenish.Online.Food.Ordering.service.CustomUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private CartRepository cartRepository;
	
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception{
		
		User isEmailExist = userRepository.findByEmail(user.getEmail());
		
		if(isEmailExist != null) {
			throw new Exception("Email is already used with another account");
		}
		
		User createdUser = new User();
		
		createdUser.setEmail(user.getEmail());
		createdUser.setFullName(user.getFullName());
		createdUser.setRole(user.getRole());
		createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User saveduser = userRepository.save(createdUser);
		
		Cart cart = new Cart();
		cart.setCustomer(saveduser);
		cartRepository.save(cart);
		
		Authentication authentication =  new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse();
		
		authResponse.setJwt(jwt);
		authResponse.setMessage("Register Success");
		authResponse.setRole(saveduser.getRole());
		
		return new ResponseEntity<>(authResponse , HttpStatus.CREATED);
		}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signIn(@RequestBody  LoginRequest request){
		
		String username = request.getEmail();
		String password = request.getPassword();
		
		Authentication authentication = authentiacte(username , password);
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String role = authorities.isEmpty() ? null :  authorities.iterator().next().getAuthority();
		String jwt = jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse();
		
		authResponse.setJwt(jwt);
		authResponse.setMessage("Login Success");
		authResponse.setRole(USER_ROLE.valueOf(role));
		
		return new ResponseEntity<>(authResponse , HttpStatus.OK);
		
		
	}

	private Authentication authentiacte(String username, String password) {
		
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
		
		if(userDetails == null) {
			throw new BadCredentialsException("Invalid username....");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("invalid password....");
		}

		return new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());
	}
	
	
}
