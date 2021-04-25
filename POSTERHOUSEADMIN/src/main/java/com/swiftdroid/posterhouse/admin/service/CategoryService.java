package com.swiftdroid.posterhouse.admin.service;

import java.util.List;

import com.swiftdroid.posterhouse.admin.model.ProductType;

public interface CategoryService {

	public ProductType saveCategory(ProductType productType);
	public ProductType findById(Long ProductType);
	public List<ProductType> findAll();
	ProductType findByProductTypeCode(String productCode);

}
