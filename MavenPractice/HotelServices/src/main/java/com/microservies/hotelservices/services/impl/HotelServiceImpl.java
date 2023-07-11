package com.microservies.hotelservices.services.impl;
import com.microservies.hotelservices.entities.Hotel;
import com.microservies.hotelservices.exception.ResourceNotFoundException;
import com.microservies.hotelservices.repository.HotelRepository;
import com.microservies.hotelservices.services.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public Hotel createHotel ( Hotel hotel ) {
        String hotelId = UUID.randomUUID ().toString ();
        hotel.setId ( hotelId );
        return hotelRepository.save ( hotel );
    }

    @Override
    public List<Hotel> getAllHotels () {
        return hotelRepository.findAll ();
    }

    @Override
    public Hotel getHotelById ( String hotelId ) {
        return hotelRepository.findById ( hotelId ).orElseThrow ( ()-> new ResourceNotFoundException ("Hotel with given id not found") );
    }
}
