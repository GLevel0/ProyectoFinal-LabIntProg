package com.codigo;

public class Escuela {
    private String nombre;
    private int id;

    public Escuela(int id, String nombre) {
        this.id = id;   
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }
    public int getId() { return id; }
}
