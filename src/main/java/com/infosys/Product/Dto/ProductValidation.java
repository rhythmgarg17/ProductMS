package com.infosys.Product.Dto;

import com.infosys.Product.Exception.ProductException;

public class ProductValidation {

	public static void validateProduct(ProductDto productDto) throws ProductException {
		
		if(!validateName(productDto.getProductName()))
			throw new ProductException("Validation.VALIDATE_NAME");
		
		if(!validateDescription(productDto.getProductDescription()))
			throw new ProductException("Validation.VALIDATE_DESCRIPTION");
		
		if(!validatePrice(productDto.getProductPrice()))
			throw new ProductException("Validation.VALIDATE_PRICE");
		
		if(!validateStock(productDto.getProductStock()))
			throw new ProductException("Validation.VALIDATE_STOCK");
		
		if(!validateImage(productDto.getProductImageUrl()))
			throw new ProductException("Validation.VALIDATE_IMAGE");
		
	}
	
	public static boolean validateName(String productName) {
		String regex = "([A-Za-z]+( [A-Za-z]+)*){1,100}$";
		if(productName.matches(regex) ) {
			return true;
		}
		return false;
	}
	
	public static boolean validateDescription(String productDescription) {
		String regex = "([A-Za-z0-9]+( [A-Za-z0-9]+)*){1,500}$";
		if(productDescription.matches(regex) ) {
			return true;
		}
		return false;
	}

	public static boolean validatePrice(Integer productPrice) {
		if (productPrice >= 200) {
			return true;
		}
		return false;
	}
	
	public static boolean validateStock(Integer productStock) {
		if(productStock >= 10) {
			return true;
		}
		return false;
	}
	
	public static boolean validateImage(String productImageUrl) {
		String regex = "([^\\s]+(\\.(?i)(png|jpeg))$)";
		if((productImageUrl.matches(regex))) {
			return true;
		}
		return false;
	}
			
}
