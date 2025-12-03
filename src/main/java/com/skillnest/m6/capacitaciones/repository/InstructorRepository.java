package com.skillnest.m6.capacitaciones.repository;

import com.skillnest.m6.capacitaciones.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
