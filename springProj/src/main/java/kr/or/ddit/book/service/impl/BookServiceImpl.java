package kr.or.ddit.book.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.book.dao.BookDao;
import kr.or.ddit.book.mapper.BookMapper;
import kr.or.ddit.book.service.BookService;
import kr.or.ddit.book.vo.BookVO;

//어노테이션.. 스프링에게 이 클래스는 서비스 클래스임을 알려줌
//스프링이 자바 빈(java bean)으로 등록하여 관리
@Service
public class BookServiceImpl implements BookService {
	private static final Logger logger = 
			LoggerFactory.getLogger(BookServiceImpl.class);
	//IoC(제어의 역전)
	//DI(의존성 주입)
	@Autowired
	BookMapper bookMapper; //bookDao = new BookDao();나 getInstance가 필요없음
	//부모 객체의 메소드를 재정의
	@Override
	public int insert(BookVO bookVO) {
		logger.info("bookVO : " + bookVO.toString());
		int affectRowCount = this.bookMapper.insert(bookVO);
		
		if(affectRowCount == 1) {//입력이 성공
			//xml에서 selectKey에서 세팅된 그 값(max+1)
			return bookVO.getBookId();
		}
		//입력실패
		return 0;
	}

	//책 상세보기
	@Override
	public BookVO detail(BookVO bookVO) {
		return this.bookMapper.detail(bookVO);
	}
	
	//책 수정하기
	@Override
	public boolean update(BookVO bookVO) {
		return this.bookMapper.update(bookVO);
	}
	
	//책 목록보기
	@Override
	public List<BookVO> list(Map<String, Object> map){
		return this.bookMapper.list(map);
	}
	
	//책 삭제하기
	@Override
	public boolean delete(Map<String, Object> map) {
		return bookMapper.delete(map);
	}
}
