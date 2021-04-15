package com.swiftdroid.posterhouse.admin.repo;

import org.springframework.data.repository.CrudRepository;

import com.swiftdroid.posterhouse.admin.model.CartItem;
import com.swiftdroid.posterhouse.admin.model.CartItemToImage;

public interface CartItemToImageRepository extends CrudRepository<CartItemToImage, Long> {
	CartItemToImage findByCartItemAndId(CartItem cartItem,Long Id);
}
