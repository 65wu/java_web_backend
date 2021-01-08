package com.java_web.backend;

import com.java_web.backend.dao.User.RoleRepository;
import com.java_web.backend.model.po.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class RoleInitializer {
    @Autowired
    private RoleRepository roleRepository;
    @Test
    void addRecord() {
        ArrayList<Role> newRoleList = new ArrayList<>() {{
            add(new Role(0, "最高管理员"));
            add(new Role(1, "管理员"));
            add(new Role(2, "普通用户"));
        }};
        for(Role r : newRoleList) {
            roleRepository.save(r);
        }
    }
}
