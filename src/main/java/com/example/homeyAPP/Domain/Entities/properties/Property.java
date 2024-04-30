package com.example.homeyAPP.Domain.Entities.properties;


import com.example.homeyAPP.Domain.Entities.actors.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Property {

        @Enumerated(EnumType.STRING)
        private Type type;
        @Enumerated(EnumType.STRING)
        private PropertyStatus status;
        private double price;
        private double size;
        private double latitude;
        private double longitude;


}
