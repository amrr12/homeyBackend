package com.example.homeyAPP.Repositories;

import com.example.homeyAPP.Domain.Entities.actors.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    Optional<Agent> findAgentByEmail(String email);


}
