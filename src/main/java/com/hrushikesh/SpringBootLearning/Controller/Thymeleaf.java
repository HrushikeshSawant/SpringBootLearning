package com.hrushikesh.SpringBootLearning.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class Thymeleaf {
	private Logger log = LoggerFactory.getLogger(Thymeleaf.class);
	
	@GetMapping("/thymeleaf")
	public String test(Model m)
	{
		log.info("Inside test method");
		m.addAttribute("name", "Hrushikesh Sawant");
		m.addAttribute("city", "Mumbai");
		return "test";
	}

}
