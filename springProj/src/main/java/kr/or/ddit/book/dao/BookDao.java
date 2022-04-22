package kr.or.ddit.book.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.book.vo.BookVO;

//매퍼 xml을 실행해주는 클래스.
//어노테이션을 붙여서 이 클래스는 데이터에 접근하는 클래스라는 것을
//Spring에게 알려줌
//Spring이 데이터를 관리하는 클래스라고 인지해서 자바 빈(java bean)으로 등록해서 관리
@Repository
public class BookDao {
	//sqlSessionTemplate 사용 - root-context에서 가져옴
	/*
	 * new 키워드를 통해 직접 생성 안함.
	 * 의존성 주입(Dependency Injection - DI)을 통해 주입받음.
	 * 스프링은 미리 만들어 놓은 sqlSessionTemplate 타입 객체를 BookDao 객체에 주입
	 * 이 과정은 자동으로 스프링에서 실행되며, 개발자가 직접 객체를 생성하지 않음(IoC)
	 */
	@Autowired
	SqlSessionTemplate sqlSessionTemplate; 
	
	public int insert(BookVO bookVO) {
		//book_SQL.xml파일에서 
		//namespace="book"
		//id="insert"
		//book.insert : 매퍼 쿼리 명
		//bookVO : 두 번째 인수.. 쿼리에 전달할 데이터(String, int, VO, Map)
		return this.sqlSessionTemplate.insert("book.insert", bookVO);
	}
	
	//책 상세보기
	public BookVO detail(BookVO bookVO) {
		//.selectOne 메소드 : 1행을 가져올 때 사용
		//결과 행 수가 0이면 null을 반환
		//결과 행 수가 2이상일 때 TooManyResultException 예외 발생
		//(namespace.id, 파라미터 0
		return sqlSessionTemplate.selectOne("book.detail", bookVO);
	}
	
	//책 수정하기
	public boolean update(BookVO bookVO) {
		//(namespace.id, 파라미터)
		//update 후에 영향받은 행의 수를 받음
		int result = sqlSessionTemplate.update("book.update", bookVO);
		//0보다 크다는 것은 update가 성공했다는 의미
		return result > 0; //0보다 크면 true를 반환
	}
	
	//책 목록보기
	public List<BookVO> list(Map<String, Object> map){
		//.selectList() 메소드는 결과 집합 목록 반환
		//List 타입으로 읽어들일 수 있음
		return sqlSessionTemplate.selectList("book.list", map);
	}
	
	//책 삭제하기
	public boolean delete(Map<String, Object> map) {
		//RDBMS(Relational DataBase Management System)에서
		//delete 구문은 update 구문처럼 where 조건에 일치하는 
		//모든 행을 삭제하므로 영향을 받은 행의 수는 0혹은 1이상이 됨 
		int result = this.sqlSessionTemplate.delete("book.delete", map);
		//result > 0 는 삭제가 잘 되었다는 의미 
		return result > 0;
	}
	
}
