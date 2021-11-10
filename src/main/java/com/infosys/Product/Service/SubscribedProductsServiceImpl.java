package com.infosys.Product.Service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.infosys.Product.Dto.SubscribedProductsDto;
import com.infosys.Product.Entity.SubscribedProducts;
import com.infosys.Product.Exception.ProductException;
import com.infosys.Product.Repository.SubscribedProductsRepository;

@Service
@Transactional
public class SubscribedProductsServiceImpl implements SubscribedProductsService{
	
	@Autowired
	SubscribedProductsRepository subscribedRepo;

	@Override
	public String addSubscribedProducts(SubscribedProductsDto subProductDto) throws ProductException {
		Optional<SubscribedProducts> product = subscribedRepo.findBySubscribedProducts(subProductDto.getBuyerId(), subProductDto.getProductId());
		if (product.isPresent()) {
			throw new ProductException("Service.SUBSCRIBED_PRODUCT_ALREADY_EXISTS");
		}
		SubscribedProducts prod = subProductDto.createEntity();
		SubscribedProducts sub = subscribedRepo.save(prod);
		return sub.getBuyerId();
	}
	
		@Override
		public void deleteSubscribedProducts(String buyerId, String productId) throws ProductException { 
		Optional<SubscribedProducts> subProduct = subscribedRepo.findBySubscribedProducts(buyerId, productId);
		subProduct.orElseThrow(() -> new ProductException("Service.SUBSCRIBED_PRODUCT_NOT_FOUND"));
		subscribedRepo.deleteBySubscribedProducts(buyerId, productId);
	}
}
