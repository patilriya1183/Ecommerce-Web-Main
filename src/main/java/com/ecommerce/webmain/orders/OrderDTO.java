package com.ecommerce.webmain.orders;

import java.util.List;

public class OrderDTO {
	private Long OrderId;
	private String name;
	private String email;
	private String mobile;
	private String address;
	private String paymentMode;
	private double totalPrice;
	public OrderDTO(Long orderId, String name, String email, String mobile, String address, String paymentMode,
			double totalPrice, List<ProdDTO> products) {
		super();
		OrderId = orderId;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.paymentMode = paymentMode;
		this.totalPrice = totalPrice;
		this.products = products;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	private List<ProdDTO> products;
	public Long getOrderId() {
		return OrderId;
	}
	public void setOrderId(Long orderId) {
		OrderId = orderId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public List<ProdDTO> getProducts() {
		return products;
	}
	public void setProducts(List<ProdDTO> products) {
		this.products = products;
	}
}
