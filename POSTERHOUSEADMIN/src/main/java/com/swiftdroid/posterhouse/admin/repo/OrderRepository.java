package com.swiftdroid.posterhouse.admin.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.swiftdroid.posterhouse.admin.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

	
	
	List<Order> findByCreatedDate(Date createdDate);
	
}
