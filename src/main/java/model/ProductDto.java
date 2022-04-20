package model;

public class ProductDto {
	private int prodId;
	private int stock;
	private int price;
	
	private String prodName;
	private String descript;
	
	private String category;
	private String petkind;
	//private String imgName;
	
//	public String getImgName() {
//		return imgName;
//	}
//	public void setImgName(String imgName) {
//		this.imgName = imgName;
//	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getPetkind() {
		return petkind;
	}
	public void setPetkind(String petkind) {
		this.petkind = petkind;
	}
	
	
}
