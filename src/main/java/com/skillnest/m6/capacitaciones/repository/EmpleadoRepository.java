package com.skillnest.m6.capacitaciones.repository;

import com.skillnest.m6.capacitaciones.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Optional<Empleado> findByEmail(String email);
}
