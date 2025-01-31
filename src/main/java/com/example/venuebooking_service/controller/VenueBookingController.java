package com.example.venuebooking_service.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.venuebooking_service.dto.VenueBookingResponseDto;
import com.example.venuebooking_service.entity.VenueBookingEntity;
import com.example.venuebooking_service.service.VenueBookingService;

@RestController
@RequestMapping("/api/venue-bookings")
public class VenueBookingController {
	@Autowired
    private VenueBookingService venueBookingService;

    @PostMapping
    public ResponseEntity<VenueBookingEntity> createVenueBooking(@RequestBody VenueBookingEntity venueBooking) {
        VenueBookingEntity createdBooking = venueBookingService.createVenueBooking(venueBooking);
        return ResponseEntity.ok(createdBooking);
    }
    
    @DeleteMapping("/{venueBookingId}")
    public ResponseEntity<Void> deleteVenueBooking(@PathVariable int venueBookingId) {
        venueBookingService.deleteVenueBooking(venueBookingId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{venueBookingId}")
    public ResponseEntity<VenueBookingEntity> modifyVenueBooking(
            @PathVariable int venueBookingId,
            @RequestBody VenueBookingEntity updatedBooking) {
       VenueBookingEntity modifiedBooking = venueBookingService.modifyVenueBooking(venueBookingId, updatedBooking);
        return ResponseEntity.ok(modifiedBooking);
    }

    @GetMapping("/booking/{venueBookingId}")
    public ResponseEntity<VenueBookingEntity> viewVenueBooking(@PathVariable int venueBookingId) {
        VenueBookingEntity booking = venueBookingService.viewVenueBooking(venueBookingId);
        return ResponseEntity.ok(booking);
    }

    @GetMapping
    public ResponseEntity<List<VenueBookingEntity>> viewAllVenueBookings() {
        List<VenueBookingEntity> bookings = venueBookingService.viewAllVenueBookings();
        return ResponseEntity.ok(bookings);
    }
    
    @GetMapping("/{venueBookingId}")
    public VenueBookingResponseDto getVenueBookingWithUserDetails(@PathVariable int venueBookingId) {
        return venueBookingService.getVenueBookingWithUserDetails(venueBookingId);
    }
    
    @GetMapping("/booking-date")
	public ResponseEntity<List<VenueBookingEntity>> getVenueByBookingDate(@RequestParam("BookingDate") LocalDate bookingDate){
		return new ResponseEntity<List<VenueBookingEntity>>(venueBookingService.getVenueByBookingDate(bookingDate),HttpStatus.OK);
	}
}