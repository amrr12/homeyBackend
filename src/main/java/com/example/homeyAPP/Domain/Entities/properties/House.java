package com.example.homeyAPP.Domain.Entities.properties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder(builderMethodName = "houseBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class House extends Property {

    @Id
    @GeneratedValue
    private Long id;

    private double gardenSize;

    private int roomsNum;

    private int bathroomsNum;

    private int floorNum;


    @Column(name = "owner_id")
    private Long owner_id;


    @Column(name = "Houseimages")
    @ElementCollection
    private List<String> images = new ArrayList<>();

    public House (String address,
     String city,
     String region,
     Type type,
     PropertyStatus status,
     double price,
     double size,
     double latitude,
     double longitude,
     double gardenSize,
     int roomsNum,
     int bathroomsNum,
     int floorNum,
     Long owner_id) {
        super(address, city, region, type, status, price, size, latitude, longitude);
        this.gardenSize = gardenSize;
        this.roomsNum = roomsNum;
        this.bathroomsNum = bathroomsNum;
        this.floorNum = floorNum;
        this.owner_id = owner_id;
    }

    public boolean setImages (String imgpath) {

        return this.images.add(imgpath);
    }

}
