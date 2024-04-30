package com.example.homeyAPP.Business.propertyBusiness;

import com.example.homeyAPP.Domain.Entities.actors.Member;
import com.example.homeyAPP.Domain.Entities.properties.FavoritesHouses;
import com.example.homeyAPP.Domain.Entities.properties.House;
import com.example.homeyAPP.Repositories.FavoritesHousesRepository;
import com.example.homeyAPP.Repositories.HouseRepository;
import com.example.homeyAPP.Repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FvHouseService {

   final FavoritesHousesRepository fvservice;
   final MemberRepository memberRepository;
   final HouseRepository houseRepository;

   public String addHouseToFavorites (Long houseId, String memberEmail) {

      Optional<House> houseOptional = houseRepository.findById(houseId);
      House house = houseOptional.isPresent() ? houseOptional.get() : null ;
      Optional<FavoritesHouses> fv = fvservice.findFavoritesHousesByMember_Email(memberEmail);

      if (fv.isPresent()) {
         FavoritesHouses fvh = fv.get();
         for (House h : fvh.getFavhouses()) {
            if (h == house) {
               return "this house is already in your favorites ! ";
            }
         }
         fvh.customizedSetFavhouses(house);
      }else{
         Optional<Member> member = memberRepository.findAllByEmail(memberEmail);
         Member m = member.isPresent() ? member.get() : null;
         List<House> fvhouseList = new ArrayList<>();
         fvhouseList.add(house);
         fvservice.save(new FavoritesHouses(m,fvhouseList));
      }
      return "House added successfully to to favorites!";
   }

   public String deleteFromFvHouse (Long houseId, String memberEmail) {
      Optional<House> houseOptional = houseRepository.findById(houseId);
      House house = houseOptional.isPresent() ? houseOptional.get() : null ;
      Optional<FavoritesHouses> fv = fvservice.findFavoritesHousesByMember_Email(memberEmail);

      if (fv.isPresent()) {
         FavoritesHouses fvh = fv.get();
         for (House h : fvh.getFavhouses()) {
            if (h == house) {
               fvh.deleteHouse(h);
               fvservice.save(fvh);
            }
         }
         if(fvh.getFavhouses().isEmpty()){
            fvservice.delete(fvh);
         }
      }
      return "house deleted from favorites !";
   }

   public List<House> getMemberfvh(String email) {
      Optional<FavoritesHouses> optionalFavoritesHouses = fvservice.findFavoritesHousesByMember_Email(email);

      if (optionalFavoritesHouses.isPresent()) {
         FavoritesHouses fvh = optionalFavoritesHouses.get();
         return fvh.getFavhouses();
      } else {
         // No favorites houses found for the member email
         System.out.println("No favorites houses found for member with email: " + email);
         return Collections.emptyList(); // Return an empty list
      }
   }

}
