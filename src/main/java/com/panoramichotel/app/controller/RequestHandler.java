package com.panoramichotel.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.panoramichotel.app.entities.Guest;
import com.panoramichotel.app.service.BookingService;
import com.panoramichotel.app.utility.ResponseData;

import java.util.Optional;

@RestController
public class RequestHandler {
	
	@Autowired BookingService bookinService;
		
	@PostMapping("/booking")
	public ResponseEntity<String> saveBookingDetails(@RequestBody Guest guest) {
		ResponseData responseData=bookinService.processBooking(guest);
		String response = new Gson().toJson(responseData,ResponseData.class);
		return ResponseData.getStatus(response);
	}
	
	@PostMapping("/cancel")
	public ResponseEntity<String> cancelBooking(@RequestParam Integer bookingId) {
		ResponseData responseData=bookinService.cancelBooking(bookingId);
		String response = new Gson().toJson(responseData,ResponseData.class);
		return ResponseData.getStatus(response);
	}

	@GetMapping("/details")
	public ResponseEntity<String> getBookingDetails(@RequestParam Integer bookingId) {
		Optional<Guest> responseData=bookinService.getBookingDetails(bookingId);
		String response = new Gson().toJson(responseData,Optional.class);
		return ResponseData.getStatus(response);
	}
	
}
