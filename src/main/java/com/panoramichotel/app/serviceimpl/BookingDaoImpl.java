package com.panoramichotel.app.serviceimpl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.panoramichotel.app.entities.Guest;

public interface BookingDaoImpl extends JpaRepository<Guest, Integer> {

}
