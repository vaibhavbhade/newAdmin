package com.swiftdroid.posterhouse.admin.repo;

import org.springframework.data.repository.CrudRepository;

import com.swiftdroid.posterhouse.admin.model.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem, Long>{

}
