
package jp.co.internous.framepj.model.domain.dto;

/**
 * カート画面に表示するためのDTO
 * @author インターノウス
 *
 */
public class CartDto {
	
	private int id;
	private String imageFullPath;
	private String productName;
	private int price;
	private int productCount;
	private int subtotal;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImageFullPath() {
		return imageFullPath;
	}
	public void setImageFullPath(String imageFullPath) {
		this.imageFullPath = imageFullPath;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productcount) {
		this.productCount = productcount;
	}
	public int getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}
	
	
}
