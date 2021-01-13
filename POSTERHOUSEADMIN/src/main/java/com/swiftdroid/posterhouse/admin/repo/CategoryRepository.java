package com.swiftdroid.posterhouse.admin.repo;

import org.springframework.data.repository.CrudRepository;

import com.swiftdroid.posterhouse.admin.model.ProductType;

public interface CategoryRepository extends CrudRepository<ProductType, Long>
{

}
