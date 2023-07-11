package com.microservice.ratingservice.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("user_ratings")
public class Rating {
    @Id
    public String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;

}
