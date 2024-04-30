package com.example.homeyAPP.Domain.Entities.properties;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder(builderMethodName = "location_builder")
@NoArgsConstructor
@AllArgsConstructor
@Entity


public class Location {

        @Id
        @GeneratedValue
        private Long id;

        @ManyToOne
        @JoinColumn(name = "addressid")
        private Address address;

        @OneToMany(mappedBy = "location")
        @JsonIgnoreProperties("location")
        private List<House> houses = new ArrayList<>();

        @OneToMany(mappedBy = "location")
        private List<Apartment> apartments = new ArrayList<>();

        public Location(Address address) {
                this.address = address;
        }
        // Constructors, getters, and setters
}
