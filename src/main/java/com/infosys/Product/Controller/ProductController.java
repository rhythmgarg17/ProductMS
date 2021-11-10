package com.infosys.Product.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.infosys.Product.Dto.ProductDto;
import com.infosys.Product.Dto.ProductValidation;
import com.infosys.Product.Dto.SubscribedProductsDto;
import com.infosys.Product.Exception.ProductException;
import com.infosys.Product.Service.ProductServiceImpl;
import com.infosys.Product.Service.SubscribedProductsServiceImpl;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class ProductController {

	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@Autowired
	private SubscribedProductsServiceImpl subProdService;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Environment environment;
	
	@GetMapping("/validate/product/{id}")
	public ResponseEntity<Boolean> validateProduct(@PathVariable("id") String productId) throws ProductException {
		Boolean result = productServiceImpl.validateProduct(productId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/productList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDto>> getAllProducts() throws ProductException {
		List<ProductDto> products = productServiceImpl.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDto> getProductById(@PathVariable("id") String productId) throws ProductException {
		ProductDto products = productServiceImpl.getProductById(productId);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping(value = "/product/category/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDto>> getAllproductsByCategory(@PathVariable("category") String category) throws ProductException {
		List<ProductDto> products = productServiceImpl.getAllProductsByCategory(category);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping(value = "/product/productname/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDto>> getAllProductsByProductName(@PathVariable("name") String productName) throws ProductException {
		List<ProductDto> products = productServiceImpl.getAllProductsByProductName(productName);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@PostMapping(value = "/seller/addProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addproduct(@RequestBody ProductDto product) throws ProductException {

		String url = "http://localhost:8100/api/validate/seller/"+product.getSellerId();
		Boolean result = restTemplate.getForObject(url, Boolean.class);

		if (Boolean.TRUE.equals(result)) {
			return new ResponseEntity<>("Invalid Seller!!", HttpStatus.BAD_REQUEST);
		} else {
			String productId = productServiceImpl.addProduct(product);
			String successMessage = environment.getProperty("API.INSERT_SUCCESS") + productId;
			return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
		}
	}

	@PutMapping(value = "/seller/updateProduct/{id}")
	public ResponseEntity<String> updateStockBySeller(@PathVariable("id") String productId, @RequestBody ProductDto product) throws ProductException {
			Boolean result = ProductValidation.validateStock(product.getProductStock());
			if (Boolean.TRUE.equals(result)) {
				productServiceImpl.updateStockBySeller(productId, product.getProductStock());
				String successMessage = environment.getProperty("API.UPDATE_SUCCESS") + productId;
				return new ResponseEntity<>(successMessage, HttpStatus.OK);
			} else {
				String errorMsg = environment.getProperty("Validation.VALIDATE_STOCK");
				return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
			}
	}

	@DeleteMapping(value = "/seller/deleteProduct/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") String productId) throws ProductException {
			productServiceImpl.deleteProduct(productId);
			String successMessage = environment.getProperty("API.DELETE_SUCCESS") + productId;
			return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@PutMapping(value = "/product/OrderPlaced/{prodId}/{quantity}")
	public ResponseEntity<Boolean> updateStockWhenOrderPlaced(@PathVariable("prodId") String productId, @PathVariable("quantity") Integer quantity) throws ProductException {
		Boolean result = productServiceImpl.updateStockWhenOrderPlaced(productId, quantity);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value = "/buyer/privileged/subscribe")
	public ResponseEntity<String> addSubscribedProducts(@RequestBody SubscribedProductsDto subProductDto) throws ProductException {

		String url = "http://localhost:8100/api/validate/privileged/"+subProductDto.getBuyerId();
		Boolean result = restTemplate.getForObject(url, Boolean.class);

		if (Boolean.TRUE.equals(result)) {
			String buyerId = subProdService.addSubscribedProducts(subProductDto);
			String successMsg = environment.getProperty("API.ADD_SUCCESS") + buyerId;
			return new ResponseEntity<>(successMsg, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("You are not a Privileged Buyer!!", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/buyer/privileged/{buyerId}/{productId}/subscribe")
	public ResponseEntity<String> deleteSubscribedProducts(@PathVariable String buyerId, @PathVariable String productId) throws ProductException {
		subProdService.deleteSubscribedProducts(buyerId, productId);
		String successMsg = environment.getProperty("API.REMOVED_SUCCESS") + buyerId;
		return new ResponseEntity<>(successMsg, HttpStatus.OK);
	}
	
}