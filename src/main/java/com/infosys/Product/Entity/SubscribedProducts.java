package com.infosys.Product.Entity;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="subscribed_products")
@IdClass(CompositePrimaryKey.class)
public class SubscribedProducts {
	
	@Id
	@Column(name = "buyerid")
	private String buyerId;
	
	@Id
	@Column(name = "prodid")
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
	public int hashCode() {
		return Objects.hash(buyerId, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubscribedProducts other = (SubscribedProducts) obj;
		return Objects.equals(buyerId, other.buyerId) && Objects.equals(productId, other.productId);
	}

	@Override
	public String toString() {
		return "SubscribedProducts [buyerId=" + buyerId + ", productId=" + productId + ", quantity=" + quantity + "]";
	}
	
}
