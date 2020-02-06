package com.paradigma.PruebaPostgre.mapper;

import com.paradigma.PruebaPostgre.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class BuilderUser {

    PasswordEncoder encoder =
            PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public UserDetails convertToUser(User userLogin){

       return Optional.ofNullable(userLogin)
                .map(this::createUser)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private  org.springframework.security.core.userdetails.User createUser(User userLogin) {

        return new org.springframework.security.core.userdetails.User(
                userLogin.getUserName(),
                encoder.encode(userLogin.getPassword()),
                userLogin.getActive(),
                userLogin.getActive(),
                userLogin.getActive(),
                userLogin.getActive(),
                getRoles(userLogin.getRole()));
    }

    private List<GrantedAuthority> getRoles(byte role){

       return Optional.ofNullable(role)
                .map(this::assignRoles)
                .orElse(null);
    }

    private List<GrantedAuthority> assignRoles(Byte roleResult) {

        String[] roles = {"INVITED","USER","ADMIN"};

       return IntStream.range(0, roles.length)
                .filter(i -> roleResult >= i)
                .mapToObj(i -> new SimpleGrantedAuthority(roles[i]))
                .collect(Collectors.toList());
    }
}
