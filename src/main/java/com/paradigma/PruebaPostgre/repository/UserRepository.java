package com.paradigma.PruebaPostgre.repository;

import com.paradigma.PruebaPostgre.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserLogin, Integer>{

    List<UserLogin> findByName(String name);

}
