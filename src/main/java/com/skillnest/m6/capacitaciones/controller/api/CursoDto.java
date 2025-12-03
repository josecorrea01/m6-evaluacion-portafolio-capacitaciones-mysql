package com.skillnest.m6.capacitaciones.controller.api;

import com.skillnest.m6.capacitaciones.model.Curso;

public class CursoDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
    private Integer cupoMaximo;
    private boolean activo;
    private String instructorNombre;

    public static CursoDto fromEntity(Curso curso) {
        CursoDto dto = new CursoDto();
        dto.setId(curso.getId());
        dto.setNombre(curso.getNombre());
        dto.setDescripcion(curso.getDescripcion());
        dto.setCupoMaximo(curso.getCupoMaximo());
        dto.setActivo(curso.isActivo());
        dto.setFechaInicio(curso.getFechaInicio() != null ? curso.getFechaInicio().toString() : null);
        dto.setFechaFin(curso.getFechaFin() != null ? curso.getFechaFin().toString() : null);
        dto.setInstructorNombre(curso.getInstructor() != null ? curso.getInstructor().getNombre() : null);
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(Integer cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getInstructorNombre() {
        return instructorNombre;
    }

    public void setInstructorNombre(String instructorNombre) {
        this.instructorNombre = instructorNombre;
    }
}
