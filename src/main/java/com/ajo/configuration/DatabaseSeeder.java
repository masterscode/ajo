package com.ajo.configuration;


import com.ajo.models.Admin;
import com.ajo.models.Role;
import com.ajo.models.enums.UserRole;
import com.ajo.repositories.AdminRepository;
import com.ajo.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
    private final AdminRepository adminRepository;
    private  final PasswordEncoder passwordEncoder;

    public void seedAdminUserEntity() {
        adminRepository.findByEmail("admin@admin.com")
                .ifPresentOrElse(admin -> log.info("base admin user already exists"), () -> {
            Admin admin = new Admin();
                    admin.setEmail("admin@admin.com");
                    admin.setPassword(passwordEncoder.encode("password"));
                    admin.setFirstName("Saparized");
                    admin.setLastName("Admin :)");
                    admin.setRole(
                            roleRepository.findRoleByUserRole(UserRole.ADMIN)
                    );
                    adminRepository.save(admin);
                    log.info("admin has been created!");
                });
    }

    public void seedRoleEntity() {
        var roles = roleRepository.findAll().size();
        if (roles < 2){
            Role member = new Role();
            member.setUserRole(UserRole.USER);

            Role admin = new Role();
            admin.setUserRole(UserRole.ADMIN);

            roleRepository.saveAll(List.of(member, admin));
            log.info("roles have been created successfully");
        }else{
            log.info("roles already created");
        }
    }
}
