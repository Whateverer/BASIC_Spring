package kr.or.ddit;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//매퍼 xml을 실행해주는 클래스.
//어노테이션을 붙여서 이 클래스는 데이터에 접근하는 클래스라는 것을
//Spring에게 알려줌
//Spring이 데이터를 관리하는 클래스라고 인지해서 자바 빈(java bean)으로 등록해서 관리
@Repository
public class BuyerDao {
	//sqlSessionTemplate 사용 - root-context에서 가져옴
	/*
	 * new 키워드를 통해 직접 생성 안함.
	 * 의존성 주입(Dependency Injection - DI)을 통해 주입받음.
	 * 스프링은 미리 만들어 놓은 sqlSessionTemplate 타입 객체를 BookDao 객체에 주입
	 * 이 과정은 자동으로 스프링에서 실행되며, 개발자가 직접 객체를 생성하지 않음(IoC)
	 */
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	//거래처 별 상품목록
	public List<BuyerVO> list(){
		//namespace.id
		return this.sqlSessionTemplate.selectList("buyer.list");
	}
	
	//Lprod
	public List<BuyerVO> lpordList(){
		return this.sqlSessionTemplate.selectList("buyer.lprodList");
	}
}
