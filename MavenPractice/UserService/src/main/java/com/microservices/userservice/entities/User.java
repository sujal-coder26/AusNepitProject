package com.microservices.userservice.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "micro_users")
@Builder
public class User {

    @Id
    @Column(name = "id")
    private String userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "about")
    private String about;

    @Transient
    private Rating[] rating = new Rating[]{};
}
