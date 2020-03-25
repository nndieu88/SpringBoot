package com.smartosc.mobile;

import com.smartosc.mobile.entity.Role;
import com.smartosc.mobile.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootApplication
public class MobileApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileApplication.class, args);
    }

}
