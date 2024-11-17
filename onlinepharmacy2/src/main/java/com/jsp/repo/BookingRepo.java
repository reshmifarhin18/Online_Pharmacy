package com.jsp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer>{

}
