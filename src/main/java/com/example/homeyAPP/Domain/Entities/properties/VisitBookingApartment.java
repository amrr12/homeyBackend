package com.example.homeyAPP.Domain.Entities.properties;


import com.example.homeyAPP.Domain.Entities.actors.Agent;
import com.example.homeyAPP.Domain.Entities.actors.Member;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder(builderMethodName = "vbApartment")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VisitBookingApartment {

    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    private Boolean confirmed;

    @OneToOne
    @JsonIgnoreProperties({"houses","apartments"})
    private Agent agent;

    @OneToOne
    private Member member;

    @OneToOne
    @JsonIgnoreProperties("owner_id")
    private Apartment apartment;

}
