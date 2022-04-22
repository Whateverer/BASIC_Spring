package kr.or.ddit.lprod.service.impl;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.BuyerVO;
import kr.or.ddit.lprod.mapper.LprodMapper;
import kr.or.ddit.lprod.service.LprodService;
import kr.or.ddit.lprod.vo.LprodVO;
import kr.or.ddit.member.vo.MemberVO;

@Service
public class LprodServiceImpl implements LprodService {
	private static final Logger logger = 
			LoggerFactory.getLogger(LprodServiceImpl.class);

	//DI
	@Autowired
	private LprodMapper lprodMapper;
//	LprodDao lprodDao; DAO가 LprodMapper로 변경됨
	
	//상품분류 별 거래처 목록
	//메소드 재정의
	@Override
	public List<LprodVO> list(Map<String, Object> map){
		return this.lprodMapper.list(map);
	}
	//상품분류 별 거래처 목록 행의 수
	@Override
	public int listCount(Map<String, Object> map) {
		return this.lprodMapper.listCount(map);
	}
	//거래처 상세 정보
	@Override
	public BuyerVO detail(String buyerId) {
		return this.lprodMapper.detail(buyerId);
	}
	//거래처 수정
	@Override
	public int modify(BuyerVO buyerVO) {
		return this.lprodMapper.modify(buyerVO);
	}
	//로그인 확인
	@Override
	public MemberVO checkLogin(MemberVO memberVO) {
		return this.lprodMapper.checkLogin(memberVO);
	}
	//상품 별 판매 금액 합계 차트
	@Override
	public JSONObject cartMoney(){
		List<Map<String,Object>> list = this.lprodMapper.cartMoney();
		
		logger.info("list : " + list.get(0).toString());
		
		//0. 최종적으로 리턴할 json 객체
		JSONObject data = new JSONObject();
		//1. cols 배열에 넣기
		JSONObject col1 = new JSONObject();
		JSONObject col2 = new JSONObject();
		
		JSONArray title = new JSONArray(); 
		col1.put("label", "상품명");
		col1.put("type", "string");
		col2.put("label", "금액");
		col2.put("type", "number");
		
		title.add(col1);
		title.add(col2);
		
		data.put("cols", title);
		
		//2. rows 배열에 넣기
		JSONArray body = new JSONArray();	//rows
		for(Map<String, Object> map : list) {
				
			//{"c":[{"v":"삼성 칼라 TV 29인치"}, {"v":14280000}]},;
			JSONObject prodName = new JSONObject();
			prodName.put("v", map.get("PROD_NAME"));
			JSONObject money = new JSONObject();
			money.put("v", map.get("MONEY"));	//금액
			
			JSONArray row = new JSONArray();
			row.add(prodName);
			row.add(money);
			
			JSONObject cell = new JSONObject();
			cell.put("c", row);
			
			body.add(cell); //레코드 1행 추가
		} //end for
		
		data.put("rows", body);
		
		return data;
	}
}
