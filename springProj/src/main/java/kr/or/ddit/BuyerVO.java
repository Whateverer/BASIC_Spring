package kr.or.ddit;

import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Repository;

import kr.or.ddit.lprod.vo.LprodVO;

//자바빈 클래스
@Repository
public class BuyerVO {
	//멤버변수
	private String buyerId;
	private String buyerName;
	private String buyerLgu;
	private String buyerBank;
	private String buyerBankno;
	private String buyerBankname;
	private String buyerZip;
	private String buyerAdd1;
	private String buyerAdd2;
	private String buyerImg;
	@NotBlank
	private String buyerComtel;
	private String buyerFax;
	@Email
	@NotBlank
	private String buyerMail;
	@NotBlank
	private String buyerCharger;
	private String buyerTelext;
	//상품분류명
	private String lprodNm;
	
	//BuyerVO : ProdVO = 1:N 관계
	private List<ProdVO> prodVO;

	//BuyerVO : LprodVO = 1:N 관계
	private List<LprodVO> lprodVO;
	
	//getter/setter 메소드
	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerLgu() {
		return buyerLgu;
	}

	public void setBuyerLgu(String buyerLgu) {
		this.buyerLgu = buyerLgu;
	}

	public String getBuyerBank() {
		return buyerBank;
	}

	public void setBuyerBank(String buyerBank) {
		this.buyerBank = buyerBank;
	}

	public String getBuyerBankno() {
		return buyerBankno;
	}

	public void setBuyerBankno(String buyerBankno) {
		this.buyerBankno = buyerBankno;
	}

	public String getBuyerBankname() {
		return buyerBankname;
	}

	public void setBuyerBankname(String buyerBankname) {
		this.buyerBankname = buyerBankname;
	}

	public String getBuyerZip() {
		return buyerZip;
	}

	public void setBuyerZip(String buyerZip) {
		this.buyerZip = buyerZip;
	}

	public String getBuyerAdd1() {
		return buyerAdd1;
	}

	public void setBuyerAdd1(String buyerAdd1) {
		this.buyerAdd1 = buyerAdd1;
	}

	public String getBuyerAdd2() {
		return buyerAdd2;
	}

	public void setBuyerAdd2(String buyerAdd2) {
		this.buyerAdd2 = buyerAdd2;
	}

	public String getBuyerComtel() {
		return buyerComtel;
	}

	public void setBuyerComtel(String buyerComtel) {
		this.buyerComtel = buyerComtel;
	}

	public String getBuyerFax() {
		return buyerFax;
	}

	public void setBuyerFax(String buyerFax) {
		this.buyerFax = buyerFax;
	}

	public String getBuyerMail() {
		return buyerMail;
	}

	public void setBuyerMail(String buyerMail) {
		this.buyerMail = buyerMail;
	}

	public String getBuyerCharger() {
		return buyerCharger;
	}

	public void setBuyerCharger(String buyerCharger) {
		this.buyerCharger = buyerCharger;
	}

	public String getBuyerTelext() {
		return buyerTelext;
	}

	public void setBuyerTelext(String buyerTelext) {
		this.buyerTelext = buyerTelext;
	}

	public List<ProdVO> getProdVO() {
		return prodVO;
	}

	public void setProdVO(List<ProdVO> prodVO) {
		this.prodVO = prodVO;
	}
	
	public List<LprodVO> getLprodVO() {
		return lprodVO;
	}

	public void setLprodVO(List<LprodVO> lprodVO) {
		this.lprodVO = lprodVO;
	}
	
	public String getLprodNm() {
		return lprodNm;
	}

	public void setLprodNm(String lprodNm) {
		this.lprodNm = lprodNm;
	}

	
	public String getBuyerImg() {
		return buyerImg;
	}

	public void setBuyerImg(String buyerImg) {
		this.buyerImg = buyerImg;
	}

	@Override
	public String toString() {
		return "BuyerVO [buyerId=" + buyerId + ", buyerName=" + buyerName + ", buyerLgu=" + buyerLgu + ", buyerBank="
				+ buyerBank + ", buyerBankno=" + buyerBankno + ", buyerBankname=" + buyerBankname + ", buyerZip="
				+ buyerZip + ", buyerAdd1=" + buyerAdd1 + ", buyerAdd2=" + buyerAdd2 + ", buyerImg=" + buyerImg
				+ ", buyerComtel=" + buyerComtel + ", buyerFax=" + buyerFax + ", buyerMail=" + buyerMail
				+ ", buyerCharger=" + buyerCharger + ", buyerTelext=" + buyerTelext + ", lprodNm=" + lprodNm
				+ ", prodVO=" + prodVO + ", lprodVO=" + lprodVO + "]";
	}

	

}
