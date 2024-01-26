package com.nosetr.trade.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	
	@GetMapping("/about")
  public String getAboutPage() {
     return "Hallo";
  }
	
}
