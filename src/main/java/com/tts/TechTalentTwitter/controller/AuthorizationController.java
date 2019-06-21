package com.tts.TechTalentTwitter.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tts.TechTalentTwitter.model.User;
import com.tts.TechTalentTwitter.service.UserService;

@RequestMapping
@Controller
public class AuthorizationController {

	@Autowired
	private UserService userService;


	@GetMapping(value="/login")
	public String login(){
		return "login";
	}


	@GetMapping(value="/signup")
	public String registration(Model model){
		User user = new User();
		model.addAttribute("user", user);
		return "registration";
	}

	@PostMapping(value = "/signup")
	public String createNewUser(@Valid User formData, BindingResult bindingResult, Model model) {
		User userExists = userService.findByUsername(formData.getUsername());
		if (userExists != null) {
			bindingResult.rejectValue("username", "error.user", "Username is already taken");
		}
		if (!bindingResult.hasErrors()) {
			userService.saveNewUser(formData);
			model.addAttribute("success", "Sign up successful!");
			model.addAttribute("user", new User());
		}
		return "authentication/registration";
	}
}