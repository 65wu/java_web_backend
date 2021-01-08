package com.java_web.backend.dao.User;

import com.java_web.backend.model.po.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
