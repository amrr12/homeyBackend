package com.example.homeyAPP.Repositories;


import com.example.homeyAPP.Domain.Entities.properties.House;
import com.example.homeyAPP.Domain.Entities.properties.PropertyStatus;
import com.example.homeyAPP.Domain.Entities.properties.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface HouseRepository extends JpaRepository<House, Long> {

     @Query("SELECT h FROM House h WHERE h.owner_id = :id")
     List<House> findAllByOwner_id(Long id);

     @Query("SELECT h FROM House h WHERE h.type = :type")
     List<House> findAllByType(Type type);

     @Query("SELECT h FROM House h WHERE h.status = :status")
     List<House> findAllByStatus(PropertyStatus status);

     @Query("SELECT h FROM House h WHERE h.price < :price")
     List<House> findAllByPriceLowerThen(double price);

     @Query("SELECT h FROM House h WHERE h.price > :price")
     List<House> findAllByPriceGreaterThan(double price);

     List<House> findAllByRoomsNumBeforeAndBathroomsNum(int rooms,int bathrooms);


     List<House> findAllByCityAndRegion(String city,String region);
}
