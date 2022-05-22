package com.panoramichotel.app.service;

import com.panoramichotel.app.entities.Guest;
import com.panoramichotel.app.utility.ResponseData;

import java.util.Optional;

public interface BookingService {

	ResponseData processBooking(Guest guest);

	ResponseData cancelBooking(Integer bookingId);

	Optional<Guest> getBookingDetails(Integer bookingId);
}
