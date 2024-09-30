package com.ecommerce.webmain.customer;

public class CustomerDTO {
	private Long id;
	private String name;
	private String email;
	private String address;
	private String mobile;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CustomerDTO(Long id, String name, String email, String address, String mobile) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
