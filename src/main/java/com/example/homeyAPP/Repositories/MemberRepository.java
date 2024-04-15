package com.example.homeyAPP.Repositories;

import com.example.homeyAPP.Domain.Entities.actors.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findAllByEmail(String email);

}
