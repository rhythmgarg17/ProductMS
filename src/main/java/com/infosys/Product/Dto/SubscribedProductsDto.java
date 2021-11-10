package com.infosys.Product.Dto;

import com.infosys.Product.Entity.SubscribedProducts;

public class SubscribedProductsDto {

	private String buyerId;
	
	private String productId;
	
	private Integer quantity;

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	@Override
	public String toString() {
		return "SubscribedProductsDto [buyerId=" + buyerId + ", productId=" + productId + ", quantity=" + quantity
				+ "]";
	}
	
	
//	Converts Entity into DTO
	public static SubscribedProductsDto valueOf(SubscribedProducts subProd) {
		SubscribedProductsDto subProdDto = new SubscribedProductsDto();
		subProdDto.setBuyerId(subProd.getBuyerId());
		subProdDto.setProductId(subProd.getProductId());
		subProdDto.setQuantity(subProd.getQuantity());
		return subProdDto;
	}
//	Converts DTO into Entity
	public SubscribedProducts createEntity() {
		SubscribedProducts subProducts = new SubscribedProducts();
		subProducts.setBuyerId(this.getBuyerId());
		subProducts.setProductId(this.getProductId());
		subProducts.setQuantity(this.getQuantity());
		return subProducts;
	}
}
	

