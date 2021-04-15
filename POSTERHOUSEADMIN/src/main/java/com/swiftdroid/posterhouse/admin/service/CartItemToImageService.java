package com.swiftdroid.posterhouse.admin.service;

import com.swiftdroid.posterhouse.admin.model.CartItem;
import com.swiftdroid.posterhouse.admin.model.CartItemToImage;

public interface CartItemToImageService {
	CartItemToImage findCartItemToImageByCartItemAndId(CartItem cartItem,Long id);

}
