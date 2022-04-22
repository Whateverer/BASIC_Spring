package kr.or.ddit.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.BoardVO;

@RequestMapping("/board")
@Controller
public class BoardController {
	private static final Logger logger = 
			LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping("/list")
	public void list() {
		logger.info("list : access to all");
	}
	
	@RequestMapping("/register")
	public void registerForm() {
		logger.info("registerForm : access to member");
	}
	
	//JSON.stringify(boardObject)를 받을 수 있어야 함
	//JSON 데이터를 받을 땐 RequestBody를 사용함
	//ResponseEntity : 오브젝트
	//statusCode(상태 코드) + headers(HTTP 응답 데이터) + body(담을 데이터 오브젝트)
	@ResponseBody
	@RequestMapping(value = "/{boardNo}",method = RequestMethod.PUT)
	public ResponseEntity<String> modify(@PathVariable("boardNo") int boardNo,
			@RequestBody BoardVO boardVO){
		
		logger.info("boardVO : " + boardVO.toString());
		//body부분
		ResponseEntity<String> entity = 
				new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{boardNo}",method = RequestMethod.PUT
			,headers="X-HTTP-Method-Override=PUT")
	public ResponseEntity<String> modifyByHeader(@PathVariable("boardNo") int boardNo,
			@RequestBody BoardVO boardVO){
		
		logger.info("boardVO : " + boardVO.toString());
		ResponseEntity<String> entity = 
				new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
}






