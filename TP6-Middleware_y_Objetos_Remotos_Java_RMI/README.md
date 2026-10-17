📘 Trabajo Práctico N° 6
**Tema** Middleware y Objetos Remotos - Java RMI
**Asignatura:** Desarrollo de Aplicaciones para Ambientes Distribuidos  
**Docente:** Lic. Gabriel Artaza  

**Objetivo**
Desarrollar una aplicación distribuida utilizando Java RMI (Remote Method Invocation), implementando una arquitectura cliente-servidor que permita ejecutar métodos remotos desde una aplicación cliente.

*Arquitectura del Proyecto*
La aplicación se divide en cuatro paquetes:

TP6-Middleware_y_Objetos_Remotos_Java_RMI
│
├── cliente
│   └── Cliente.java
│
├── interfaces
│   └── ServicioRemoto.java
│
├── modelo
│   └── Estadisticas.java
│
└── servidor
    ├── ServicioRemotoImpl.java
    └── Servidor.java

*Funcionalidades Implementadas*

La aplicación ofrece tres servicios remotos:

Calcular estadísticas sobre un conjunto de números.
Validar un número de DNI.
Filtrar textos mediante una palabra de búsqueda.
Descripción de los Componentes
Interfaz Remota
Archivo:

interfaces/ServicioRemoto.java
Define los métodos que podrán ser utilizados por los clientes remotos.

Métodos implementados:

calcularEstadisticas()
validarDNI()
filtrarTexto()
Clase de Modelo
Archivo:

modelo/Estadisticas.java
Representa el objeto que almacena:

Promedio
Valor máximo
Valor mínimo
Desviación estándar
Implementa la interfaz:

Serializable
para permitir su envío a través de la red.

*Funcionamiento del Sistema*
El cliente establece conexión con el registro RMI.

Posteriormente obtiene una referencia al objeto remoto publicado por el servidor.

Cuando el usuario selecciona una opción:

El cliente realiza una llamada remota.
El servidor recibe la solicitud.
Ejecuta el procesamiento correspondiente.
Envía el resultado al cliente.
El cliente muestra el resultado por pantalla.
Diagrama de Funcionamiento
             CLIENTE
                |
                |
        Llamada Remota
                |
                V
        ----------------
          REGISTRO RMI
        ----------------
                |
                V

             SERVIDOR
                |
       -------------------
        Servicio Remoto
       -------------------
                |
         Procesamiento
                |
          Resultado
                |
                V

             CLIENTE
Evidencias de Ejecución captura de pantalla
<img width="1277" height="140" alt="servidor " src="https://github.com/user-attachments/assets/b1c8c302-9f8c-40cb-be4f-7578d55ca8cd" />
<img width="1001" height="677" alt="clientes" src="https://github.com/user-attachments/assets/6aee019f-a363-4f36-bf90-209c609797c1" />
<img width="661" height="562" alt="Captura de pantalla 2026-10-13 225918" src="https://github.com/user-attachments/assets/a0380bb3-a980-481f-b967-f90ddb8ac837" />

Cuestionario Teórico
1. ¿Cuál es la diferencia entre Sockets y Java RMI?
Los sockets permiten la comunicación entre aplicaciones mediante la red, pero el programador debe gestionar manualmente la transmisión y recepción de los datos.

Java RMI simplifica este proceso permitiendo invocar métodos remotos como si fueran métodos locales.

RMI se encarga automáticamente de:

Comunicación de red.
Serialización de objetos.
Envío de parámetros.
Recepción de resultados.
Por este motivo el desarrollo resulta más sencillo y organizado.

2. ¿Qué es el Stub y el Skeleton?
Stub
Es el componente ubicado del lado cliente.

Su función es:

Recibir la petición del cliente.
Convertir los parámetros para enviarlos.
Enviar la solicitud al servidor.
Recibir la respuesta.
Skeleton
Es el componente del lado servidor.

Su función es:

Recibir la solicitud.
Interpretar los datos recibidos.
Ejecutar el método correspondiente.
Enviar el resultado al cliente.
Marshalling
Proceso de convertir objetos en datos transmisibles por la red.

Unmarshalling
Proceso de reconstruir esos datos nuevamente en objetos utilizables.

3. ¿Qué sucede si el servidor deja de funcionar durante una ejecución?
Si el servidor deja de responder o pierde la conexión, el cliente genera una excepción.

La más frecuente es:

RemoteException
Por esta razón se recomienda utilizar bloques:

try-catch
para evitar la interrupción inesperada de la aplicación.

Ejemplo:

try {

    servicio.validarDNI("33265277");

} catch(RemoteException e) {

    System.out.println(
        "No fue posible conectar con el servidor"
    );
}
De esta manera se mejora la estabilidad del sistema.

Ventajas de Java RMI
Reduce la complejidad del código de comunicación.
Permite reutilizar objetos remotos.
Facilita el desarrollo de aplicaciones distribuidas.
Maneja automáticamente el intercambio de información.
Mejora la organización del proyecto.
