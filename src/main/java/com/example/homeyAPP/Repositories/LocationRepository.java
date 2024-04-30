package com.example.homeyAPP.Repositories;

import com.example.homeyAPP.Domain.Entities.properties.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {


}
