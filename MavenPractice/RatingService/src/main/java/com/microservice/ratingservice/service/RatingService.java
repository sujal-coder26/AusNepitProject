package com.microservice.ratingservice.service;

import com.microservice.ratingservice.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating createRating( Rating rating);
    List<Rating> getRatings();
    List<Rating> getRatingByUserId(String userId);
    List<Rating> getRatingByHotelId(String hotelId);

}
