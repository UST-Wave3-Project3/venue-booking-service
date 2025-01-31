package com.example.venuebooking_service.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="venuebooking_service")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenueBookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="v_booking_id")
    private int venueBookingId;
    
    @Column(name="user_id")
    private int userId;
    
    @Column(name="venue_id")
    private int venueId;
    
    @Column(name="v_booking_date")
    private LocalDate venuebookingDate;
    
    @Column(name="v_start_time")
    private LocalTime startTime;
    
    @Column(name="v_end_time")
    private LocalTime endTime;
}