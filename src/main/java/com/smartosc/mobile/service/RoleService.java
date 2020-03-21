package com.smartosc.mobile.service;

import com.smartosc.mobile.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    public Role finById(Long id);
}
