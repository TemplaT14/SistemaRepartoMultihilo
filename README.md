# SistemaRepartoMultihilo

# Sistema de Carga y Reparto de Mercancía

Este proyecto consiste en una aplicación Java que simula un sistema logístico utilizando **programación multifilo** y comunicación mediante **sockets**. El sistema gestiona la producción coordinada de cajas, su almacenamiento temporal en un camión y su posterior envío a un cliente remoto.

## Arquitectura del Sistema

La aplicación se divide en un modelo de **Servidor (Productor/Consumidor)** y un **Cliente**:

### 1. Servidor (Orquestador)

El servidor gestiona la lógica central y la concurrencia:

* 
**Camión (Buffer):** Actúa como recurso compartido con capacidad limitada a 10 cajas. Controla el acceso sincronizado para evitar condiciones de carrera.


* 
**Trabajadores (Productores):** Hilos que fabrican 5 cajas cada uno y las cargan en el camión.


* 
**Repartidor (Consumidor):** Hilo que extrae las cajas del camión y las envía al cliente vía sockets conforme están disponibles.



### 2. Cliente (Receptor)

* Se conecta al servidor para recibir la mercancía.


* Muestra por pantalla la información de cada caja entregada y finaliza al recibir la notificación de cierre.



## Requisitos Técnicos y Sincronización

* 
**Capacidad Máxima:** El camión solo puede albergar 10 cajas simultáneamente.


* 
**Control de Hilos:** El repartidor espera si el camión está vacío, y los trabajadores esperan si está lleno.


* 
**Comunicación:** Se utiliza el puerto de red para la transferencia de datos entre el repartidor y el cliente.


* 
**Finalización:** El sistema garantiza un cierre limpio una vez que todas las cajas han sido producidas y entregadas.



## Estructura de Clases

* 
`Caja`: Clase básica con ID y nombre.


* 
`Camion`: Recurso compartido sincronizado.


* 
`Trabajador`: Lógica del hilo productor.


* 
`Repartidor`: Lógica del hilo consumidor y gestión del socket.


* 
`Servidor`: Punto de entrada y orquestador.


* 
`Cliente`: Receptor de la información.
