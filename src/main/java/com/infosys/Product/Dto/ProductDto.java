package com.infosys.Product.Dto;

import com.infosys.Product.Entity.Product;

public class ProductDto {
  
	private String productId;
	
	private String productName;

	private Integer productPrice;

	private Integer productStock;

	private String productDescription;

	private String productImageUrl;

	private String sellerId;
	
	private String category;

	private String subCategory;

	private Integer productRating;
	


	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getProductStock() {
		return productStock;
	}

	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public Integer getProductRating() {
		return productRating;
	}

	public void setProductRating(Integer productRating) {
		this.productRating = productRating;
	}

	@Override
	public String toString() {
		return "ProductDto [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productStock=" + productStock + ", productDescription=" + productDescription + ", productImageUrl="
				+ productImageUrl + ", sellerId=" + sellerId + ", category=" + category + ", subCategory=" + subCategory
				+ ", productRating=" + productRating + "]";
	}

	// Converts Entity into DTO
	public static ProductDto valueOf(Product productEntity) {
		ProductDto productDto = new ProductDto();
		productDto.setProductId(productEntity.getProductId());
		productDto.setProductName(productEntity.getProductName());
		productDto.setProductPrice(productEntity.getProductPrice());
		productDto.setProductStock(productEntity.getProductStock());
		productDto.setProductDescription(productEntity.getProductDescription());
		productDto.setProductImageUrl(productEntity.getProductImageUrl());
		productDto.setSellerId(productEntity.getSellerId());
		productDto.setCategory(productEntity.getCategory());
		productDto.setSubCategory(productEntity.getSubCategory());
		productDto.setProductRating(productEntity.getProductRating());
		return productDto;
	}

	// Converts DTO into Entity
	public Product createEntity() {
		Product productEntity = new Product();
		productEntity.setProductId(this.getProductId());
		productEntity.setProductName(this.getProductName());
		productEntity.setProductPrice(this.getProductPrice());
		productEntity.setProductStock(this.getProductStock());
		productEntity.setProductDescription(this.getProductDescription());
		productEntity.setProductImageUrl(this.getProductImageUrl());
		productEntity.setSellerId(this.getSellerId());
		productEntity.setCategory(this.getCategory());
		productEntity.setSubCategory(this.getSubCategory());
		productEntity.setProductRating(this.getProductRating());
		return productEntity;
	}
}
