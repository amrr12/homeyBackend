package com.example.homeyAPP.Domain.Entities.properties;

import com.example.homeyAPP.Domain.Entities.actors.Agent;
import com.example.homeyAPP.Domain.Entities.actors.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder(builderMethodName = "apartmentBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Apartment extends Property {

    @Id
    @GeneratedValue
    private Long id;
    private int roomsNum;
    private int bathroomsNum;
    private int floor;
    @Column(name = "apartmentimages")
    @ElementCollection
    private List<String> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    @JsonIgnoreProperties("apartments")
    private Agent owner_id;

    @ManyToOne
    @JoinColumn(name = "favhouses")
    @JsonIgnore
    private FavoritesApartments favoritesApartments;

    public Apartment (
                      Type type,
                  PropertyStatus status,
                  double price,
                  double size,
                  int roomsNum,
                  int bathroomsNum,
                  int floor,
                      Agent owner_id,
                      double latitude,
                      double longitude) {
        super(type,status, price, size, latitude, longitude);
        this.roomsNum = roomsNum;
        this.bathroomsNum = bathroomsNum;
        this.floor = floor;
        this.owner_id = owner_id;
    }


    public Boolean setImages(String imgUrl) {
        return this.images.add(imgUrl);
    }
}
