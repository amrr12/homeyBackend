package com.example.homeyAPP.Domain.Entities.properties;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(builderMethodName = "address_builder")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "addressid")
    private Long id;
    private String city;
    private String region;

    public Address (String city, String region) {
        this.city = city;
        this.region = region;
    }

}
