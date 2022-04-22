package kr.or.ddit.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginContoller {
	private static final Logger logger = 
			LoggerFactory.getLogger(LoginContoller.class);
	
	//<security:form-login login-page="/login" />
	//오류 메시지와 로그아웃 메시지를 파라미터로 사용
	@RequestMapping("/login")
	public String loginForm(String error, String logout, Model model) {
		logger.info("error : " + error);
		logger.info("logout : " + logout);
		
		if(error!=null) {//에러가 있을 때
			model.addAttribute("error", "Login Error!");
		}
		
		if(logout!=null) {
			model.addAttribute("logout", "Logout되었습니다");
		}
		
		//forwarding
		return "loginForm";
	}
	
	@RequestMapping("/logout")
	public String logoutForm() {
		//forwarding
		return "logoutForm";
	}
}
