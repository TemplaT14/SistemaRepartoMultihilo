package com.josemarti.Reparto;

import java.util.ArrayList;
import java.util.List;

public class Camion {

    private final int capacidadMax;
    private final List<Caja> listaCajas;
    private int idUnico;
    private boolean trabajoTerminado;

    public Camion() {
        this.capacidadMax = 10;
        this.listaCajas = new ArrayList<>();
        this.idUnico = 0;
        this.trabajoTerminado = false;

    }

    public synchronized int generarId() {
        idUnico++;
        return idUnico;
    }

    public synchronized void terminar() {
        this.trabajoTerminado = true;
        notifyAll();
    }

    public synchronized void cargar(Caja caja) {
        while (listaCajas.size() == capacidadMax) {
            System.out.println("Camion lleno, esperando a que el repartidor descargue...");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        listaCajas.add(caja);
        notifyAll();
    }

    public synchronized Caja descargar() {
        while (listaCajas.isEmpty() && !trabajoTerminado) {
            System.out.println("Camion vacio, repartidor esperando cajas...");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }

        if (listaCajas.isEmpty() && trabajoTerminado) {
            return null;
        }

        Caja caja = listaCajas.remove(0);
        notifyAll();
        return caja;
    }

}
