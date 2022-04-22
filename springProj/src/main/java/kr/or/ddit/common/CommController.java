package kr.or.ddit.common;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//RestController : Controller + ResponseBody
@RestController
public class CommController {
	private static final Logger logger =
			LoggerFactory.getLogger(CommController.class);
	
	/**
	 * 공통 파일 다운로드
	 * @param fileName(규칙 : /download?fileName=/upload/.. 부터 파일명.확장자까지) 
	 * @return
	 */
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
	
}
