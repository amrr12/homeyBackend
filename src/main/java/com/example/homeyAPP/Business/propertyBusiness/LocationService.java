package com.example.homeyAPP.Business.propertyBusiness;


import com.example.homeyAPP.Domain.Entities.properties.Location;
import com.example.homeyAPP.Repositories.AddressRepository;
import com.example.homeyAPP.Repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    final LocationRepository locationRepository;
    final AddressRepository addressRepository;

    public List<Location> getallLocations (){
        return locationRepository.findAll();
    }
    public Location getLocationByCityAndRegion (String city, String region) {
        Location loc = null;
        List<Location> locations = locationRepository.findAll();
        for (Location l : locations) {
            if(l.getAddress().getCity().equals(city) && l.getAddress().getRegion().equals(region) ) {
                loc = l;
            }
        }
        return loc;
    }
}
