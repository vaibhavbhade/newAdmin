package com.swiftdroid.posterhouse.admin.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftdroid.posterhouse.admin.model.Product;
import com.swiftdroid.posterhouse.admin.model.ProductType;
import com.swiftdroid.posterhouse.admin.repo.ProductRepository;
import com.swiftdroid.posterhouse.admin.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService {

	
	@Autowired
	private ProductRepository productRepository;
	
	
	@Override
	public Product save(Product product) {
		
		
		return productRepository.save(product);
	}


	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return (List<Product>) productRepository.findAll();
	}


	@Override
	public Product findOneBook(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).orElse(null);
	}


	@Override
	public Product updateBook(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).orElse(null);
	}


	@Override
	public Product saveProductWithcategory(Product product,ProductType productType) {
		product.setProductType(productType);
		
		productType.getProducts().add(product);
		
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}


	@Override
	public Product findByProductCode(String productCode) {
		// TODO Auto-generated method stub
		return productRepository.findByProductCode(productCode);
	}

}
