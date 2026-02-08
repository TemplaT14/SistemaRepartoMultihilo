package com.josemarti.reparto;

public class Trabajador implements Runnable {

    private Camion camion;
    private String nombre;

    public Trabajador(String nombre, Camion camion) {
        this.nombre = nombre;
        this.camion = camion;
    }

    @Override
    public void run() {

        Thread.currentThread().setName(nombre);
        // Variables
        String[] productos = { "Fruta", "Leche", "Frutos secos", "Legumbres", "Pescado" };
        System.out.println("Empieza a trabajar Trabajador " + Thread.currentThread().getName());

        for (int i = 0; i < 5; i++) {
            int id = camion.idUnico();
            Caja caja = new Caja(id, productos[i]);
            camion.cargar(caja);
            System.out.println("Creamos la caja " + id + ". " + productos[i]);
        }
        camion.trabajadoTerminado();
        System.out.println("Trabajador " + Thread.currentThread().getName() + " terminado.");
    }

}
