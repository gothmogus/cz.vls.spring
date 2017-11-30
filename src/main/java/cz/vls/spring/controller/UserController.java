package cz.vls.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cz.vls.spring.model.mssql.User;
import cz.vls.spring.model.mssql.UserCard;
import cz.vls.spring.repositories.mssql.UserCardRepository;
import cz.vls.spring.repositories.mssql.UserRepository;

@RestController
@RequestMapping(path="/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserCardRepository userCardRepository;
	
	
	@GetMapping(path="/users")
	public @ResponseBody Iterable<User> getAllUsers() {
		
		// This returns a JSON or XML with the users
		return userRepository.findAll();
		
	}
	
	@GetMapping(path="/cards")
	public @ResponseBody Iterable<UserCard> getAllUserCards() {
		
		// This returns a JSON or XML with the userCards
		return userCardRepository.findAll();
		
	}
	
}
