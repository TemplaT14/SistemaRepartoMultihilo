package com.josemarti.Reparto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    public static void main(String[] args) {
        int port = 5555;
        String host = "localhost";

        System.out.println("Cliente conectando al servidor...");

        try (Socket socket = new Socket(host, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Conexion establecida. Esperando datos...");

            String linea;
            while ((linea = in.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (UnknownHostException e) {
            System.err.println("Error: Host desconocido " + host);
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        }

        System.out.println("Cliente terminado.");
    }
}
