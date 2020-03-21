package com.smartosc.mobile.service.Impl;

import com.smartosc.mobile.entity.Role;
import com.smartosc.mobile.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImpl {
    @Autowired
    private static RoleRepository roleRepository;

    public  Role findById(Long id) {
        Role role = roleRepository.findById(id).get();
        return role;
    }
}
