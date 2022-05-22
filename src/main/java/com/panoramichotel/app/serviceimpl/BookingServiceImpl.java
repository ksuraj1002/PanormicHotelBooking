package com.panoramichotel.app.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panoramichotel.app.entities.Guest;
import com.panoramichotel.app.service.BookingService;
import com.panoramichotel.app.utility.ResponseData;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired BookingDaoImpl bookinDaoImpl;
	
	@Override
	public ResponseData processBooking(Guest guest) {
		ResponseData response = new ResponseData();
		if(guest.getNumOfPeople()>3 || guest.getNumOfPeople() == 0) {
			response.setResCode(0);
			response.setResMsg("Atmost three person are allowed per booking.");
		}else if(guest.getCheckOut().getDate()-guest.getCheckIn().getDate()>3) {
			response.setResCode(0);
			response.setResMsg("No. of days allowed only 3.");
		}else if(guest.getCheckIn().getTime()>guest.getCheckOut().getTime()) {
			response.setResCode(0);
			response.setResMsg("Check out date can't be smaller than check in date.");
		}else {
			try{
				bookinDaoImpl.save(guest);
				response.setResCode(1);
				response.setResMsg("Successfully booked. Your booking id is "+guest.getId());
			}catch(Exception e) {
				response.setResCode(0);
				response.setResMsg("There is some error");
			}
			
		}
		return response;
	}

	@Override
	public ResponseData cancelBooking(Integer bookingId) {
		ResponseData response = new ResponseData();
		try{
			bookinDaoImpl.deleteById(bookingId);
			response.setResCode(1);
			response.setResMsg("Booking cancelled successfully");
		}catch(Exception e){
			response.setResCode(0);
			response.setResMsg("Booking id does not exist");
		}
		return response;
	}

	@Override
	public Optional<Guest> getBookingDetails(Integer bookingId) {

			Optional<Guest> guest=bookinDaoImpl.findById(bookingId);

		return guest;
	}

}
