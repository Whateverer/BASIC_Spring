package kr.or.ddit.notice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/notice")
@Controller
public class NoticeController {
   
   private static final Logger logger =
         LoggerFactory.getLogger(NoticeController.class);
	/*
	 void 
	- 호출하는 URL과 동일한 뷰 이름을 나타냄
	- 요청이 /notice/list 이면, 뷰는 /notice/list.jsp를 가리킴.
	 */
   @RequestMapping("/list")
   public void list() {
      logger.info("list: access to all");
      
   }
   
   @RequestMapping("/register")
   public void registerForm() {
      logger.info("registerForm: access to admin");
   }
   
}