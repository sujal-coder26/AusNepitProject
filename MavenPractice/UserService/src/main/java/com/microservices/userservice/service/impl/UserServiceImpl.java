package com.microservices.userservice.service.impl;

import com.microservices.userservice.entities.Hotel;
import com.microservices.userservice.entities.Rating;
import com.microservices.userservice.entities.User;
import com.microservices.userservice.exception.ResourceNotFoundException;
import com.microservices.userservice.external.service.HotelService;
import com.microservices.userservice.repository.UserRepository;
import com.microservices.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RestTemplate restTemplate;

    private final HotelService hotelService;

    @Override
    public User saveUser ( User user ) {
        String randomUserId = UUID.randomUUID ( ).toString ( );
        user.setUserId ( randomUserId );
        return userRepository.save ( user );
    }

    @Override
    public List<User> getAllUser () {
        return userRepository.findAll ( );
    }

    @Override
    public User getUserById ( String userId ) {
        User user =
                this.userRepository
                        .findById ( userId )
                        .orElseThrow (
                                () -> new ResourceNotFoundException ( "User with " + userId + "id is not found" ) );

        ResponseEntity<List<Rating>> responseEntity =
                restTemplate.exchange (
                        "http://RATING-SERVICE/ratings/users/" + userId ,
                        HttpMethod.GET ,
                        null ,
                        new ParameterizedTypeReference<> ( ) {
                        } );
        Object[] ratings = Objects.requireNonNull ( responseEntity.getBody ( ) ).stream ( ).peek ( rating -> {
            Hotel hotel = hotelService.getHotel ( rating.getHotelId ( ) );
            rating.setHotel ( hotel );
        } ).toArray ( );

        user.setRating ( Stream.of ( ratings ).toArray ( Rating[]::new ) );
        return user;
    }
}
