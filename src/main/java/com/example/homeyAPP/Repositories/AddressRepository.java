package com.example.homeyAPP.Repositories;


import com.example.homeyAPP.Domain.Entities.properties.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {


    Address findByCityAndRegion(String city, String region);
}
