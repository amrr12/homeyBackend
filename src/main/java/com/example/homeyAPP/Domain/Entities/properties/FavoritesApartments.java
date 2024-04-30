package com.example.homeyAPP.Domain.Entities.properties;


import com.example.homeyAPP.Domain.Entities.actors.Member;
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
public class FavoritesApartments {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Member member;

    @OneToMany
    @JsonIgnoreProperties({"owner_id"})
    private List<Apartment> favApatments;
}
