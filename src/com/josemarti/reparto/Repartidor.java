package com.josemarti.reparto;

import java.io.*;
import java.net.Socket;

public class Repartidor implements Runnable {

    private Camion camion;
    private String nombre;
    private Socket socket;

    public Repartidor(String nombre, Camion camion, Socket socket) {
        this.camion = camion;
        this.nombre = nombre;
        this.socket = socket;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(nombre);

        System.out.println("Empieza a trabajar el repartidor: " + Thread.currentThread().getName());

        try (Socket socket =this.socket;
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            while (true) {
                Caja caja = camion.descargar();
                if(caja == null) {
                    break;
                }
                String mensaje = "Entrega " + caja.getId() + ". " + caja.getNombre() + " - Cliente avisado";
                out.println(mensaje);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Repartidor " + Thread.currentThread().getName() + " terminado");
    }
}