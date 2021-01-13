package com.swiftdroid.posterhouse.admin.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftdroid.posterhouse.admin.model.ProductImage;
import com.swiftdroid.posterhouse.admin.repo.ProductImageRepository;
import com.swiftdroid.posterhouse.admin.service.ProductImageService;


@Service
public class ProductImageServiceImpl implements ProductImageService {

	@Autowired
	private ProductImageRepository productImageRepository;
	
	@Override
	public ProductImage saveProductImage(ProductImage productImage) {
		// TODO Auto-generated method stub
		return productImageRepository.save(productImage);
	}

}
