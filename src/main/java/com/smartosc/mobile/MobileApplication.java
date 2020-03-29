package com.smartosc.mobile;

import com.smartosc.mobile.entity.Role;
import com.smartosc.mobile.entity.User;
import com.smartosc.mobile.repository.RoleRepository;
import com.smartosc.mobile.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootApplication
public class MobileApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileApplication.class, args);
    }

    @Bean
    public CommandLineRunner initUser(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            roleRepository.save(new Role(1L, "ROLE_ADMIN"));
            roleRepository.save(new Role(2L, "ROLE_USER"));
            userRepository.save(new User(1L, "admin00", "Hà Nội", "0901234567", "admin@gmail.com", BCrypt.hashpw("123456", BCrypt.gensalt(12)), new Role(1L, "ROLE_ADMIN")));
        };
    }
}
