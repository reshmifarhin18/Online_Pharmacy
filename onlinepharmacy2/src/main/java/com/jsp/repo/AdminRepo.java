package com.jsp.repo;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
	@Query("select a from Admin a where a.adminEmail=?1")
	Optional<Admin> findByEmail(String email);
}
