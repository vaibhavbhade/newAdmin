package com.swiftdroid.posterhouse.admin.serviceImpl;


import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftdroid.posterhouse.admin.model.User;
import com.swiftdroid.posterhouse.admin.model.UserRole;
import com.swiftdroid.posterhouse.admin.repo.RoleRepostiory;
import com.swiftdroid.posterhouse.admin.repo.UserRepository;
import com.swiftdroid.posterhouse.admin.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	 private final static Logger LOG=LoggerFactory.getLogger(UserService.class);
	

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepostiory roleRepostiory;
	


	@Override
	public User createUser(User user, Set<UserRole> userRoles){
		
		User localUser=userRepository.findByUsername(user.getUsername());
		
		if(localUser != null) {
		//	throw new Exception("User already exists.Nothing will be done.");
			LOG.info("User {} already exists.Nothing will be done.",user.getUsername());
		}
		else {
			for (UserRole ur : userRoles) {
			roleRepostiory.save(ur.getRole());
			}
			
		}
		
		user.getUserRoles().addAll(userRoles);
		localUser=userRepository.save(user);
		
return localUser;
		}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public long userCount() {
		// TODO Auto-generated method stub
		return userRepository.count();
	}

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return (List<User>) userRepository.findAll();
	}

}
