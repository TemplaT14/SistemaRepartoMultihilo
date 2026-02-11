package com.josemarti.Reparto;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        //Variables
        int numTrabajadores = 2;
        int port = 5555;
        Camion camion = new Camion();
        Thread[] trabajadores = new Thread[numTrabajadores];

        System.out.println("Iniciando servidor con " + numTrabajadores + " trabajadores...");

        for (int i = 0; i < numTrabajadores; i++) {
            trabajadores[i] = new Thread(new Trabajador(String.valueOf(i + 1), camion));
        }

        System.out.println("Esperando al cliente...");

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado");


            for (Thread h : trabajadores) {
                h.start();
            }
            //Esperar a que terminen los trabajadores
            try {
                for (Thread trabajador : trabajadores) {
                    trabajador.join();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Error esperando a trabajadores: " + e.getMessage());
            }
            //Terminada la produccion
            System.out.println("Camion lleno");
            camion.terminar();


            //Crear repartidor e iniciar
            Thread hRepartidor = new Thread(new Repartidor("1", camion, socket));
            hRepartidor.start();
            //Esperara a que termine el repartidor
            try {
                hRepartidor.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Error esperando a repartidor: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("ERROR: No se pudo iniciar el servidor en el puerto " + port);
        }

        System.out.println("Servidor terminado correctamente.");
    }
}
