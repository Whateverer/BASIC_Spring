package kr.or.practice;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.AttachFileVO;

@RequestMapping("/uploadTest")
@Controller
public class Practice0210 {
	private static final Logger logger = 
			LoggerFactory.getLogger(Practice0210.class);
	
	//GetMapping 과 PostMapping
	@PostMapping("/uploadFormAction")
	public String uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		//파일 저장 위치 설정
		String uploadFolder = "D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\springProj\\src\\main\\webapp\\resources\\upload";
		//파일이름 저장할 list 생성
		List<String> list = new ArrayList<String>();
		//JSP에서 받아온 파일 하나씩 꺼내오기
		for(MultipartFile multipartFile : uploadFile) {
			logger.info("-----------");
			logger.info("파일명: " + multipartFile.getOriginalFilename());
			logger.info("파일크기 : " + multipartFile.getSize());
			
			//파일객체생성해서 담기 파일(경로, 파일명)
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				//파일 실제이름을 list에 담음
				list.add(multipartFile.getOriginalFilename());
				//물리적으로 파일에 담기
				multipartFile.transferTo(saveFile);
				
			}catch(Exception e) {
				logger.info(e.getMessage());
			}
		}
		
		model.addAttribute("list", list);
		
		return "uploadTest/uploadSuccess";
	}
	
	//uploadTest/uploadAjax
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		
	}
	
	//<form> 태그를 이용하던 방식과 동일한 방식으로 처리됨
	//Ajax 방식으로 결과 데이터를 전달하므로 Model을 사용하지 않음.
	
	@ResponseBody
	@PostMapping("/uploadAjaxAction")
	public List<AttachFileVO> uploadAjaxAction(MultipartFile[] uploadFile){
		String uploadFolder =  "경로";
		
		//연/월/일 폴더생성 시작-------
		File uploadPath = new File(uploadFolder, getFolder());
		logger.info("uploadPath" + uploadPath);
		
		if(uploadPath.exists() == false) {//해당 경로가 없으면 생성
			uploadPath.mkdirs();
		}
		//연/월/일 폴더생성 끝-------
		
		//첨부된 파일의 이름을 담을 List
		List<AttachFileVO> list = new ArrayList<AttachFileVO>();
		
		for(MultipartFile multipartFile : uploadFile) {
			logger.info("");
			
			AttachFileVO attachFileVO = new AttachFileVO();
			
			// 1) fileName 세팅
			attachFileVO.setFileName(multipartFile.getOriginalFilename());
			
			//------------UUID 파일명 처리 시작----------------
			// 동일한 이름으로 업로드되면 기존 파일을 지우게 되므로 이를 방지하기 위함
			UUID uuid = UUID.randomUUID();
			
			String uploadFileName = uuid.toString() + "-" + multipartFile.getOriginalFilename();
			//c:\\upload\\gongu03.jpg로 조립
			// 이렇게 업로드 하겠다고 설계 uploadFolder => uploadPath
			File saveFile = new File(uploadPath, uploadFileName);
			//------------UUID 파일명 처리 끝----------------
			
			try {
				
				//transforTo() : 물리적으로 파일 업로드가 됨
				multipartFile.transferTo(saveFile);
				
				//2) uploadPath
				attachFileVO.setUploadPath(uploadPath.getPath());
				//3) uuid
				attachFileVO.setUuid(uuid.toString());
				
				//-----썸네일 시작
				//이미지 파일인지 체킹
				if(checkImageType(saveFile)) {
					
				}else {
					
				}
				//-----썸네일 끝
				
				//파일 객체(AttachFileVO)를 list에 담음
				list.add(attachFileVO);
			}catch(Exception e) {
				logger.info("");
			}//end catch
		}//end for
		return list;
	}
	
	//첨부파일을 보관하는 폴더를 연/월/일 계층 형태로 생성하기 위함
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
	
	//특정한 파일이 이미지 타입인지 검사해주는 메소드
	private boolean checkImageType(File file) {
		try {
			//file.toPath() : 파일의 전체 경로
			logger.info("file.toPath" + file.toPath());
			String contentType = Files.probeContentType(file.toPath());
			//contentType이 image로 시작하면 이미지 타입이므로 true를 리턴
			return contentType.startsWith("image");		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}










