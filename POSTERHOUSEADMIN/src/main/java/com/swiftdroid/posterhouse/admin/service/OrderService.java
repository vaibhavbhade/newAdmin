package com.swiftdroid.posterhouse.admin.service;

import java.util.Date;
import java.util.List;

import com.swiftdroid.posterhouse.admin.model.Order;

public interface OrderService {

	long findListOfOrder();
	List<Order> findOrderByTodaysDate(Date today);
	List<Order> allOrder();
	List<Order> UpdateOrder(Order order);
	Order saveOrder(Order order);
	Order findOrderById(Long id);
	
}
