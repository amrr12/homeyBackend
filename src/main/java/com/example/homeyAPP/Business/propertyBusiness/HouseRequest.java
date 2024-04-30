package com.example.homeyAPP.Business.propertyBusiness;


import com.example.homeyAPP.Domain.Entities.actors.Agent;
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

    private String city;
    private String region;
    private Type type;
    private PropertyStatus status;
    private double price;
    private double size;
    private double gardenSize;
    private int roomsNum;
    private int bathroomsNum;
    private int floorNum;
    private String owner_id;
    private double latitude;
    private double longitude;

}
