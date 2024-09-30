package com.ecommerce.webmain.orders;

import java.util.List;

class Prod {
	private String image;
	private String name;
	private double price;
	private int quantity;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Prod(String image, String name, double price, int quantity) {
		super();
		this.image = image;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
}

public class UserDTO {
	private Long order_id;
	public UserDTO(Long order_id, List<Prod> products) {
		super();
		this.order_id = order_id;
		this.products = products;
	}
	private List<Prod> products;
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public List<Prod> getProducts() {
		return products;
	}
	public void setProducts(List<Prod> products) {
		this.products = products;
	}
	
}
