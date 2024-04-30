package com.example.homeyAPP.Domain.Entities.properties;

import com.example.homeyAPP.Domain.Entities.actors.Agent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
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

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnoreProperties("houses")
    private Agent owner_id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonIgnoreProperties("houses")
    private Location location;

    @Column(name = "Houseimages")
    @ElementCollection
    private List<String> images = new ArrayList<>();



    public House (
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
     Agent owner_id,
     Location location) {
        super(type, status, price, size, latitude, longitude);
        this.gardenSize = gardenSize;
        this.roomsNum = roomsNum;
        this.bathroomsNum = bathroomsNum;
        this.floorNum = floorNum;
        this.owner_id = owner_id;
        this.location =location;
    }

    public boolean setImages (String imgpath) {

        return this.images.add(imgpath);
    }




}
