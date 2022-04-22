package kr.or.ddit.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
	private static final Logger logger = 
			LoggerFactory.getLogger(CommonController.class);
	//Authentication : 인증(로그인)
	//Authorization  : 인가(접근 제어)
	//리턴타입이 void-> /accessError에 해당되는 /accessError.jsp를 forwarding해줌
	//접근 거부 처리자가 보낸 요청과 메소드를 매핑
	//<security:access-denied-handler error-page="/accessError" />
	@RequestMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		logger.info("access Denied : " + auth);
		
		model.addAttribute("msg", "Access Denied");
		
//		return "accessError";
	}
}
