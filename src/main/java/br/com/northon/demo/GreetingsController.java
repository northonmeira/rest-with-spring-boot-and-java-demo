package br.com.northon.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * GreetingsController
 */
@RestController
public class GreetingsController {

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name", defaultValue = "world") String name) {
		
		return new Greeting(0, name);
		
	}
    
}