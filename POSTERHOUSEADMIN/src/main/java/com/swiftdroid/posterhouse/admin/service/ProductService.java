package com.swiftdroid.posterhouse.admin.service;

import java.util.List;

import com.swiftdroid.posterhouse.admin.model.Product;
import com.swiftdroid.posterhouse.admin.model.ProductType;

public interface ProductService {

	public Product save(Product product);
	public List<Product> findAll();
	public Product findOneBook(Long id);
	public Product updateBook(Long id);
	public Product saveProductWithcategory(Product product,ProductType productType);

}
