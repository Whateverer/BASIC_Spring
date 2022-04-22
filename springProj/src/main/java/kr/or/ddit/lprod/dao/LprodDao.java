package kr.or.ddit.lprod.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.BuyerVO;
import kr.or.ddit.lprod.vo.LprodVO;
import kr.or.ddit.member.vo.MemberVO;

@Repository
public class LprodDao {
	//sqlSessionTemplate 사용 - root-context에서 가져옴
	/*
	 * new 키워드를 통해 직접 생성 안함.
	 * 의존성 주입(Dependency Injection - DI)을 통해 주입받음.
	 * 스프링은 미리 만들어 놓은 sqlSessionTemplate 타입 객체를 BookDao 객체에 주입
	 * 이 과정은 자동으로 스프링에서 실행되며, 개발자가 직접 객체를 생성하지 않음(IoC)
	 */
	@Autowired
	SqlSessionTemplate sqlSessionTemplate; 
	
	//상품분류 별 거래처 목록
	public List<LprodVO> list(Map<String, Object> map){
		return this.sqlSessionTemplate.selectList("lprod.list", map);
	}
	
	//상품분류 별 거래처 목록 행의 수
	public int listCount(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("lprod.listCount", map);
	}
	
	//거래처 상세 정보
	public BuyerVO detail(String buyerId) {
		return this.sqlSessionTemplate.selectOne("lprod.detail", buyerId);
	}
	
	//거래처 수정
	public int modify(BuyerVO buyerVO) {
		//mapper.xml의 namespace.id
		return this.sqlSessionTemplate.update("lprod.modify", buyerVO);
	}
	
	//로그인 확인
	public MemberVO checkLogin(MemberVO memberVO) {
		return this.sqlSessionTemplate.selectOne("lprod.checkLogin", memberVO);
	}
}
