package com.example.springmongo.api.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springmongo.api.model.UserModel;
import com.example.springmongo.api.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserModel foundUser = userRepository.findByUsername(username);
		if (foundUser == null) return null;
		
		String name = foundUser.getUsername();
		String password = foundUser.getPassword();
		
		return new User(name, password, new ArrayList<>());
	}
	
	
	/* 
	 * SECOND Youtube video
	 * 
	 * public interface UserDetailsManager extends UserDetailsService {
	 * 
	 * 		void createUser(UserDetails var1);
	 * 
	 * 		void updateUser(UserDeatils var1);
	 * 
	 * 		void deleteUser(String var1);
	 * 
	 * 		void changePassword(String var1, String var2);
	 * 
	 * 		boolean userExists(String var1);
	 * }
	 */
	
}
