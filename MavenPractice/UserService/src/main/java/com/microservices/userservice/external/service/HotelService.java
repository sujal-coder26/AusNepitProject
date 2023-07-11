package com.microservices.userservice.external.service;

import com.microservices.userservice.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotels/{hotelid}")
     Hotel getHotel( @PathVariable String hotelid );
}
