package com.codigo;

import java.util.ArrayList;
import java.util.List;

public class Escuela {
    private String nombre;
    private List<Carrera> carreras = new ArrayList<>();
    private int id;

    public Escuela(int id, String nombre) {
        this.id = id;   
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }
    public List<Carrera> getCarreras() { return carreras; }
    public int getId() { return id; }

    public boolean addCarrera(Carrera carrera_to_be_added) 
    {
        if (carrera_to_be_added == null) {
            return false;
        }

        carreras.add(carrera_to_be_added);

        return true;
    }
}
