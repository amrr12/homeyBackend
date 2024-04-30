package com.example.homeyAPP.Business.propertyBusiness;


import com.example.homeyAPP.Domain.Entities.properties.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationController {

    final LocationService service;
    @GetMapping("/loc")
    public List<Location> getAllLocations() {
        return  service.getallLocations();
    }

    @GetMapping("/locbycityandregion/{city}/{region}")
    public Location getLocation(@PathVariable String city, @PathVariable String region) {
        return service.getLocationByCityAndRegion(city,region);
    }
}
