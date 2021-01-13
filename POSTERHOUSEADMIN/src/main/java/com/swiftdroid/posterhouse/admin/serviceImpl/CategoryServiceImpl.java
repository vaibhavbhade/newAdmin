package com.swiftdroid.posterhouse.admin.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftdroid.posterhouse.admin.model.ProductType;
import com.swiftdroid.posterhouse.admin.repo.CategoryRepository;
import com.swiftdroid.posterhouse.admin.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	
	@Override
	public ProductType saveCategory(ProductType productType) {
		// TODO Auto-generated method stub
		return categoryRepository.save(productType);
		
	}



	@Override
	public ProductType findById(Long ProductTypeId) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(ProductTypeId).orElse(null);
	}



	@Override
	public List<ProductType> findAll() {
		// TODO Auto-generated method stub
		return (List<ProductType>) categoryRepository.findAll();
	}

}
