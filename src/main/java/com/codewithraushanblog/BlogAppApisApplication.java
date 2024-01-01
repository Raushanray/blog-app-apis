package com.codewithraushanblog;


import com.codewithraushanblog.config.AppConstants;
import com.codewithraushanblog.entities.Role;
import com.codewithraushanblog.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;


    public static void main(String[] args) {
        SpringApplication.run(BlogAppApisApplication.class, args);
    }


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Override
    public void run(String... args) throws Exception {

        System.out.println(this.passwordEncoder.encode("xyz"));

        try {
            Role role = new Role();
            role.setId(AppConstants.ADMIN_USER);
            role.setName("ROLE_ADMIN");

            Role role1 = new Role();
            role1.setId(AppConstants.NORMAL_USER);
            role1.setName("ROLE_NORMAL");

            List<Role> lsRole = new ArrayList<>();
            lsRole.add(role);
            lsRole.add(role1);

            List<Role> result = this.roleRepo.saveAll(lsRole);
            result.forEach(r -> {
                System.out.println(r.getName());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
