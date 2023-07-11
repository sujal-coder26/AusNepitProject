package com.microservies.hotelservices.services;


import com.microservies.hotelservices.entities.Hotel;

import java.util.List;

public interface HotelService{

    Hotel createHotel( Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel getHotelById(String hotelId);


}
