package com.microservies.hotelservices.controller;

import com.microservies.hotelservices.entities.Hotel;
import com.microservies.hotelservices.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor

public class HotelController {
    private final HotelService hotelService;

    @PostMapping()
//    @PreAuthorize ("hasAuthority('Admin')")
    public ResponseEntity<Hotel> createHotel( @RequestBody Hotel hotel) {
        return ResponseEntity.status ( HttpStatus.CREATED ).body ( hotelService.createHotel ( hotel ) );
    }

//    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId){
        return ResponseEntity.ok (hotelService.getHotelById ( hotelId ) );
    }

    @GetMapping()
    public ResponseEntity<List<Hotel>> getAll(){
        return ResponseEntity.ok (hotelService.getAllHotels ());
    }

}
