package com.ajo.configuration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableJpaRepositories("com.ajo.repositories")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class BaseConfiguration {
    private final  DatabaseSeeder seeder;
    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper defaultMapper() {
        return new ModelMapper();
    }

    @EventListener
    public void seedDataBase(ContextRefreshedEvent event){
        seeder.seedRoleEntity();
        seeder.seedAdminUserEntity();
    }
}
