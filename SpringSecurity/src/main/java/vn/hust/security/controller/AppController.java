package vn.hust.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	
	@GetMapping({"/", "/login"})
	public String showHome() {
		return "login";
	}
	
	@GetMapping("/admin")
	public String showAdmin() {
		return "admin";
	}
	
	@GetMapping("/user")
	public String showUser() {
		return "user";
	}
	
	@GetMapping("/403")
	public String showError() {
		return "403";
	}
	
	@GetMapping("/register")
	public String showRegister() {
		return "register";
	}
	
	@GetMapping("/search")
	public String showSearch() {
		return "search";
	}

}
