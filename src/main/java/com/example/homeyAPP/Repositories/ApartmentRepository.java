package com.example.homeyAPP.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.homeyAPP.Domain.Entities.properties.Apartment;


public interface ApartmentRepository extends JpaRepository<Apartment, Long> {


}
