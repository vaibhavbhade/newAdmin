package com.swiftdroid.posterhouse.admin.repo;

import org.springframework.data.repository.CrudRepository;

import com.swiftdroid.posterhouse.admin.model.Role;



public interface RoleRepostiory extends CrudRepository<Role, Long>{
Role findByname(String name);

}
