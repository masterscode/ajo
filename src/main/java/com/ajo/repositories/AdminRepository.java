package com.ajo.repositories;

import com.ajo.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
//    Optional<Admin> findByEmail(String email);
}
