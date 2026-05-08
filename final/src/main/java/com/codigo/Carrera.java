package com.codigo;

public class Carrera {
    private String nombre;
    private Escuela escuela;

    public Carrera(String nombre, Escuela escuela) {
        this.nombre = nombre;
        this.escuela = escuela;
    }

    public String getNombre() { return nombre; }
    public Escuela getEscuela() { return escuela; }
}
