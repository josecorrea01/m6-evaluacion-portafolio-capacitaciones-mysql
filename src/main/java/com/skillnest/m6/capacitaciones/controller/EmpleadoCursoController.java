package com.skillnest.m6.capacitaciones.controller;

import com.skillnest.m6.capacitaciones.model.Curso;
import com.skillnest.m6.capacitaciones.model.Empleado;
import com.skillnest.m6.capacitaciones.model.Inscripcion;
import com.skillnest.m6.capacitaciones.repository.CursoRepository;
import com.skillnest.m6.capacitaciones.repository.EmpleadoRepository;
import com.skillnest.m6.capacitaciones.repository.InscripcionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/empleado/cursos")
public class EmpleadoCursoController {

    private final CursoRepository cursoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final InscripcionRepository inscripcionRepository;

    public EmpleadoCursoController(CursoRepository cursoRepository,
                                   EmpleadoRepository empleadoRepository,
                                   InscripcionRepository inscripcionRepository) {
        this.cursoRepository = cursoRepository;
        this.empleadoRepository = empleadoRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("cursos", cursoRepository.findAll());
        return "empleado/cursos/lista";
    }

    @PostMapping("/{id}/inscribirse")
    public String inscribirse(@PathVariable("id") Long id,
                              RedirectAttributes redirectAttributes) {

        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        Empleado empleado = empleadoRepository.findByEmail("empleado@empresa.com")
                .orElseGet(() -> {
                    Empleado e = new Empleado();
                    e.setNombre("Empleado Prueba");
                    e.setEmail("empleado@empresa.com");
                    e.setPassword("dummy");
                    e.setRol("EMPLEADO");
                    return empleadoRepository.save(e);
                });

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setCurso(curso);
        inscripcion.setEmpleado(empleado);
        inscripcion.setFechaInscripcion(LocalDateTime.now());
        inscripcionRepository.save(inscripcion);

        redirectAttributes.addFlashAttribute("inscripcionExitosa", true);
        return "redirect:/empleado/cursos";
    }
}
