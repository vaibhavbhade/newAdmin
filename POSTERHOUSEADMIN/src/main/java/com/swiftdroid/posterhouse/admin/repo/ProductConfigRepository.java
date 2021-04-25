package com.swiftdroid.posterhouse.admin.repo;

import org.springframework.data.repository.CrudRepository;

import com.swiftdroid.posterhouse.admin.model.ProductConfig;

public interface ProductConfigRepository extends CrudRepository<ProductConfig, Long> {
	
	ProductConfig findByProductConfigCode(String productConfigCode);

}
