package com.skillnest.m6.capacitaciones.controller;

import com.skillnest.m6.capacitaciones.model.Curso;
import com.skillnest.m6.capacitaciones.model.Instructor;
import com.skillnest.m6.capacitaciones.repository.CursoRepository;
import com.skillnest.m6.capacitaciones.repository.InstructorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/cursos")
public class AdminCursoController {

    private final CursoRepository cursoRepository;
    private final InstructorRepository instructorRepository;

    public AdminCursoController(CursoRepository cursoRepository, InstructorRepository instructorRepository) {
        this.cursoRepository = cursoRepository;
        this.instructorRepository = instructorRepository;
    }

    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("cursos", cursoRepository.findAll());
        return "admin/cursos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoCurso(Model model) {
        Curso curso = new Curso();
        curso.setActivo(true);
        curso.setInstructor(new Instructor());
        model.addAttribute("curso", curso);
        model.addAttribute("instructores", instructorRepository.findAll());
        return "admin/cursos/form";
    }

    @PostMapping
    public String guardarCurso(@ModelAttribute("curso") Curso curso, Model model) {
        cursoRepository.save(curso);
        return "redirect:/admin/cursos";
    }
}
