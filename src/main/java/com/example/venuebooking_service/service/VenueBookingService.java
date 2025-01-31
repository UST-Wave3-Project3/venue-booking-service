package com.example.venuebooking_service.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.venuebooking_service.client.UserClient;
import com.example.venuebooking_service.client.VenueClient;
import com.example.venuebooking_service.dto.UserDto;
import com.example.venuebooking_service.dto.VenueBookingResponseDto;
import com.example.venuebooking_service.dto.VenueDto;
import com.example.venuebooking_service.entity.VenueBookingEntity;
import com.example.venuebooking_service.repository.VenueBookingRepository;


@Service
public class VenueBookingService {
	@Autowired
    private VenueBookingRepository venueBookingRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private VenueClient venueClient;
    
    public VenueBookingEntity createVenueBooking(VenueBookingEntity venueBooking) {
        UserDto user = userClient.getUserById(venueBooking.getUserId());
        if (user == null) {
            throw new RuntimeException("User not found with ID: " + venueBooking.getUserId());
        }
        VenueDto venue = venueClient.getVenueById(venueBooking.getVenueId());
        if (venue == null || !venue.isVenueAvailable()) {
            throw new RuntimeException("Venue not available with ID: " + venueBooking.getVenueId());
        }
        return venueBookingRepository.save(venueBooking);
    }
        
    public void deleteVenueBooking(int venueBookingId) {
        if (!venueBookingRepository.existsById(venueBookingId)) {
            throw new RuntimeException("Booking not found with ID: " + venueBookingId);
        }
        venueBookingRepository.deleteById(venueBookingId);
    }
    
    public VenueBookingEntity modifyVenueBooking(int venueBookingId, VenueBookingEntity updatedBooking) {
        Optional<VenueBookingEntity> existingBookingOpt = venueBookingRepository.findById(venueBookingId);
        if (existingBookingOpt.isEmpty()) {
            throw new RuntimeException("Booking not found with ID: " + venueBookingId);
        }

        VenueBookingEntity existingBooking = existingBookingOpt.get();
        existingBooking.setVenueId(updatedBooking.getVenueId());
        existingBooking.setUserId(updatedBooking.getUserId());
        existingBooking.setVenuebookingDate(updatedBooking.getVenuebookingDate());
        existingBooking.setStartTime(updatedBooking.getStartTime());
        existingBooking.setEndTime(updatedBooking.getEndTime());

        return venueBookingRepository.save(existingBooking);
    }

    public VenueBookingEntity viewVenueBooking(int bookingId) {
        return venueBookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + bookingId));
    }
    
    public List<VenueBookingEntity> viewAllVenueBookings() {
        return venueBookingRepository.findAll();
    }
    
    public VenueBookingResponseDto getVenueBookingWithUserDetails(int bookingId) {
        Optional<VenueBookingEntity> venueBookingOpt = venueBookingRepository.findById(bookingId);
        
        if (venueBookingOpt.isPresent()) {
            VenueBookingEntity venueBooking = venueBookingOpt.get();
 
            UserDto user = userClient.getUserById(venueBooking.getUserId());

            VenueDto venue = venueClient.getVenueById(venueBooking.getVenueId());

            VenueBookingResponseDto response = new VenueBookingResponseDto();
            response.setVenueBookingId(venueBooking.getVenueBookingId());
            response.setVenueBookingDate(venueBooking.getVenuebookingDate());
            response.setStartTime(venueBooking.getStartTime());
            response.setEndTime(venueBooking.getEndTime());
            
            response.setUserId(venueBooking.getUserId());
            response.setVenueId(venueBooking.getVenueId());
            
            response.setUser(user);
            response.setVenue(venue);

            return response;
        } else {
            throw new RuntimeException("Venue Booking not found with ID: " + bookingId);
        }
    }
    public List<VenueBookingEntity> getVenueByBookingDate(LocalDate bookingDate) {
        return venueBookingRepository.findByVenuebookingDate(bookingDate);
    }
    
   
}