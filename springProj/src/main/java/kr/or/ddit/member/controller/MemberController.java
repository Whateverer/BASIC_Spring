package kr.or.ddit.member.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;

import org.json.simple.JSONObject;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.vo.CardVO;
import kr.or.ddit.member.vo.MemberVO;
//클래스 레벨 매핑
@RequestMapping(value="/member")
//자바빈으로 관리하겠다
@Controller
public class MemberController {
	private static final Logger logger = 
			LoggerFactory.getLogger(MemberController.class);
	//DI(Dependency Injection : 의존성 주입)
	@Autowired
	MemberService memberService;
	//컨트롤러의 insert메서드의 매개변수로 자바빈즈 객체가 전달이 되면 
	//기본적으로 다시 화면(view(jsp))으로 전달함
	//컨트롤러와 뷰 사이에 자바빈즈 객체를 서로 공유함
	//1) 폼 객체의 속성명을 직접 지정
	//2) 폼 객체의 속성명은 직접 지정하지 않으면 * 폼 객체의 클래스명의 맨 처음 문자를 소문자로 변환하여 처리
	//3) ModelAttribute 애너테이션으로 폼 객체의 속성명을 gaeddong라고 지정했다면.. 
	//스프링 폼의 modelAttribute 속성의 값도 똑같이 gaeddong라고 작성해줘야 함 
	//MemberVO -> memberVO
	@RequestMapping("/insert")
	public String insert(Model model, @ModelAttribute("memberVO") MemberVO memberVO) {
		//modelAttribute="memberVO" - jsp에서 사용하기 전에 Controller에서 미리 정의되어있어야 한다.
		//폼 객체의 속성명과 스프링 폼 태그의 modelAttribute 속성값이 같아야 함. 
		//방법1) model.addAttribute("memberVO", new MemberVO());
		//방법2) insert의 파라미터로 MemberVO를 넘겨준다.
		//폼 객체의 프로퍼티 값을 지정..
		//모델을 통해서 뷰(jsp)로 전달이 됨
		memberVO.setMemberid("a001");
		memberVO.setName("개똥이");
		//<form:password -> 값을 설정해서 뷰(jsp)에 전달하더라도 패스워드 필드에 반영되지 않음
		memberVO.setPassword("java");
		memberVO.setEmail("test@test.com");
		
		String introduction = "안녕하세요.\n반갑습니다.";
		memberVO.setIntroduction(introduction);
		
		//취미 세팅
		Map<String, String> hobbyMap = new HashMap<String, String>();
		hobbyMap.put("01", "야구");
		hobbyMap.put("02", "BTS Music");
		hobbyMap.put("03", "스파이더맨 시리즈");
		
		model.addAttribute("hobbyMap", hobbyMap); //Model객체에 Attribute로 hobbyMap설정
		
		//성별 세팅
		Map<String, String> genderMap =
				new HashMap<String, String>();
		genderMap.put("Male", "남성");
		genderMap.put("Female", "여성");
		genderMap.put("Other", "기타");
		
//		session.setAttribute("gender", "genderMap");
		model.addAttribute("genderMap", genderMap);
		
		return "member/joinForm";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insertPost(@Validated MemberVO memberVO,
			BindingResult result, Model model) {
		//파일 업로드 처리 시작--------------------------------
		String uploadFolder = "D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\springProj\\src\\main\\webapp\\resources\\upload";
		
			//연월일 폴더 생성
			File uploadPath = new File(uploadFolder, getFolder());
			
			if(uploadPath.exists()==false) {
				uploadPath.mkdirs(); //연월일 폴더 생성
			}
			
			MultipartFile[] multipartFiles = memberVO.getUploadFile();
			
			for(MultipartFile multipartFile : multipartFiles) {
				logger.info("Upload File Name : " + multipartFile.getOriginalFilename());
				//uploadFileName => C:\Users\\Downloads\ksh.jsp
				//lastIndexOf("\\")+1 => k
				//.substring => ksh.jpg
				String uploadFileName = multipartFile.getOriginalFilename();
				uploadFileName 
				= uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
				
				//경로 설정
				String path = "/resources/upload/"+getFolder()+"/"+uploadFileName;
				
				UUID uuid = UUID.randomUUID();
				//asadfasdfasdfasdf_ksh.jpg
				uploadFileName = uuid.toString() + "_" + uploadFileName;
				
				memberVO.setMemberCertPic(path);
				
				File saveFile = new File(uploadPath, uploadFileName);
				
				try {
					//물리적인 파일로 생성
					multipartFile.transferTo(saveFile);
				}catch(Exception e) {
					logger.warn(e.getMessage());
				}//end catch
			}// end for
		//파일 업로드 처리 끝--------------------------------
			
		logger.info(memberVO.toString());
		
		List<String> hobbyList = memberVO.getHobbyList();
		for(String hobby : hobbyList) {
			logger.info(hobby);
		}
		String[] hobbyArray = memberVO.getHobbyArray();
		for(String hobby:hobbyArray) {
			logger.info(hobby);
		}
		//성별
		logger.info(memberVO.getGender());
		
		//입력값 검증 후 BindingResult가 제공하는 메서드를 이용하여 검사 결과를 확인
		//result.hasErrors : 검증 오류 발생 시 
		if(result.hasErrors()) {
			//취미 세팅
			Map<String, String> hobbyMap = new HashMap<String, String>();
			hobbyMap.put("01", "야구");
			hobbyMap.put("02", "BTS Music");
			hobbyMap.put("03", "스파이더맨 시리즈");
			
			model.addAttribute("hobbyMap", hobbyMap); //Model객체에 Attribute로 hobbyMap설정
			
			//성별 세팅
			Map<String, String> genderMap =
					new HashMap<String, String>();
			genderMap.put("Male", "남성");
			genderMap.put("Female", "여성");
			genderMap.put("Other", "기타");
			
//			session.setAttribute("gender", "genderMap");
			model.addAttribute("genderMap", genderMap);
			
			//forwarding
			return "member/joinForm";
		}else {	//입력 검증 통과
			//회원 아이디가 중복되었는지 체킹
			int dupChkResult = memberService.memberDupChk(memberVO.getMemberid());
			
			if(dupChkResult > 0) {//중복된 아이디
				model.addAttribute("dupChkResult", "아이디가 중복되었습니다.");
				
				//취미 세팅
				Map<String, String> hobbyMap = new HashMap<String, String>();
				hobbyMap.put("01", "야구");
				hobbyMap.put("02", "BTS Music");
				hobbyMap.put("03", "스파이더맨 시리즈");
				
				model.addAttribute("hobbyMap", hobbyMap); //Model객체에 Attribute로 hobbyMap설정
				
				//성별 세팅
				Map<String, String> genderMap =
						new HashMap<String, String>();
				genderMap.put("Male", "남성");
				genderMap.put("Female", "여성");
				genderMap.put("Other", "기타");
				
//				session.setAttribute("gender", "genderMap");
				model.addAttribute("genderMap", genderMap);
				
				//forwarding
				return "member/joinForm";
			}else {//중복되지 않은 아이디
				//회원 등록 처리
				int rslt = memberService.insert(memberVO);
			
				//뷰의 경로 (mav.setViewName("member/result");)
				//forwarding
				return "member/result";
			}
		}
	}
//	@RequestMapping(value="/member/list", method=RequestMethod.GET)
//	public ModelAndView list(ModelAndView mav) { //모델과 뷰의 경로
	//속성이 하나이면 생략이 가능함
	@RequestMapping("/list")
	public String list(Model model) {
		List<MemberVO> memberVO = this.memberService.select();
		
		//1) 데이터
		model.addAttribute("list", memberVO);
		
		//2) 뷰의경로. forwarding방식(데이터를 끌어감)
		return "member/list";
	}
	
	/*
	  	경로(path) 변수(Variable)
		- 요청 경로를 동적으로 표현이 가능한 경로 패턴으로 지정할 수 있음
		- URL 경로 상의 변하는 값을 경로 변수로 취급
		- 경로 변수에 해당하는 값을 파라미터 변수에 설정할 수 있음
	 */
	@RequestMapping("/detail/{memberid}")
	public String detail(@PathVariable("memberid") String memberid,
			Model model) {
		List<MemberVO> memberVO = this.memberService.detail(memberid);
		
		//1
		MemberVO vo2 = memberVO.get(0);
		//다
		List<CardVO> cardVO = vo2.getCardList();
		
		//회원정보(1)
		model.addAttribute("memberVO", vo2);
		//카드정보(N)
		model.addAttribute("cardVO", cardVO);
		
		//forwarding
		return "member/detail";
	}
	
	//첨부파일 뭐시기 - 보관하는 폴더를 연/월/일 계층 형태로 생성하기 위함
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", "/");
	}
	
	//파일 다운로드
	//APPLICATION_OCTET_STREAM_VALUE : MIME 타입
	/*
	 
	  1) text/plain : 텍스트 파일. 인간이 읽을 수 있는 타입(이진데이터가 없음)
	  2) application : 
	  
	*/
	
	@ResponseBody
	@RequestMapping(value="/download"
			, produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(@RequestParam String fileName){
		///resources/upload/ksh.jpg
		//Java의 정규표현식에서 \와 $ 사용할 때, 오류 발생
		logger.info("download file : " + fileName);
		String downloadPath = "D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\springProj\\src\\main\\webapp" + 
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
	
	//memberAmout 차트를 조회
	@RequestMapping("/memAmount")
	public String memAmount() {
		return "member/memAmount";
	}
	
	//차트 요청
	@ResponseBody
	@RequestMapping("/memAmount_money")
	public JSONObject memAmount_money() {
		return this.memberService.memberMoney();
	}
	
}









