package com.example.homeyAPP.Domain.Entities.actors;

import com.example.homeyAPP.Domain.Entities.properties.Property;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class User {



    private String firstName;

    private String lastName;

    private String email;

    private String userPassword;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;



}
