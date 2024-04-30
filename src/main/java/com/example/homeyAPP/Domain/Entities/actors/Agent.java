package com.example.homeyAPP.Domain.Entities.actors;


import com.example.homeyAPP.Domain.Entities.properties.Apartment;
import com.example.homeyAPP.Domain.Entities.properties.House;
import com.example.homeyAPP.Domain.Entities.properties.Property;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Data
@Builder(builderMethodName = "agentBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Agent extends User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private List<House> houses;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private List<Apartment> apartments;




    public Agent(String firstName, String lastName, String email, String userPassword, String phoneNumber, Role role) {
       this.setFirstName(firstName);
       this.setLastName(lastName);
       this.setEmail(email);
       this.setUserPassword(userPassword);
       this.setPhoneNumber(phoneNumber);
       this.setRole(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return getPassword();
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
