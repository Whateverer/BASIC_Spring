package kr.or.ddit.book.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.book.vo.BookVO;

/*
 * 서비스 클래스는 비즈니스 클래스(기능)가 위치하는 곳임.
 * 스프링 MVC 구조에서 Contoller - Service - DAO 
 * 컨트롤러와 DAO를 연결하는 역할
 * 
 * 스프링은 직접 클래스를 생성하는 것을 지양(안함)하고, 
 * 인터페이스를 통해 접근하는 것을 권장하는 프레임워크.
 */
public interface BookService {
	//메소드 시그니처
	//book 테이블로 insert
	public int insert(BookVO bookVO);
	//book 상세보기
	public BookVO detail(BookVO bookVO);
	//book 수정하기
	public boolean update(BookVO bookVO);
	//book 목록보기
	public List<BookVO> list(Map<String, Object> map);
	//책 삭제하기
	public boolean delete(Map<String, Object> map);
}






