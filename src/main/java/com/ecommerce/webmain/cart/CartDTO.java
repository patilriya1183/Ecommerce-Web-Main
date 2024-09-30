package com.ecommerce.webmain.cart;

public class CartDTO {
	private Long id;
	private Long pid;
	private String image;
	private String name;
	private int quantity;
	private double price;
	public CartDTO(Long id, Long pid, String image, String name, int quantity, double price) {
		super();
		this.id = id;
		this.pid = pid;
		this.image = image;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
