package kr.or.ddit.member.service.impl;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.mapper.MemberMapper;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.vo.MemberVO;

//spring 프레임워크가 자바 빈으로 등록해서 관리 
@Service
public class MemberServiceImpl implements MemberService {
	private static final Logger logger = 
			LoggerFactory.getLogger(MemberServiceImpl.class);
	
	//DI(의존성 주입)
	//데이터베이스에 접근을 위해 MemberDao 인스턴스르 주입받음
	@Autowired
	MemberMapper memberMapper;

	//회원가입
	@Override
	public int insert(MemberVO memberVO) {
		return memberMapper.insert(memberVO);
	}

	//회원정보 상세
	@Override
	public List<MemberVO> detail(String memberid){
		return memberMapper.detail(memberid);
	}
	
	//회원정보 목록
	@Override
	public List<MemberVO> select(){
		return memberMapper.select();
	}
	
	//회원 아이디 중복 체킹
	@Override
	public int memberDupChk(String memberid) {
		return memberMapper.memberDupChk(memberid);
	}
	
	//회원 별 판매 금액 합계 차트
	@Override
	public JSONObject memberMoney() {
		List<Map<String, Object>> list = this.memberMapper.memberMoney();
		logger.info("여기를 보시오 : " + list.toString());
		//0. 최종적으로 리턴할 json 객체
		JSONObject data = new JSONObject();
		
		//1. cols 배열에 넣기
		JSONObject col1 = new JSONObject();
		JSONObject col2 = new JSONObject();
		
		JSONArray title = new JSONArray();
		col1.put("label", "회원명");
		col1.put("type", "string");
		col2.put("label", "매출금액");
		col2.put("type", "number");
		
		title.add(col1);
		title.add(col2);
		
		data.put("cols", title);
		
		//2. rows 배열에 넣기
		JSONArray body = new JSONArray(); // rows
		for(Map<String, Object> map : list) {
			
			JSONObject name = new JSONObject();
			name.put("v", map.get("NAME"));
			JSONObject money = new JSONObject();
			money.put("v", map.get("MONEY"));
			
			JSONArray row = new JSONArray();
			
			row.add(name);
			row.add(money);
			JSONObject cell = new JSONObject();
			
			cell.put("c", row);
			
			body.add(cell);
		}// end for
		
		data.put("rows", body);
		logger.info(data.toJSONString());
		return data;
	}
}











