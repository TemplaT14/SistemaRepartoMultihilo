package com.josemarti.Reparto;

public class Caja {

    private final int id;
    private final String nombre;

    public Caja(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    //Estos los pongo por que tu los pides en el enunciado aunque no los uso.
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Caja " + id + ". " + nombre;
    }
}
