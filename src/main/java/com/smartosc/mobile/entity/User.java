package com.smartosc.mobile.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameUser;

    private String address;

    private String phone;

    private String avatar;

    private String email;

    private String password;

    private Date createDate;

    private Date updateDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Orders> orders;

    //    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_role",
//            joinColumns = @JoinColumn(name = "role_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private Set<Role> roles = new HashSet<>();
    @ManyToOne
    @JoinColumn
    private Role role;

    public User(Long id, String nameUser, String address, String phone, String email, String password, Role role) {
        this.id=id;
        this.nameUser=nameUser;
        this.address=address;
        this.phone=phone;
        this.email=email;
        this.password=password;
        this.role=role;
    }
}
