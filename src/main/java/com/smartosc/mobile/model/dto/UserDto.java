package com.smartosc.mobile.model.dto;

import com.smartosc.mobile.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String avatar;
    private String email;
}
