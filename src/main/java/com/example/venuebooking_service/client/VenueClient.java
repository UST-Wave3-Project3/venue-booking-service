package com.example.venuebooking_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.example.venuebooking_service.dto.VenueDto;

@FeignClient(name = "venue-service", url = "http://localhost:1111/api/venues")
public interface VenueClient {
    @GetMapping("{venueId}")
    VenueDto getVenueById(@PathVariable int venueId);
    
}