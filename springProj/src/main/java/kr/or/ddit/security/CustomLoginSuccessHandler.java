package kr.or.ddit.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

//SavedRequestAwareAuthenticationSuccessHandler
//인증 전에 접근을 시도한 URL(/login)로 redirect하는 기능을 갖고 있음
//스프링 시큐리티에서 기본적으로 사용되는 구현 클래스
public class CustomLoginSuccessHandler extends
	SavedRequestAwareAuthenticationSuccessHandler{
	
	private static final Logger logger = 
			LoggerFactory.getLogger(CustomLoginSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		logger.warn("onAuthenticationSuccess");
		
		//User? 스프링 시큐리티에서 기본적으로 제공해주는 사용자 객체
		//loginForm.jsp의 계정 id와 비밀번호 정보를 authentication 객체로 받아옴
		User customUser = (User)authentication.getPrincipal();
		logger.info("username : " + customUser.getUsername());
		logger.info("password : " + customUser.getPassword());
		
		//view단과 request, response, authentication을 공유해서 쓰겠다.
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
