package com.swiftdroid.posterhouse.admin.repo;

import org.springframework.data.repository.CrudRepository;

import com.swiftdroid.posterhouse.admin.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	
Product findByProductCode(String productCode);
}
