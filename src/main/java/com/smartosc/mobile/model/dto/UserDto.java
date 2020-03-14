package com.smartosc.mobile.model.dto;

import com.smartosc.mobile.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

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
    private boolean status;
    private Date createDate;
    private Date updateDate;
    private Set<Role> role;
}
