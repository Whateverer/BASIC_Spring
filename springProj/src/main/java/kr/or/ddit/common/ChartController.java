package kr.or.ddit.common;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.lprod.service.LprodService;

@RequestMapping("/chart")
@Controller
public class ChartController {
	@Autowired
	LprodService lprodService;
	
	@GetMapping("/chartMain")
	public String chartMain() {
		//forwarding
		return "chart/chartMain";
	}
	
	@GetMapping("/chart01")
	public String chart01() {
		//forwarding
		return "chart/chart01";
	}
	
	@GetMapping("/chartFruit")
	public String chartFruit() {
		//forwarding
		return "chart/chartFruit";
	}
	
	@GetMapping("/chart02")
	public String chart02() {
		//forwarding
		return "chart/chart02";
	}
	
	//ResponseBody 어노테이션을 쓰면 JSON 데이터로 리턴함
	@ResponseBody
	@RequestMapping("/chart02_money")
	public JSONObject chart02_money() {
		return this.lprodService.cartMoney();
	}

	
}
