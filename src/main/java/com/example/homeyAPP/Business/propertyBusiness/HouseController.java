package com.example.homeyAPP.Business.propertyBusiness;


import com.example.homeyAPP.Domain.Entities.properties.House;
import com.example.homeyAPP.Domain.Entities.properties.PropertyStatus;
import com.example.homeyAPP.Domain.Entities.properties.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/house")
@RequiredArgsConstructor
public class HouseController {

    private final HouseServices houseServices;

    @GetMapping("/allhouses")
    public List<House> getAllHouses() {
        return  houseServices.getallHouses();
    }

    @GetMapping("/housebyid/{id}")
    public ResponseEntity<House> getHouseById(@PathVariable Long id) {
        Optional<House> house = houseServices.getHouseById(id);
        if (house.isPresent()) {
            return ResponseEntity.ok(house.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/housebyownerid/{id}")
    public List<House> housesbyOwnerId(@PathVariable Long id) {
        return houseServices.getHousesByOwnerId(id);
    }

    @GetMapping("/housebytype/{type}")
    public List<House> housesbyType(@PathVariable Type type) {
        return houseServices.getHousesByType(type);
    }

    @GetMapping("/housebystatus/{status}")
    public List<House> housesbyStatus(@PathVariable PropertyStatus status) {
        return houseServices.getHousesByStatus(status);
    }

    @GetMapping("/housebypricelowerthen/{price}")
    public List<House> housesbyPriceLowerThen(@PathVariable double price) {
        return houseServices.getHousesByPriceLowerThen(price);
    }
    @GetMapping("/housebypricegreaterthen/{price}")
    public List<House> housesbyPriceGreaterThen(@PathVariable double price) {
        return houseServices.getHousesByPriceGreaterThen(price);
    }

    @GetMapping("/housebyroomsnumbeforeandbathroomsnum/{rooms}/{bathrooms}")
    public List<House> housesByRoomsNumBeforeAndBathroomsNum(@PathVariable int rooms,@PathVariable int bathrooms) {
        return houseServices.gethousesByRoomsNumBeforeAndBathroomsNum(rooms, bathrooms);
    }

    @GetMapping("/housesbycityandregion/{city}/{region}")
    public List<House> housesByCityandRegion(@PathVariable String city,@PathVariable String region) {
        return houseServices.getHousesbyCityandRegion(city,region);
    }



    @PostMapping("/add")
    public ResponseEntity<String> addHouse(@RequestBody HouseRequest houseRequest) {
        houseServices.addHouse(houseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("House added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateHouse(@PathVariable Long id, @RequestBody HouseRequest houseRequest) {
        houseServices.updateHouse(id, houseRequest);
        return ResponseEntity.ok("House updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHouse(@PathVariable Long id) {
        houseServices.deleteHouse(id);
        return ResponseEntity.ok("House deleted successfully");
    }


    @PostMapping("/image/{houseId}")
    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image")MultipartFile file,@PathVariable Long houseId) throws IOException {
        String uploadImage = houseServices.uploadImageToFileSystem(file,houseId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

}
