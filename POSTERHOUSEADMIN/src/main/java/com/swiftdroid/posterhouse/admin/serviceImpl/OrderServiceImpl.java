package com.swiftdroid.posterhouse.admin.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftdroid.posterhouse.admin.model.Order;
import com.swiftdroid.posterhouse.admin.repo.OrderRepository;
import com.swiftdroid.posterhouse.admin.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public long findListOfOrder() {
		// TODO Auto-generated method stub
		return orderRepository.count();
	}

	@Override
	public List<Order> findOrderByTodaysDate(Date today) {
		// TODO Auto-generated method stub
		return orderRepository.findByCreatedDate(today);
	}

	@Override
	public List<Order> allOrder() {
		// TODO Auto-generated method stub
		return (List<Order>) orderRepository.findAll();
	}

	@Override
	public Order findOrderById(Long id) {
		// TODO Auto-generated method stub
		
		return orderRepository.findById(id).orElse(null);
	}

	@Override
	public List<Order> UpdateOrder(Order order) {
		// TODO Auto-generated method stub
		 orderRepository.save(order);
		return allOrder();
		 
	}



}
