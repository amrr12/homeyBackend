package com.example.homeyAPP.Business.propertyBusiness;


import com.example.homeyAPP.Domain.Entities.properties.House;
import com.example.homeyAPP.Domain.Entities.properties.PropertyStatus;
import com.example.homeyAPP.Domain.Entities.properties.Type;
import com.example.homeyAPP.Repositories.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HouseServices {

    final HouseRepository houseRepository;

    public House addHouse(HouseRequest reqHouse) {
        House house = new House(
                reqHouse.getAddress(),
                reqHouse.getCity(),
                reqHouse.getRegion(),
                reqHouse.getType(),
                reqHouse.getStatus(),
                reqHouse.getPrice(),
                reqHouse.getSize(),
                reqHouse.getLatitude(),
                reqHouse.getLongitude(),
                reqHouse.getGardenSize(),
                reqHouse.getRoomsNum(),
                reqHouse.getBathroomsNum(),
                reqHouse.getFloorNum(),
                reqHouse.getOwner_id()
                );
        return houseRepository.save(house);
    }

    public List<House> getallHouses() {
        return houseRepository.findAll();
    }

    public Optional<House> getHouseById(Long id) {
        return houseRepository.findById(id);
    }

    public List<House> getHousesByOwnerId(Long id) {
        return houseRepository.findAllByOwner_id(id);
    }

    public List<House> getHousesByType(Type type) {
        return houseRepository.findAllByType(type);
    }

    public List<House> getHousesByStatus(PropertyStatus status) {
        return houseRepository.findAllByStatus(status);
    }

    public List<House> getHousesByPriceLowerThen(double price) {
        return houseRepository.findAllByPriceLowerThen(price);
    }

    public List<House> getHousesByPriceGreaterThen(double price) {
        return houseRepository.findAllByPriceGreaterThan(price);
    }

    public List<House> gethousesByRoomsNumBeforeAndBathroomsNum(int rooms, int bathrooms) {
        return houseRepository.findAllByRoomsNumBeforeAndBathroomsNum(rooms, bathrooms);
    }

    public List<House> getHousesbyCityandRegion(String city, String region) {
        return houseRepository.findAllByCityAndRegion(city,region);
    }

    public House updateHouse(Long id, HouseRequest reqHouse) {
        Optional<House> optionalHouse = houseRepository.findById(id);
        if (optionalHouse.isPresent()) {
            House house = optionalHouse.get();
            house.setAddress(reqHouse.getAddress());
            house.setCity(reqHouse.getCity());
            house.setRegion(reqHouse.getRegion());
            house.setType(reqHouse.getType());
            house.setStatus(reqHouse.getStatus());
            house.setPrice(reqHouse.getPrice());
            house.setSize(reqHouse.getSize());
            house.setLatitude(reqHouse.getLatitude());
            house.setLongitude(reqHouse.getLongitude());
            house.setGardenSize(reqHouse.getGardenSize());
            house.setRoomsNum(reqHouse.getRoomsNum());
            house.setBathroomsNum(reqHouse.getBathroomsNum());
            house.setFloorNum(reqHouse.getFloorNum());
            house.setOwner_id(reqHouse.getOwner_id());

            return houseRepository.save(house);
        } else {
            throw new ResourceNotFoundException("House not found with id: " + id);
        }
    }

    public void deleteHouse(Long id) {
        Optional<House> optionalHouse = houseRepository.findById(id);
        if (optionalHouse.isPresent()) {
            houseRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("House not found with id: " + id);
        }
    }
}
