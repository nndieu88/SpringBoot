package com.smartosc.mobile.service;

import com.smartosc.mobile.entity.User;
import com.smartosc.mobile.model.dto.UserDto;
import com.smartosc.mobile.model.request.CreateUserRequest;
import com.smartosc.mobile.model.request.UpdateUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<UserDto> findAllUser();

    public List<User> listUser();

    public UserDto getUserById(Long id);

    public UserDto createUser(CreateUserRequest createUserRequest);

    public UserDto updateUser(Long id, UpdateUserRequest updateUserRequest);

    public void deleteUser(Long id);
}
