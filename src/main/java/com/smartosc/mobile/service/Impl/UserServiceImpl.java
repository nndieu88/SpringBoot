package com.smartosc.mobile.service.Impl;

import com.smartosc.mobile.entity.Role;
import com.smartosc.mobile.entity.User;
import com.smartosc.mobile.exception.DuplicateRecordException;
import com.smartosc.mobile.exception.InternalServerException;
import com.smartosc.mobile.exception.NotFoundException;
import com.smartosc.mobile.model.dto.UserDto;
import com.smartosc.mobile.model.mapper.UserMapper;
import com.smartosc.mobile.model.request.CreateUserRequest;
import com.smartosc.mobile.model.request.UpdateUserRequest;
import com.smartosc.mobile.repository.RoleRepository;
import com.smartosc.mobile.repository.UserRepository;
import com.smartosc.mobile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserDto> findAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(UserMapper.toUserDto(user));
        }
        return userDtos;
    }

    @Override
    public List<User> listUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new NotFoundException("No user found");
        }

        return UserMapper.toUserDto(user.get());
    }

    @Override
    public UserDto createUser(CreateUserRequest createUserRequest) {
        User user = userRepository.findByEmail(createUserRequest.getEmail());
        if (user != null) {
            throw new DuplicateRecordException("Email already exists in the system");
        }
        User user1 = UserMapper.toUser(createUserRequest);
        user1.setRole(roleRepository.findById(2L).get());
        try {

            userRepository.save(user1);
        } catch (Exception ex) {
            throw new InternalServerException("Can't create user");
        }

        return UserMapper.toUserDto(user1);
    }

    @Override
    public UserDto updateUser(Long id, UpdateUserRequest updateUserRequest) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new NotFoundException("Not found user");
        }
        User user1 = UserMapper.toUser(updateUserRequest, id, user.get().getCreateDate(),user.get().getRole());
        try {
            userRepository.save(user1);
        } catch (Exception ex) {
            throw new InternalServerException("Can't update user");
        }
        return UserMapper.toUserDto(user1);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new NotFoundException("Not found user");
        }
        try {
            userRepository.deleteById(id);
        } catch (Exception ex) {
            throw new InternalServerException("Can't delete user");
        }
    }
}
