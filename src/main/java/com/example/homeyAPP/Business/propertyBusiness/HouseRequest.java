package com.example.homeyAPP.Business.propertyBusiness;


import com.example.homeyAPP.Domain.Entities.properties.PropertyStatus;
import com.example.homeyAPP.Domain.Entities.properties.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HouseRequest {

    private String address;
    private String city;
    private String region;
    private Type type;
    private PropertyStatus status;
    private double price;
    private double size;
    private double latitude;
    private double longitude;
    private double gardenSize;
    private int roomsNum;
    private int bathroomsNum;
    private int floorNum;
    private Long owner_id;

}
