package br.com.northon.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * GreetingsController
 */
@RestController
public class MathController {

	@GetMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(@PathVariable(value="numberOne") String numberOne, 
			@PathVariable(value="numberTwo") String numberTwo) throws Exception {
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new Exception();
		}
		
		return convertToDouble(numberOne) + convertToDouble(numberTwo);
		
	}

	private Double convertToDouble(String numberOne) {
		if(numberOne == null) return 0D;
		
		if(isNumeric(numberOne)) return Double.parseDouble(numberOne);
			
		return 0D;
	}

	private boolean isNumeric(String numberOne) {
		if(numberOne == null) return false;
		
		return numberOne.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
    
}