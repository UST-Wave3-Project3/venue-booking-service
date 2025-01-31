package com.example.venuebooking_service.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueBookingResponseDto {
    private int venueBookingId;
    private int userId;
    private int venueId;
    private LocalDate venueBookingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private UserDto user;
    private VenueDto venue;
}