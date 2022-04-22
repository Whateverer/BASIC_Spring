package kr.or.ddit.member.service;

import java.util.List;

import org.json.simple.JSONObject;

import kr.or.ddit.member.vo.MemberVO;

/* 
 * 스프링은 직접 클래스를 생성하는 것을 지양(안함)하고, 
 * 인터페이스를 통해 접근하는 것을 권장하는 프레임워크.
 */
public interface MemberService {
	//메소드 시그니처
	//회원 가입
	public int insert(MemberVO memberVO);

	public List<MemberVO> select();

	public int memberDupChk(String memberid);

	public List<MemberVO> detail(String memberid);

	public JSONObject memberMoney();
}
