package com.josemarti.reparto;

import java.util.ArrayList;
import java.util.List;

public class Camion {

    private List<Caja> listaCajas;
    private int capacidad;
    private int idCajas;
    private int totalTrabajadores;

    public Camion() {
        this.idCajas = 0;
        this.listaCajas = new ArrayList<>();
        this.capacidad = 10;
        this.totalTrabajadores = 0;
    }

    public synchronized void trabajadoTerminado() {
        this.totalTrabajadores++;
        notifyAll();
    }

    public synchronized void cargar(Caja caja) {
        while (listaCajas.size() == capacidad) {
            try {
                wait();
                System.out.println("Esperando para cargar, camion lleno.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        listaCajas.add(caja);
        notifyAll();

    }

    public synchronized Caja descargar() {
        while (listaCajas.isEmpty() && totalTrabajadores < 3) {
            try {
                wait();
                System.out.println("Esperando para descargar, camion vacio.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (listaCajas.isEmpty()) {
            return null;
        }

        Caja caja = listaCajas.get(listaCajas.size() - 1);
        listaCajas.remove(caja);
        notifyAll();
        return caja;
    }

    public synchronized int idUnico() {
        idCajas++;
        return idCajas;
    }
}
