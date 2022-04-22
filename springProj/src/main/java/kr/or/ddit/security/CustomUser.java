package kr.or.ddit.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import kr.or.ddit.member.vo.MemberAuth;
import kr.or.ddit.member.vo.MemberVO;

public class CustomUser extends User {

	private MemberVO member;
	
	public CustomUser(String username, String password
			, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	//return member==null?null:new CustomUser(member);
	//사용자가 정의한 MemberVO 타입을 
	//스프링 시큐리티 UsersDetails 타입으로 변환
	public CustomUser(MemberVO member) {
		//super(username, password, authorities);
//		super(member.getMemberid(), member.getPassword(),
//				member.getAuthList().stream()
//				.map(auth->new SimpleGrantedAuthority(
//						auth.getAuth())).collect(Collectors.toList()));
		super(member.getMemberid(), member.getPassword(),getList(member));
		
		this.member = member;		
	}
	
	public MemberVO getMember() {
		return this.member;
	}
	
	public static List<SimpleGrantedAuthority> getList(MemberVO member) {
		List<SimpleGrantedAuthority> authorities = 
				new ArrayList<SimpleGrantedAuthority>();
		//authList : {"a001":"ROLE_MEMBER","a001":"ROLE_ADMIN"}
		List<MemberAuth> authList = member.getAuthList();
		for(MemberAuth auth : authList) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(auth.getAuth());
			authorities.add(authority);
		}
		
		return authorities;
	}
	
	
	
}




