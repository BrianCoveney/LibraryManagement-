package ie.soft8020.librarymanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PaymentController {

	@RequestMapping(value="/payment", method=RequestMethod.GET)
	public String payment() {
		return "payment";
	}
	
}
