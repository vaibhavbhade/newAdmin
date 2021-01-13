package com.swiftdroid.posterhouse.admin.service;

import org.springframework.stereotype.Service;

import com.swiftdroid.posterhouse.admin.model.CartItem;



public interface CartItemService {
	
	public CartItem getCartItemById(Long id);

}
