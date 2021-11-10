package com.infosys.Product.Service;

import java.util.List;

import com.infosys.Product.Dto.ProductDto;
import com.infosys.Product.Exception.ProductException;

public interface ProductService {
	
	public Boolean validateProduct(String productId) throws ProductException;
	
	public List<ProductDto> getAllProducts() throws ProductException;
	
	public ProductDto getProductById(String productId) throws ProductException; 
	
	public List<ProductDto> getAllProductsByCategory(String category) throws ProductException;
	
	public List<ProductDto> getAllProductsByProductName(String productName) throws ProductException;
	
	public String addProduct(ProductDto productDto) throws ProductException;
	
	public void updateStockBySeller(String productId, Integer productStock) throws ProductException;
	
	public Boolean updateStockWhenOrderPlaced(String productId, Integer quantity) throws ProductException;
	
	public void deleteProduct(String productId) throws ProductException;

}
