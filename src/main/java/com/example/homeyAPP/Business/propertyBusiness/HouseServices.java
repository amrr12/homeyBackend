package com.example.homeyAPP.Business.propertyBusiness;


import com.example.homeyAPP.Domain.Entities.actors.Agent;
import com.example.homeyAPP.Domain.Entities.properties.*;
import com.example.homeyAPP.Repositories.AddressRepository;
import com.example.homeyAPP.Repositories.AgentRepository;
import com.example.homeyAPP.Repositories.HouseRepository;
import com.example.homeyAPP.Repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class HouseServices {

    final HouseRepository houseRepository;
    final AddressRepository addressRepository;
    final LocationRepository locationRepository;
    final AgentRepository agentRepository;

    public House addHouse(HouseRequest reqHouse) {
        String city = reqHouse.getCity();
        String region = reqHouse.getRegion();

        String email = reqHouse.getOwner_id();

        Optional<Agent> agent = agentRepository.findAgentByEmail(email);
        Agent a = null;
        if (agent.isPresent()) {
            // If not, create a new address
            a = agent.get();
        }


        // Check if an address with the same city and region exists
        Address address = addressRepository.findByCityAndRegion(city, region);
        if (address == null) {
            // If not, create a new address
            address = new Address(city, region);
            addressRepository.save(address);
        }

        Location location = new Location(address);
        locationRepository.save(location);

        House house = new House(
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
                a,
                location
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



    public House updateHouse(Long id, HouseRequest reqHouse) {
        Optional<House> optionalHouse = houseRepository.findById(id);
        Optional<Agent> a = agentRepository.findAgentByEmail(reqHouse.getOwner_id());
        Agent agent = a.isPresent() ? a.get() : null;
        if (optionalHouse.isPresent()) {
            House house = optionalHouse.get();
            house.setType(reqHouse.getType());
            house.setStatus(reqHouse.getStatus());
            house.setPrice(reqHouse.getPrice());
            house.setSize(reqHouse.getSize());
            house.setGardenSize(reqHouse.getGardenSize());
            house.setRoomsNum(reqHouse.getRoomsNum());
            house.setBathroomsNum(reqHouse.getBathroomsNum());
            house.setFloorNum(reqHouse.getFloorNum());
            house.setOwner_id(agent);
            return houseRepository.save(house);
        } else {
            throw new ResourceNotFoundException("House not found with id: " + id);
        }
    }

    public House updateHouseImage(Long id, String imgUrl) {
        Optional<House> optionalHouse = houseRepository.findById(id);
        if (optionalHouse.isPresent()) {
            House house = optionalHouse.get();
            house.setImages(imgUrl);

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

    public String uploadImageToFileSystem(MultipartFile file,Long id) throws IOException {
        String uploadDir = "C:/Users/Asus/Downloads/homeyAPP/uploads/";

        String filePath=uploadDir+file.getOriginalFilename();

        Optional<House> house = houseRepository.findById(id);
                if (house.isPresent()){
                    House selectedHouse = house.get();
                    file.transferTo(new File(filePath));
                    updateHouseImage(selectedHouse.getId(),filePath);
                }else {
                    return "error";
                }

        if (house != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }



    public List<byte[]> downloadImageFromFileSystem(Long id) throws IOException {
        Optional<House> optionalHouse = getHouseById(id);
        List<byte[]> imageBytesList = new ArrayList<>();

        if (optionalHouse.isPresent()) {
            House house = optionalHouse.get();
            List<String> imagePaths = house.getImages();

            for (String imagePath : imagePaths) {
                byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
                imageBytesList.add(imageBytes);
            }
        }
        System.out.println(imageBytesList);
        return imageBytesList;
    }

}
