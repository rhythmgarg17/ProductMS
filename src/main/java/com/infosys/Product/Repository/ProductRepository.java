package com.infosys.Product.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infosys.Product.Entity.Product;
import com.infosys.Product.Exception.ProductException;


@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	public List<Product> findAllProductsByCategory(String category) throws ProductException;
	
	public List<Product> findAllProductsByProductName(String productName) throws ProductException;
	
	@Modifying
	@Transactional
	@Query("UPDATE Product p SET p.productStock = :productStock WHERE p.productId = :productId")
	public void updateStock(@Param("productStock") Integer productStock, @Param("productId") String productId) throws ProductException;
	
}