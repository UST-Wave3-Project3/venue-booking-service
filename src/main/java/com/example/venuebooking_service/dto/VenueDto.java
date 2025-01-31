package com.example.venuebooking_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueDto {
    
	private int venueId;

    private String venueName;
    
    private String venueBuilding;
 
    private String venueFloor;
 
    private int venueCapacity;
    
    private boolean hasAc;

    private boolean hasProjector;

    private boolean venueAvailable;
}