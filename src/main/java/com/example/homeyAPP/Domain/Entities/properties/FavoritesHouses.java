package com.example.homeyAPP.Domain.Entities.properties;


import com.example.homeyAPP.Domain.Entities.actors.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(builderMethodName = "favhouseBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FavoritesHouses {

    @Id
    @GeneratedValue
    @Column(name = "fvh_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"owner_id"})
    private List<House> favhouses;

    public void customizedSetFavhouses(House house) {
        this.favhouses.add(house);
    }

    public void deleteHouse (House house) {
        this.favhouses.remove(house);
    }

    public FavoritesHouses(Member member,List<House> favhouses) {
        this.member = member;
        this.favhouses = favhouses;
    }
}
