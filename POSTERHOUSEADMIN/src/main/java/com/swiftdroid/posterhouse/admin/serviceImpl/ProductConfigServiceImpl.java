package com.swiftdroid.posterhouse.admin.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftdroid.posterhouse.admin.model.ProductConfig;
import com.swiftdroid.posterhouse.admin.repo.ProductConfigRepository;
import com.swiftdroid.posterhouse.admin.service.ProductConfigService;


@Service
public class ProductConfigServiceImpl implements ProductConfigService{

	@Autowired
	private ProductConfigRepository productConfigRepository;
	
	@Override
	public ProductConfig saveProductConfig( ProductConfig productConfig) {
		// TODO Auto-generated method stub
		return productConfigRepository.save(productConfig);
	}

	@Override
	public ProductConfig findProductConfigById(Long id) {
		// TODO Auto-generated method stub
		return productConfigRepository.findById(id).orElse(null);
	}

}
