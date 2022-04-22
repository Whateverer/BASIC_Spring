package kr.or.ddit.book.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.book.vo.BookVO;

public interface BookMapper {
	public int insert(BookVO bookVO);
		//book_SQL.xml파일에서 
		//namespace="book"
		//id="insert"
		//book.insert : 매퍼 쿼리 명
		//bookVO : 두 번째 인수.. 쿼리에 전달할 데이터(String, int, VO, Map)
	//책 상세보기
	public BookVO detail(BookVO bookVO);
	
	//책 수정하기
	public boolean update(BookVO bookVO);
	
	//책 목록보기
	public List<BookVO> list(Map<String, Object> map);
	
	//책 삭제하기
	public boolean delete(Map<String, Object> map);
}
