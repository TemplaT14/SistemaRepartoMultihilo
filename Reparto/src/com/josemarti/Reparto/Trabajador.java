package com.josemarti.Reparto;

public class Trabajador implements Runnable {

    private final String nombre;
    private final Camion camion;

    public Trabajador(String nombre, Camion camion) {
        this.nombre = nombre;
        this.camion = camion;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Trabajador " + nombre);
        System.out.println("Empieza a trabajar " + Thread.currentThread().getName());

        String[] productos = { "Fruta", "Leche", "Frutos secos", "Legumbres", "Pescado" };

        for (int i = 0; i < 5; i++) {
            //Crear momentos de espera
            int random = (int) (Math.random() * 1000);
            try{
                Thread.sleep(random);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
            //Crear caja
            int id = camion.generarId();
            Caja caja = new Caja(id, productos[i]);
            camion.cargar(caja);
            System.out.println("Creamos la " + caja);
        }

        System.out.println("Termina de trabajar " + Thread.currentThread().getName());
    }
}
