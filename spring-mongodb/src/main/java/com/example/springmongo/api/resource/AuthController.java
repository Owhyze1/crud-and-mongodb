package com.example.springmongo.api.resource;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmongo.api.configurations.AuthenticationRequest;
import com.example.springmongo.api.configurations.AuthenticationResponse;
import com.example.springmongo.api.model.UserModel;
import com.example.springmongo.api.repository.UserRepository;

@RestController
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/subs")
	private ResponseEntity<?> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest) {

		String username = authenticationRequest.getUsername();
		
		
		// make sure that user doesn't already exist		
		UserModel userExists = userRepository.findByUsername(username);
		
		if (userExists == null)	{
			
			String password = authenticationRequest.getPassword();
			UserModel userModel = new UserModel();
			userModel.setUsername(username);
			userModel.setPassword(password);
			
			try {
				userRepository.save(userModel);			
			} catch (Exception e) {
				return ResponseEntity.ok(new AuthenticationResponse("Error during client subscription " + username));
			}
			return ResponseEntity.ok(new AuthenticationResponse("Successful Subscription for client " + username));	
		}				
		return ResponseEntity.ok(new AuthenticationResponse("User already exists")); 
	}
	
	
	@PostMapping("/auth")
	private ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest) {
		
		String username = authenticationRequest.getUsername();
		String password = authenticationRequest.getPassword();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));			
		} catch (Exception e) {
			return ResponseEntity.ok(new AuthenticationResponse("Error during client authentication " + username));
		}		
		return ResponseEntity.ok(new AuthenticationResponse("Successful authentication for client " + username));	
	}
	
	
	
}
