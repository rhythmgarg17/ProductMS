package com.infosys.Product.Entity;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@Column(name = "prodid")
	private String productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "price")
	private Integer productPrice;

	@Column(name = "stock")
	private Integer productStock;

	@Column(name = "prod_description")
	private String productDescription;

	@Column(name = "image")
	private String productImageUrl;

	@Column(name = "seller_id")
	private String sellerId;

	@Column(name = "category")
	private String category;

	@Column(name = "sub_category")
	private String subCategory;

	@Column(name = "product_rating")
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
	public int hashCode() {
		return Objects.hash(productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(productId, other.productId);
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productStock=" + productStock + ", productDescription=" + productDescription + ", productImageUrl="
				+ productImageUrl + ", sellerId=" + sellerId + ", category=" + category + ", subCategory=" + subCategory
				+ ", productRating=" + productRating + "]";
	}

}