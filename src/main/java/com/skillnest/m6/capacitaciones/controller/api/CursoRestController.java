package com.skillnest.m6.capacitaciones.controller.api;

import com.skillnest.m6.capacitaciones.repository.CursoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cursos")
public class CursoRestController {

    private final CursoRepository cursoRepository;

    public CursoRestController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public List<CursoDto> listarCursos() {
        return cursoRepository.findAll()
                .stream()
                .map(CursoDto::fromEntity)
                .collect(Collectors.toList());
    }
}
