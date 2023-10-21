package com.evaluacion.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaluacion.java.model.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long> {

}
