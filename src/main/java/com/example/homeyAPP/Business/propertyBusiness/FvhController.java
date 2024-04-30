package com.example.homeyAPP.Business.propertyBusiness;

import com.example.homeyAPP.Domain.Entities.properties.House;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fvh")
@RequiredArgsConstructor
public class FvhController {

    final FvHouseService service;


    @GetMapping("/getfvh/{email}")
    public List<House> getfvh(@PathVariable String email) {
        return service.getMemberfvh(email);
    }

    @PostMapping("/addtofvh/{email}/{houseid}")
    public String addToFv(@PathVariable Long houseid, @PathVariable String email) {
        return  service.addHouseToFavorites(houseid, email);
    }


    @PostMapping("/deletefromfvh/{houseid}/{email}")
    public String deleteFromFvh(@PathVariable Long houseid, @PathVariable String email) {
        return  service.deleteFromFvHouse(houseid, email);
    }

}
