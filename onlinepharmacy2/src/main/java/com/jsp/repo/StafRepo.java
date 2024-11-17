package com.jsp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.entity.Staff;

public interface StafRepo extends JpaRepository<Staff, Integer> {

}
