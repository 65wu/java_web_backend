package com.java_web.backend.dao.User;

import org.springframework.data.repository.CrudRepository;
import com.java_web.backend.model.po.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}