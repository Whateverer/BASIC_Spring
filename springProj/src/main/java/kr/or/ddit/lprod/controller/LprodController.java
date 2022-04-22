package kr.or.ddit.lprod.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.ArticlePage;
import kr.or.ddit.BuyerVO;
import kr.or.ddit.lprod.service.LprodService;
import kr.or.ddit.lprod.vo.LprodVO;
import kr.or.ddit.member.vo.MemberVO;

//모든 요청들이여 나에게로 오라.
@RequestMapping(value="/lprod")
@Controller
public class LprodController {
	//org.slf4j패키지..
	private static final Logger logger = 
			LoggerFactory.getLogger(LprodController.class);
	
	@Autowired
	LprodService lprodService;
	//http://localhost:8090/lprod/list?currentPage=1
	//defaultValue : 해당 요청 파라미터를 지정하지 않을 경우(http://localhost:8090/lprod/list)
	//defaultValue 속성에 지정한 문자열을 값으로 이용하게 됨
	//?keyWord=피혁
	//required=false : 파라미터가 없을 때 즉, 파라미터 자체가 null일 때
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model
			, @RequestParam(defaultValue="1") int currentPage
			, @RequestParam(required=false) String keyWord
			, @RequestParam(defaultValue="7",required=false) int size) {
		logger.info("currentPage : " + currentPage);
		logger.info("keyWord : " + keyWord);
		logger.info("size : " + size);
		
		//<key,value>
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("keyWord", keyWord);
		map.put("currentPage", currentPage);
		map.put("size", size);
		
		List<LprodVO> list = this.lprodService.list(map);
		logger.info("list.size() : " + list.size());
		//상품분류 별 거래처 목록 행의 수
		int total = this.lprodService.listCount(map);
		logger.info("total : " + total);
		
		//new ArticlePage(total, currentPage, size, pagingCount, content)
		model.addAttribute("list", 
				new ArticlePage(total, currentPage, size, 5, list));
		model.addAttribute("total", total);
		
		//forward
		return "lprod/list";
	}
	
	/*
	 RequestMapping 정리
	 - 요청 경로는 필수!
	 - 속성이 하나일 때는 속성명 생략 가능 "/detail"
	 - 클래스 레벨과 메서드 레벨로 지정 가능
	 - 클래스 레벨로 요청 경로를 지정하면 메서드 레벨에서 지정한 경로의 기본 경로로 취급
	 */
	// /lprod/detail?buyerId=P10101
	//리턴 타입 : String => 뷰 파일의 경로와 파일 이름을 나타내기 위해 사용
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String detail(Model model, @RequestParam String buyerId) {
		logger.info("buyerId : " + buyerId);
		
		BuyerVO buyerVO = this.lprodService.detail(buyerId);
		//buyerVO이 null이면 오류를 보정
		if(buyerVO!=null) {
			logger.info("buyerVO : " + buyerVO.toString());
		}
		
		model.addAttribute("buyerVO", buyerVO);
		
		//forward
		//반환값이 lprod/detail이므로 lprod폴더의 detail.jsp를 리턴
		return "lprod/detail";
	}
	//수정 요청 매핑
	//스프링 프레임워크에서 제공해주는 MultipartFile 타입!
	@RequestMapping(value="/detail",method=RequestMethod.POST)
	public String detailPost(@Validated BuyerVO buyerVO,
			BindingResult result, MultipartFile[] uploadFile) {
		logger.info("buyerVO : " + buyerVO.toString());
		//validation 중에 문제가 발생하면 true
		logger.info("result.hasErrors() : " + result.hasErrors());
		//validation 문제가 발생된다면
		if(result.hasErrors()) {
			List<ObjectError> allErrors = result.getAllErrors();
			List<ObjectError> globalErrors = result.getGlobalErrors();
			List<FieldError> fieldErrors = result.getFieldErrors();
			//validation 중에 어떤 오류가 나왔는지 확인..
			for(int i=0;i<allErrors.size();i++) {
				ObjectError objectError = allErrors.get(i);
				logger.info("objectError : " + objectError);
			}
			
			for(ObjectError objectError : globalErrors) {
				logger.info("objectError : " + objectError);
			}
			
			for(FieldError fieldError : fieldErrors) {
				logger.info("fieldError : " + fieldError.getDefaultMessage());
			}
			//문제가 발생되면 수정프로세스는 중단되고 forward
			return "lprod/detail";
		}//end if
		
		//파일 업로드 처리 시작-----------------------------
		//파일 저장 경로 설정
	      String uploadFolder = "D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\springProj\\src\\main\\webapp\\resources\\upload";
	      
			//연/월/일 폴더 생성 시작-------
			File uploadPath = new File(uploadFolder, getFolder());
			logger.info("uploadPath : " + uploadPath);
			
			if(uploadPath.exists()==false) {//해당 경로가 없으면 생성해줘야함
				uploadPath.mkdirs();			
			}
			//연/월/일 폴더 생성 끝-------
			
			for(MultipartFile multipartFile : uploadFile) {
				logger.info("-----------");
				logger.info("파일명 : " + multipartFile.getOriginalFilename());
				logger.info("파일크기 : " + multipartFile.getSize());
							
				//-----------UUID 파일명 처리 시작 ----------------------------
				//동일한 이름으로 업로드되면 기존 파일을 지우게 되므로 이를 방지하기 위함
				UUID uuid = UUID.randomUUID();
				
				String uploadFileName = uuid.toString() + "-" + multipartFile.getOriginalFilename();
				// c:\\upload\\gongu03.jpg으로 조립
				// 이렇게 업로드 하겠다라고 설계 uploadFolder -> uploadPath
				File saveFile = new File(uploadPath,uploadFileName);
				//-----------UUID 파일명 처리 끝 ----------------------------
				
				try {
					//transferTo() : 물리적으로 파일 업로드가 됨
					multipartFile.transferTo(saveFile);
					String path = "/upload/"+getFolder()+"/"+uploadFileName;
					// /upload/2022/02/11/e687b1a1-a8e9-4d02-b279-2f5a8a402e72-cloud03.jpg
					logger.info("buyer_img 경로 : " + path);
					buyerVO.setBuyerImg(path);
				}catch(Exception e) {
					logger.info(e.getMessage());
				}//end catch
			}//end for
			//파일 업로드 처리 끝-----------------------------
			
			logger.info("buyerVO : " + buyerVO.toString());
			
			//수정 비즈니스 로직 처리 후 -> 몇 건이 update되었나..
			int modifyResult = this.lprodService.modify(buyerVO);
			
			if(modifyResult<1) {//수정 실패
				return "lprod/detail";
			}
			
			//상세페이지로 되돌아감
			return "redirect:/lprod/detail?buyerId="+buyerVO.getBuyerId();
		}
	
	//MemberVO -> memberVO라는 model attribute명으로 자동 model에 등록
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String login(MemberVO memberVO, Model model) {
//		model.addAttribute("memberVO", memberVO); 위에 파라미터로 넘겨주던, addAttribute로 저장하던 같다.
		//forwarding
		return "lprod/login";
	}
	
	//로그인 버튼 클릭했을 때
	//login/login?memberid=a001&password=asdfasdf
	//public String loginpost(@RequestParam String memberid, String password) - 파라미터로 받을 수 있음
	//public String loginpost(@RequestParam Map<String, String> map)
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginPost(@ModelAttribute@Validated MemberVO memberVO, 
			Model model, HttpServletRequest request) {
		logger.info("MemberVO : " + memberVO.toString());
		
		
		//결과
		MemberVO member = this.lprodService.checkLogin(memberVO);
		
		if(member==null) { //아이디 &비밀번호 오류
			model.addAttribute("message", "아이디&비밀번호 오류입니다.");
			logger.info("로그인 실패!!!");
			return "lprod/login";
		}else { //로그인 처리
			//세션 객체를 받아옴
			HttpSession session = request.getSession();
			//세션의 존속 시간을 10분으로 설정(기본은 30분)
			session.setMaxInactiveInterval(10);//10분
			//세션의 속성명으로 member를 사용하여 member객체를 세팅함
			session.setAttribute("member", member);
			
			logger.info("로그인 성공");
			//redirect - 검색창에 직접 lprod/list를 입력하는 것과 같다.
			return "redirect:/lprod/list";
		}
		
	}
	
	//어노테이션 RequestMapping(value="/lprod/logout", method=RequestMethod=POST)
	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		//세션을 모두 초기화 
		session.invalidate();
		
		//redirect
		return "redirect:/lprod/login";
	}
	
	@ResponseBody
	@RequestMapping(value="/download", 
			produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(@RequestParam String fileName){
		///resources/upload/ksh.jpg
		//Java의 정규표현식에서 \와 $ 사용할 때, 오류 발생
		logger.info("download file : " + fileName);
		String downloadPath = "D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\springProj\\src\\main\\webapp\\resources" + 
				fileName.replaceAll("/", Matcher.quoteReplacement("\\"));
		
		logger.info("downloadPath : " + downloadPath);
		
		Resource resource = new FileSystemResource(downloadPath);
		
		logger.info("resource : " + resource);
		
		String resourceName = resource.getFilename();
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			//Content-Disposition : 파일 이름에 한글인 경우 저장 시 깨지는 문제를 막기 위함
			headers.add("Content-Disposition", "attachment;filename=" + new String
					(resourceName.getBytes("UTF-8"), "ISO-8859-1"));
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);

	}
		
	//첨부파일을 보관하는 폴더를 연/월/일 계층 형태로 생성하기 위함
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", "/");
	}
}








