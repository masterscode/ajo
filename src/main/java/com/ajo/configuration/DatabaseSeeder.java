package com.ajo.configuration;


import com.ajo.models.Admin;
import com.ajo.models.Role;
import com.ajo.models.User;
import com.ajo.models.enums.UserRole;
import com.ajo.repositories.RoleRepository;
import com.ajo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Slf4j
@Component
public class DatabaseSeeder {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;

    public void seedAdminUserEntity() {
        userRepository.findUserByEmail("admin@admin.com")
                .ifPresentOrElse(admin -> {
                    log.info("base admin user already seeded");
                }, () -> {

            User adminToSeed = new User();
                    adminToSeed.setEmail("admin@admin.com");
                    adminToSeed.setPassword(passwordEncoder.encode("password"));
                    adminToSeed.setFirstName("Saparized");
                    adminToSeed.setLastName("Admin");
                    adminToSeed.setPhoneNumber("08000000000");
                    adminToSeed.setRole(
                            roleRepository.findRoleByUserRole(UserRole.ADMIN)
                    );
                    userRepository.save(adminToSeed);
                    log.info("admin has been seeded successfully!");
                });
    }

    public void seedRoleEntity() {
        var roles = roleRepository.findAll().size();
        if (roles < 2){
            Role member = new Role();
            member.setUserRole(UserRole.MEMBER);

            Role admin = new Role();
            admin.setUserRole(UserRole.ADMIN);

            roleRepository.saveAll(List.of(member, admin));
            log.info("roles have been created successfully");
        }else{
            log.info("roles already created");
        }
    }
}
