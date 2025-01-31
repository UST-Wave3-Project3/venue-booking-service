package com.example.venuebooking_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.venuebooking_service.dto.UserDto;

@FeignClient(name = "user-service", url = "http://localhost:2222/api/users")
public interface UserClient {

	@GetMapping("{userId}")
    UserDto getUserById(@PathVariable int userId);
	
}
