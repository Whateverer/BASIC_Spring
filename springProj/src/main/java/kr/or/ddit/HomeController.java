package kr.or.ddit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@GetMapping("/ajaxHome")
	public String ajaxHome() {
		return "ajaxHome";
	}
	
	
	
}
