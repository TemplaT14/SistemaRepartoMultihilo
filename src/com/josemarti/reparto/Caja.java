package com.josemarti.reparto;

public class Caja {

    private int id;
    private String nombre;

    public Caja(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Caja: " + id + " -> " + nombre;
    }
}
