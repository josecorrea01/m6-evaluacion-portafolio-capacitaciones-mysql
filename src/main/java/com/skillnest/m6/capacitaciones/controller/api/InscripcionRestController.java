package com.skillnest.m6.capacitaciones.controller.api;

import com.skillnest.m6.capacitaciones.model.Curso;
import com.skillnest.m6.capacitaciones.model.Empleado;
import com.skillnest.m6.capacitaciones.model.Inscripcion;
import com.skillnest.m6.capacitaciones.repository.CursoRepository;
import com.skillnest.m6.capacitaciones.repository.EmpleadoRepository;
import com.skillnest.m6.capacitaciones.repository.InscripcionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionRestController {

    private final CursoRepository cursoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final InscripcionRepository inscripcionRepository;

    public InscripcionRestController(CursoRepository cursoRepository,
                                     EmpleadoRepository empleadoRepository,
                                     InscripcionRepository inscripcionRepository) {
        this.cursoRepository = cursoRepository;
        this.empleadoRepository = empleadoRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    @PostMapping
    public ResponseEntity<?> crearInscripcion(@RequestBody InscripcionRequest request) {

        if (request.getCursoId() == null || request.getEmpleadoId() == null) {
            return ResponseEntity.badRequest().body("cursoId y empleadoId son obligatorios");
        }

        Curso curso = cursoRepository.findById(request.getCursoId())
                .orElse(null);
        Empleado empleado = empleadoRepository.findById(request.getEmpleadoId())
                .orElse(null);

        if (curso == null || empleado == null) {
            return ResponseEntity.badRequest().body("Curso o empleado no encontrado");
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setCurso(curso);
        inscripcion.setEmpleado(empleado);
        inscripcion.setFechaInscripcion(LocalDateTime.now());
        inscripcionRepository.save(inscripcion);

        Map<String, Object> body = new HashMap<>();
        body.put("message", "Inscripción creada correctamente");
        body.put("inscripcionId", inscripcion.getId());

        return ResponseEntity.ok(body);
    }
}
