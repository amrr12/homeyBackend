package com.example.homeyAPP.Business.auth;

import com.example.homeyAPP.Business.config.JwtService;
import com.example.homeyAPP.Domain.Entities.actors.Agent;
import com.example.homeyAPP.Domain.Entities.actors.Member;
import com.example.homeyAPP.Domain.Entities.actors.Role;
import com.example.homeyAPP.Repositories.AgentRepository;
import com.example.homeyAPP.Repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final AgentRepository agentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getUserPassword());
        if (!request.getIsAgent()) {
            Member member = new Member(request.getFirstName(),request.getLastName(),request.getEmail(),encodedPassword,request.getPhoneNumber(),Role.Member);
            memberRepository.save(member);
            var jwtToken = jwtService.generateToken(member);
            return AuthenticationResponse.builder().token(jwtToken).build();
        }else {
            Agent agent = new Agent(request.getFirstName(),request.getLastName(),request.getEmail(),request.getUserPassword(),request.getPhoneNumber(),Role.Agent);
            agentRepository.save(agent);
            var jwtToken = jwtService.generateTokenA(agent);
            return AuthenticationResponse.builder().token(jwtToken).build();
        }

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
          authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(
                          request.getEmail(),
                          request.getPassword()
                  )
          );
         Optional<Agent> agentOptional = agentRepository.findAgentByEmail(request.getEmail());
         if (agentOptional.isPresent()) {
            Agent agent = agentOptional.get();
            var jwtToken = jwtService.generateTokenA(agent);
            return AuthenticationResponse.builder().token(jwtToken).build();
         }

         Optional<Member> memberOptional = memberRepository.findAllByEmail(request.getEmail());
         if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            var jwtToken = jwtService.generateToken(member);
            return AuthenticationResponse.builder().token(jwtToken).build();
         }
        throw new UsernameNotFoundException("User Not Found");
    }
}
