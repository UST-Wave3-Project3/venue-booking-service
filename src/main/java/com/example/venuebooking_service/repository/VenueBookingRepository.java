package com.example.venuebooking_service.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.venuebooking_service.entity.VenueBookingEntity;

@Repository
public interface VenueBookingRepository extends JpaRepository<VenueBookingEntity, Integer> {
	List<VenueBookingEntity> findByVenuebookingDate(LocalDate bookingDate);
}