package com.infosys.Product.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.Product.Dto.ProductDto;
import com.infosys.Product.Dto.ProductValidation;
import com.infosys.Product.Entity.Product;
import com.infosys.Product.Exception.ProductException;
import com.infosys.Product.Repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	

	@Override
	public Boolean validateProduct(String productId) throws ProductException {
		Optional<Product> product = productRepo.findById(productId);
		if(product.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public List<ProductDto> getAllProducts() throws ProductException {
		Iterable<Product> prod = productRepo.findAll();
		List<ProductDto> prodDto = new ArrayList<>();
		for (Product product : prod) {
			ProductDto productDto = ProductDto.valueOf(product);
			prodDto.add(productDto);
		}
		if(prodDto.isEmpty()) {
			throw new ProductException("Service.PRODUCTS_NOT_FOUND");
		}
		return prodDto;
	}

	@Override
	public ProductDto getProductById(String productId) throws ProductException {
		Optional<Product> prod = productRepo.findById(productId);
		Product prodMS = prod.orElseThrow(() -> new ProductException("Service.PRODUCT_NOT_FOUND"));
		ProductDto productDto = ProductDto.valueOf(prodMS);
		return productDto;
	}

	@Override
	public List<ProductDto> getAllProductsByCategory(String category) throws ProductException {
		Iterable<Product> prod = productRepo.findAllProductsByCategory(category);
		List<ProductDto> prodDto = new ArrayList<>();
		for (Product product : prod) {
			ProductDto productDto = ProductDto.valueOf(product);
			prodDto.add(productDto);
		}
		if (prodDto.isEmpty()) {
			throw new ProductException("Service.CATEGORY_NOT_FOUND");
		}
		return prodDto;
	}

	@Override
	public List<ProductDto> getAllProductsByProductName(String productName) throws ProductException {
		Iterable<Product> prod = productRepo.findAllProductsByProductName(productName);
		List<ProductDto> prodDto = new ArrayList<>();
		for (Product product : prod) {
			ProductDto productDto = ProductDto.valueOf(product);
			prodDto.add(productDto);
		}
		if (prodDto.isEmpty()) {
			throw new ProductException("Service.PRODUCT_NAME_NOT_FOUND");
		}
		return prodDto;
	}

	@Override
	public String addProduct(ProductDto productDto) throws ProductException {
		ProductValidation.validateProduct(productDto);
		Optional<Product> product = productRepo.findById(productDto.getProductId());
		if (product.isPresent()) {
			throw new ProductException("Service.PRODUCT_ALREADY_EXISTS");
		}
		Product prod = productDto.createEntity();
		productRepo.save(prod);
		return prod.getProductId();
	}

	@Override
	public void updateStockBySeller(String productId, Integer productStock) throws ProductException {
		Optional<Product> product = productRepo.findById(productId);
		Product prod = product.orElseThrow(() -> new ProductException("Service.PRODUCT_NOT_FOUND"));
		prod.setProductStock(productStock);
		productRepo.save(prod);
	}

	@Override
	public Boolean updateStockWhenOrderPlaced(String productId, Integer quantity) throws ProductException {
		Optional<Product> prod = productRepo.findById(productId);
		Product product = prod.orElseThrow(() -> new ProductException("Service.PRODUCT_NOT_FOUND"));
		if (product.getProductStock() >= quantity) {
			productRepo.updateStock((product.getProductStock() - quantity), productId);
			return true;
		}
		return false;
	}

	@Override
	public void deleteProduct(String productId) throws ProductException {
		Optional<Product> product = productRepo.findById(productId);
		product.orElseThrow(() -> new ProductException("Service.PRODUCT_NOT_FOUND"));
		productRepo.deleteById(productId);
        }
}