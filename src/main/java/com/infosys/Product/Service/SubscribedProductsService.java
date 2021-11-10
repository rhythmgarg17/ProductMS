package com.infosys.Product.Service;

import com.infosys.Product.Dto.SubscribedProductsDto;
import com.infosys.Product.Exception.ProductException;

public interface SubscribedProductsService {
	
	
	public String addSubscribedProducts(SubscribedProductsDto subProductDto) throws ProductException;
	
	public void deleteSubscribedProducts(String buyerId, String productId) throws ProductException;

}
