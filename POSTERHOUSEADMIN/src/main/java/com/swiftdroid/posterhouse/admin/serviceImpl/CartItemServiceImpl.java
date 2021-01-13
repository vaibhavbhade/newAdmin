package com.swiftdroid.posterhouse.admin.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftdroid.posterhouse.admin.model.CartItem;
import com.swiftdroid.posterhouse.admin.repo.CartItemRepository;
import com.swiftdroid.posterhouse.admin.service.CartItemService;
@Service
public class CartItemServiceImpl implements CartItemService{

	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Override
	public CartItem getCartItemById(Long id) {
		// TODO Auto-generated method stub
		return cartItemRepository.findById(id).orElse(null);
	}

}
