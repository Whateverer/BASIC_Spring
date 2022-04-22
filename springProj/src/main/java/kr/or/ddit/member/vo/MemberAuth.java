package kr.or.ddit.member.vo;

public class MemberAuth {
	private String memberid;
	private String auth;
	
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	
	@Override
	public String toString() {
		return "memberAuth [memberid=" + memberid + ", auth=" + auth + "]";
	}
	
	
	
}
