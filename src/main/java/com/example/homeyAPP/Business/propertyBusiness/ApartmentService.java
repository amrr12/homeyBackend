package com.example.homeyAPP.Business.propertyBusiness;


import com.example.homeyAPP.Domain.Entities.properties.Apartment;
import com.example.homeyAPP.Domain.Entities.properties.House;
import com.example.homeyAPP.Repositories.ApartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApartmentService {

    final ApartmentRepository apartmentRepository;

    public Apartment addApartment(ApartementRequest reqApartment) {
        Apartment apartment = new Apartment(
                reqApartment.getAddress(),
                reqApartment.getCity(),
                reqApartment.getRegion(),
                reqApartment.getType(),
                reqApartment.getStatus(),
                reqApartment.getPrice(),
                reqApartment.getSize(),
                reqApartment.getLatitude(),
                reqApartment.getLongitude(),
                reqApartment.getRoomsNum(),
                reqApartment.getBathroomsNum(),
                reqApartment.getFloor(),
                reqApartment.getOwner_id()
        );
        return apartmentRepository.save(apartment);
    }

    public Apartment updateApartment(Long id, ApartementRequest reqApartment) {
        Optional<Apartment> optionalApartment = apartmentRepository.findById(id);
        if (optionalApartment.isPresent()) {
            Apartment apartment = optionalApartment.get();
            apartment.setAddress(reqApartment.getAddress());
            apartment.setCity(reqApartment.getCity());
            apartment.setRegion(reqApartment.getRegion());
            apartment.setType(reqApartment.getType());
            apartment.setStatus(reqApartment.getStatus());
            apartment.setPrice(reqApartment.getPrice());
            apartment.setSize(reqApartment.getSize());
            apartment.setLatitude(reqApartment.getLatitude());
            apartment.setLongitude(reqApartment.getLongitude());
            apartment.setRoomsNum(reqApartment.getRoomsNum());
            apartment.setBathroomsNum(reqApartment.getBathroomsNum());
            apartment.setFloor(reqApartment.getFloor());
            apartment.setOwner_id(reqApartment.getOwner_id());

            return apartmentRepository.save(apartment);
        } else {
            throw new ResourceNotFoundException("Apartment not found with id: " + id);
        }
    }

    public void deleteApartment(Long id) {
        Optional<Apartment> optionalHouse = apartmentRepository.findById(id);
        if (optionalHouse.isPresent()) {
            apartmentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Apartment not found with id: " + id);
        }
    }

    public List<Apartment> getAllApartment () {
        return apartmentRepository.findAll();
    }

    public Optional<Apartment> getApartmentById(Long id) {
        return apartmentRepository.findById(id);
    }
}
