package com.example.homeyAPP.Business.config;

import com.example.homeyAPP.Domain.Entities.actors.Agent;
import com.example.homeyAPP.Domain.Entities.actors.Member;
import com.example.homeyAPP.Repositories.AgentRepository;
import com.example.homeyAPP.Repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final MemberRepository memberRepository;
    private final AgentRepository agentRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<Agent> agentOptional = agentRepository.findAgentByEmail(username);
                if (agentOptional.isPresent()) {
                    return agentOptional.get();
                }

                Optional<Member> memberOptional = memberRepository.findAllByEmail(username);
                if (memberOptional.isPresent()) {
                    return memberOptional.get();
                }

                throw new UsernameNotFoundException("User Not Found");
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider () {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
                authProvider.setUserDetailsService(userDetailsService());
                authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
        
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }

}
