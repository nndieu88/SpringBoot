package com.smartosc.mobile.model.mapper;

import com.smartosc.mobile.entity.Role;
import com.smartosc.mobile.entity.User;
import com.smartosc.mobile.model.dto.UserDto;
import com.smartosc.mobile.model.request.CreateUserRequest;
import com.smartosc.mobile.model.request.UpdateUserRequest;

import java.util.Date;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getNameUser());
        userDto.setAddress(user.getAddress());
        userDto.setPhone(user.getPhone());
        userDto.setAvatar(user.getAvatar());
        userDto.setEmail(user.getEmail());
        userDto.setCreateDate(user.getCreateDate());
        userDto.setUpdateDate(user.getUpdateDate());
        userDto.setRole(user.getRole().getNameRole());

        return userDto;
    }

    public static User toUser(CreateUserRequest cur) {
        User user = new User();
        user.setNameUser(cur.getName());
        user.setEmail(cur.getEmail());
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());

        String hash= BCrypt.hashpw(cur.getPassword(),BCrypt.gensalt(12));
        user.setPassword(hash);

        return user;
    }

    public static User toUser(UpdateUserRequest uur, Long id, Date date, Role role, String password) {
        User user = new User();

        user.setId(id);
        user.setNameUser(uur.getName());
        user.setAddress(uur.getAddress());
        user.setPhone(uur.getPhone());
        user.setAvatar(uur.getAvatar());
        user.setEmail(uur.getEmail());
        user.setPassword(password);
        user.setRole(role);
        user.setCreateDate(date);
        user.setUpdateDate(new Date());

//        String hash= BCrypt.hashpw(uur.getPassword(),BCrypt.gensalt(12));
//        user.setPassword(hash);

        return user;
    }
}
