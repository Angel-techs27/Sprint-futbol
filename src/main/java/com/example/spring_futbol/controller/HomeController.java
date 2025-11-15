package com.example.spring_futbol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		// Muestra la página de inicio con el menú
		return "index";
	}
}
