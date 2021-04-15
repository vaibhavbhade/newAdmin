package com.swiftdroid.posterhouse.admin.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftdroid.posterhouse.admin.model.CartItem;
import com.swiftdroid.posterhouse.admin.model.CartItemToImage;
import com.swiftdroid.posterhouse.admin.repo.CartItemToImageRepository;
import com.swiftdroid.posterhouse.admin.service.CartItemToImageService;
@Service
public class CartItemToImageServiceImpl implements CartItemToImageService {

	@Autowired
	private CartItemToImageRepository cartItemToImageRepository;
	
	
	@Override
	public CartItemToImage findCartItemToImageByCartItemAndId(CartItem cartItem, Long id) {
		// TODO Auto-generated method stub
		return cartItemToImageRepository.findByCartItemAndId(cartItem, id);
	}

}
