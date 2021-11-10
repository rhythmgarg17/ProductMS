package com.infosys.Product.Repository;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infosys.Product.Entity.CompositePrimaryKey;
import com.infosys.Product.Entity.SubscribedProducts;
import com.infosys.Product.Exception.ProductException;

@Repository
public interface SubscribedProductsRepository extends JpaRepository<SubscribedProducts, CompositePrimaryKey> {
	
	@Query("SELECT s FROM SubscribedProducts s WHERE s.buyerId = :buyerId AND s.productId = :productId")
	public Optional<SubscribedProducts> findBySubscribedProducts(@Param("buyerId") String buyerId, @Param("productId") String productId) throws ProductException;
	
	@Modifying
	@Transactional
	@Query("DELETE FROM SubscribedProducts s WHERE s.buyerId = :buyerId AND s.productId = :productId")
	public void deleteBySubscribedProducts(@Param("buyerId") String buyerId, @Param("productId") String productId) throws ProductException;
	

}

