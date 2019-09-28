package com.micro.app.ws.api.account.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
	 
	@GetMapping(path = "/{message}")
	public String message(@PathVariable String message) {
		return "Working " + message;
		
	}

}
