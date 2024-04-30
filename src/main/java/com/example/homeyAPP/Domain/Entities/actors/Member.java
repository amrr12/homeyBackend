package com.example.homeyAPP.Domain.Entities.actors;

import com.example.homeyAPP.Domain.Entities.properties.Apartment;
import com.example.homeyAPP.Domain.Entities.properties.FavoritesHouses;
import com.example.homeyAPP.Domain.Entities.properties.House;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder(builderMethodName = "memberBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends User implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "fvh_id")
    private FavoritesHouses favoritesHouses;



    public Member(String firstName, String lastName, String email, String userPassword, String phoneNumber, Role role) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setUserPassword(userPassword);
        this.setPhoneNumber(phoneNumber);
        this.setRole(role);

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole().name()));
    }

    @Override
    public String getPassword() {
        return getUserPassword();
    }


    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
