package com.evaluacion.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaluacion.java.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
