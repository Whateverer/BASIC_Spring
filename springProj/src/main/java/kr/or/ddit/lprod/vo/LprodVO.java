package kr.or.ddit.lprod.vo;

import java.util.List;

import kr.or.ddit.BuyerVO;

//자바빈 클래스
public class LprodVO {

	private int lprodId;
	private String lprodGu;
	private String lprodNm;
	private List<BuyerVO> buyerVO;
	private int rnum;
	
	public int getLprodId() {
		return lprodId;
	}
	public void setLprodId(int lprodId) {
		this.lprodId = lprodId;
	}
	public String getLprodGu() {
		return lprodGu;
	}
	public void setLprodGu(String lprodGu) {
		this.lprodGu = lprodGu;
	}
	public String getLprodNm() {
		return lprodNm;
	}
	public void setLprodNm(String lprodNm) {
		this.lprodNm = lprodNm;
	}
	
	public List<BuyerVO> getBuyerVO() {
		return buyerVO;
	}
	public void setBuyerVO(List<BuyerVO> buyerVO) {
		this.buyerVO = buyerVO;
	}
	
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	
	@Override
	public String toString() {
		return "LprodVO [lprodId=" + lprodId + ", lprodGu=" + lprodGu + ", lprodNm=" + lprodNm + ", buyerVO=" + buyerVO
				+ ", rnum=" + rnum + "]";
	}

}
