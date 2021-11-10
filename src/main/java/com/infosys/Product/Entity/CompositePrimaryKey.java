package com.infosys.Product.Entity;

import java.io.Serializable;
import java.util.Objects;

public class CompositePrimaryKey implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String buyerId;
	
	private String productId;
	
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
		CompositePrimaryKey other = (CompositePrimaryKey) obj;
		return Objects.equals(buyerId, other.buyerId) && Objects.equals(productId, other.productId);
	}

	@Override
	public String toString() {
		return "CompositePrimaryKey [buyerId=" + buyerId + ", productId=" + productId + "]";
	}

}
