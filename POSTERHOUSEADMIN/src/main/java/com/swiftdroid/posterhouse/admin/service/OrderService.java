package com.swiftdroid.posterhouse.admin.service;

import java.util.Date;
import java.util.List;

import com.swiftdroid.posterhouse.admin.model.Order;

public interface OrderService {

	long findListOfOrder();
	List<Order> findOrderByTodaysDate(Date today);
	List<Order> allOrder();
	Order findOrderById(Long Id);
	List<Order> UpdateOrder(Order order);
	
}
