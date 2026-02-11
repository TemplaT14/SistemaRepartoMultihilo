package com.josemarti.Reparto;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Repartidor implements Runnable {

    private final String nombre;
    private final Camion camion;
    private final Socket socket;

    public Repartidor(String nombre, Camion camion, Socket socket) {
        this.nombre = nombre;
        this.camion = camion;
        this.socket = socket;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Repartidor " + nombre);

        System.out.println("Repartidor asignado. Esperando cajas... - Cliente avisado");

        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            out.println("Repartidor asignado. Esperando cajas... - Cliente avisado");

            while (true) {
                //Crear momentos de espera
                int random = (int) (Math.random() * 1000);
                try{
                    Thread.sleep(random);
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }

                Caja caja = camion.descargar();

                if (caja == null) {
                    break;
                }
                //Avisar cliente
                String mensaje = "Entregada " + caja + " - Cliente avisado";
                String mensajeCliente = "Entregada " + caja;
                System.out.println(mensaje);
                out.println(mensajeCliente);
            }

            System.out.println("No quedan mas cajas. Reparto finalizado. - Cliente avisado");
            out.println("No quedan mas cajas. Reparto finalizado.");

        } catch (IOException e) {
            System.err.println("Error en Repartidor: " + e.getMessage());
        }

        System.out.println("Repartidor finalizado");
    }
}
