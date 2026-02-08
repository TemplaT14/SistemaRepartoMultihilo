package com.josemarti.reparto;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        //Variablaes
        int port = 5555;

        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Servidor Iniciado");

                while (true){
                    Socket socket = serverSocket.accept();
                    System.out.println("Cliente conectado");
                    Camion camion = new Camion();

                        Thread h1 = new Thread(new Trabajador("1",camion));
                        Thread h2 = new Thread(new Trabajador("2",camion));
                        Thread h3 = new Thread(new Trabajador("3",camion));
                        Thread h4 = new Thread(new Repartidor("4",camion, socket));

                        h1.start();
                        h2.start();
                        h3.start();
                        h4.start();

                }
        }catch (BindException e){
            System.out.println("ERROR: puerto ocupado");
        }catch (IOException e) {
            System.out.println("ERROR: conexion servidor" + e.getMessage());
        }
        System.out.println("Servidor finalizado");
    }
}
