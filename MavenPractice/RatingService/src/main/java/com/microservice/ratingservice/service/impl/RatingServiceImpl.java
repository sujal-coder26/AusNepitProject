package com.microservice.ratingservice.service.impl;

import com.microservice.ratingservice.entities.Rating;
import com.microservice.ratingservice.repository.RatingRepository;
import com.microservice.ratingservice.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Override
    public Rating createRating ( Rating rating ) {
        return ratingRepository.save ( rating );
    }

    @Override
    public List<Rating> getRatings () {
        return ratingRepository.findAll ();
    }

    @Override
    public List<Rating> getRatingByUserId ( String userId ) {
        return ratingRepository.findByUserId ( userId );
    }

    @Override
    public List<Rating> getRatingByHotelId ( String hotelId ) {
        return ratingRepository.findByHotelId ( hotelId );
    }
}
