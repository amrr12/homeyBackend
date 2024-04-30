package com.example.homeyAPP.Repositories;

import com.example.homeyAPP.Domain.Entities.properties.FavoritesHouses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FavoritesHousesRepository extends JpaRepository<FavoritesHouses, Long> {

    @Query("select f from FavoritesHouses f where f.member.email = :email ")
    public Optional<FavoritesHouses> findFavoritesHousesByMember_Email(String email);

}
