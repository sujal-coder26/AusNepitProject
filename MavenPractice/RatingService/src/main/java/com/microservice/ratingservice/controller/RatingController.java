package com.microservice.ratingservice.controller;
import com.microservice.ratingservice.entities.Rating;
import com.microservice.ratingservice.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ratings")
@AllArgsConstructor

public class RatingController {

    private final RatingService ratingService;

//    @PreAuthorize ("hasAuthority('SCOPE_internal') || hasAuthority('Admin')" )
    @PostMapping()
    public ResponseEntity<Rating> createRating ( @RequestBody Rating rating ) {
        return ResponseEntity.status ( HttpStatus.CREATED ).body ( ratingService.createRating ( rating ) );
    }

    @GetMapping()
    public ResponseEntity<List<Rating>> getAllRatings(){
        return ResponseEntity.ok ( ratingService.getRatings () );
    }

//    @PreAuthorize ("hasAuthority('SCOPE_internal') || hasAuthority('Admin')" )
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId){
        return ResponseEntity.ok ( ratingService.getRatingByUserId (userId) );
    }
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok ( ratingService.getRatingByHotelId (hotelId) );
    }


}
