package com.ecommerce.webmain.products;

public class ProductDTO {
	private Long product_id;
    private String name;
    private String description;
    private double price;
    private String base64Image;

    // Constructor, getters, and setters
    public ProductDTO(Long product_id, String name, String description, double price, String base64Image) {
    	this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.base64Image = base64Image;
    }

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

}