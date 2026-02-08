package com.josemarti.reparto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {
    public static void main(String... args) {
        //Variables
        int port = 5555;
        String host = "localhost";

        System.out.println("Iniciando comunicacion...");
        try(Socket socket = new Socket(host, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){

            System.out.println("Repartidor asignado. Esperando cajas... - Cliente avisado");
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        }catch (IOException e) {
            System.out.println("Error: fallo de Iniciar servidor");
        }
    }
}
