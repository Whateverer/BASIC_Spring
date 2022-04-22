package kr.or.ddit.member.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

//자바빈 클래스
public class CardVO {
	private String memberid;
	private String no;
//	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private String vaildMonth;
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getVaildMonth() {
		return vaildMonth;
	}
	public void setVaildMonth(String vaildMonth) {
		this.vaildMonth = vaildMonth;
	}
	@Override
	public String toString() {
		return "CardVO [memberid=" + memberid + ", no=" + no + ", vaildMonth=" + vaildMonth + "]";
	}
	
	
	
	
	
}
