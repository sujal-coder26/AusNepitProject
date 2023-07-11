package com.microservices.userservice.external.service;

import com.microservices.userservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
    @PostMapping("/ratings")
    ResponseEntity<Rating> createRating( Rating rating);

    @PutMapping("/rating/{ratingId}")
    ResponseEntity<Rating>  updateRating(@PathVariable String ratingId, Rating rating);

    @DeleteMapping("/rating/{ratingId}")
    ResponseEntity<Rating>  deleteRating(@PathVariable String ratingId);

}
