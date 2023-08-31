package com.khayayphyu.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private static Logger logger = LogManager.getLogger(HomeController.class);

	@RequestMapping("")
	public String index(Model model) {
		logger.info("Home controller");
		return "welcome";
	}
}
