package com.swiftdroid.posterhouse.admin.service;

import java.util.List;
import java.util.Set;

import com.swiftdroid.posterhouse.admin.model.User;
import com.swiftdroid.posterhouse.admin.model.UserRole;

public interface UserService {
	
      User createUser(User user,Set<UserRole> userRoles) throws Exception;
      User save(User user);
	  User findByUsername(String username);
	  long userCount();
	 List<User> findAllUser();
}
