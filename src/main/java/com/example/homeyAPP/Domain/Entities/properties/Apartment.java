package com.example.homeyAPP.Domain.Entities.properties;

import com.example.homeyAPP.Domain.Entities.actors.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "owner_id")
    private Long owner_id;
    public Apartment (String address,
                  String city,
                  String region,
                      Type type,
                  PropertyStatus status,
                  double price,
                  double size,
                  double latitude,
                  double longitude,
                  int roomsNum,
                  int bathroomsNum,
                  int floor,Long owner_id) {
        super(address, city, region, type,status, price, size, latitude, longitude);
        this.roomsNum = roomsNum;
        this.bathroomsNum = bathroomsNum;
        this.floor = floor;
        this.owner_id = owner_id;
    }


}
