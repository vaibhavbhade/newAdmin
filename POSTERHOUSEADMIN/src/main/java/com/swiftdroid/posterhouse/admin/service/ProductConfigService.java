package com.swiftdroid.posterhouse.admin.service;

import com.swiftdroid.posterhouse.admin.model.ProductConfig;

public interface ProductConfigService {

	
	public ProductConfig saveProductConfig(ProductConfig productConfig);

	public ProductConfig findProductConfigById(Long id);

	
}
