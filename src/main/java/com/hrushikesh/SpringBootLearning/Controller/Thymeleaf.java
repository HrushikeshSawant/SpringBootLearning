package com.hrushikesh.SpringBootLearning.Controller;

import java.util.List;

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

	@GetMapping("/thymeleaf-iteration")
	public String iteration(Model m)
	{
		log.info("Inside iteration method");
		
		List<String> lst = List.of("ABC", "DEF", "GHI", "JKL", "123", "456");
		m.addAttribute("list", lst);
		return "iteration";
	}
}
