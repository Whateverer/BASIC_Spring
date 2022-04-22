package kr.or.ddit;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

//바쁠때는 그냥 lombok의 Data 애너테이션을 이용 - getter,setter와 toString 생성
//자바빈 클래스
public class ProdVO {
	private String prodId;
	private String prodName;
	private String prodLgu;
	private String prodBuyer;
	private int prodCost;
	private int prodPrice;
	private int prodSale;
	private String prodOutline;
	//오라클 CLOB 자료형일지라도 String 타입으로 선언
	private String prodDetail;
	private String prodImg;
	private int prodTotalstock;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date prodInsdate;
	private int prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private int prodQtyin;
	private int prodQtysale;
	private int prodMileage;
	
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdLgu() {
		return prodLgu;
	}
	public void setProdLgu(String prodLgu) {
		this.prodLgu = prodLgu;
	}
	public String getProdBuyer() {
		return prodBuyer;
	}
	public void setProdBuyer(String prodBuyer) {
		this.prodBuyer = prodBuyer;
	}
	public int getProdCost() {
		return prodCost;
	}
	public void setProdCost(int prodCost) {
		this.prodCost = prodCost;
	}
	public int getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}
	public int getProdSale() {
		return prodSale;
	}
	public void setProdSale(int prodSale) {
		this.prodSale = prodSale;
	}
	public String getProdOutline() {
		return prodOutline;
	}
	public void setProdOutline(String prodOutline) {
		this.prodOutline = prodOutline;
	}
	public String getProdDetail() {
		return prodDetail;
	}
	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}
	public String getProdImg() {
		return prodImg;
	}
	public void setProdImg(String prodImg) {
		this.prodImg = prodImg;
	}
	public int getProdTotalstock() {
		return prodTotalstock;
	}
	public void setProdTotalstock(int prodTotalstock) {
		this.prodTotalstock = prodTotalstock;
	}
	public Date getProdInsdate() {
		return prodInsdate;
	}
	public void setProdInsdate(Date prodInsdate) {
		this.prodInsdate = prodInsdate;
	}
	public int getProdProperstock() {
		return prodProperstock;
	}
	public void setProdProperstock(int prodProperstock) {
		this.prodProperstock = prodProperstock;
	}
	public String getProdSize() {
		return prodSize;
	}
	public void setProdSize(String prodSize) {
		this.prodSize = prodSize;
	}
	public String getProdColor() {
		return prodColor;
	}
	public void setProdColor(String prodColor) {
		this.prodColor = prodColor;
	}
	public String getProdDelivery() {
		return prodDelivery;
	}
	public void setProdDelivery(String prodDelivery) {
		this.prodDelivery = prodDelivery;
	}
	public String getProdUnit() {
		return prodUnit;
	}
	public void setProdUnit(String prodUnit) {
		this.prodUnit = prodUnit;
	}
	public int getProdQtyin() {
		return prodQtyin;
	}
	public void setProdQtyin(int prodQtyin) {
		this.prodQtyin = prodQtyin;
	}
	public int getProdQtysale() {
		return prodQtysale;
	}
	public void setProdQtysale(int prodQtysale) {
		this.prodQtysale = prodQtysale;
	}
	public int getProdMileage() {
		return prodMileage;
	}
	public void setProdMileage(int prodMileage) {
		this.prodMileage = prodMileage;
	}
	@Override
	public String toString() {
		return "ProdVO [prodId=" + prodId + ", prodName=" + prodName + ", prodLgu=" + prodLgu + ", prodBuyer="
				+ prodBuyer + ", prodCost=" + prodCost + ", prodPrice=" + prodPrice + ", prodSale=" + prodSale
				+ ", prodOutline=" + prodOutline + ", prodDetail=" + prodDetail + ", prodImg=" + prodImg
				+ ", prodTotalstock=" + prodTotalstock + ", prodInsdate=" + prodInsdate + ", prodProperstock="
				+ prodProperstock + ", prodSize=" + prodSize + ", prodColor=" + prodColor + ", prodDelivery="
				+ prodDelivery + ", prodUnit=" + prodUnit + ", prodQtyin=" + prodQtyin + ", prodQtysale=" + prodQtysale
				+ ", prodMileage=" + prodMileage + "]";
	}
	
	
}
