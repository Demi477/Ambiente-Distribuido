# tp1-cliente-servidor

**Institución:** Instituto de Educación Superior N° 6.001 "Gral. Manuel Belgrano"
**Carrera:** Tecnicatura Superior en Análisis de Sistemas y Desarrollo de Software
**Docente:** Lic. Gabriel Artaza
**Alumno:** Gabriel Gabriel


## Descripción del Proyecto
Este proyecto consiste en una **Calculadora Distribuida** basada en la arquitectura Cliente-Servidor utilizando la API de Sockets TCP en Java (`java.net`). El sistema permite realizar operaciones matemáticas básicas  de forma remota

## Requisitos del Entorno
*   Java Development Kit (JDK) versión 17 o superior.
*   Terminal de comandos (PowerShell, CMD).
*   Git para control de versiones.




## Estructura del Directorio
El proyecto se encuentra organizado de la siguiente manera:

tp1-cliente-servidor/
├── cliente/
│   ├── Cliente.java          # Código fuente del Cliente
│    
├── servidor/
│   ├── Servidor.java         # Código fuente del Servidor (Puerto 5500)
│         
└── README.md                 # Documentación del Trabajo Práctico

Client (image.png)
Server (image-.png)
<img width="877" height="397" alt="FUNCIONA" src="https://github.com/user-attachments/assets/f9dd1800-7f22-4137-87a9-dedf2a5317e7" />
<img width="826" height="542" alt="FUNCIONA1" src="https://github.com/user-attachments/assets/b8b8ea31-fa77-42a1-9214-a0740eb3bce1" />


1. ¿Qué sucede con el cliente si el servidor no está ejecutándose al momento de intentar conectar?
Cuando el cliente intenta conectarse y el servidor no está en ejecución, la conexión falla porque no hay ningún proceso escuchando en el puerto indicado. Java lanza la excepción:
"java.net.ConnectException: Connection refused: connect"
2. Identifique en su código qué línea bloquea la ejecución del programa hasta que ocurre un evento de red
En el Cliente:
java
"Socket socket = new Socket("localhost", 5500)"
Esta línea bloquea hasta que se establece la conexión o se produce un error.
En el Servidor:
java
"Socket clientSocket = serverSocket.accept()"
Aquí el programa queda detenido esperando que un cliente se conecte.
3. Proponga qué cambios serían necesarios si dos compañeros de clase quisieran ejecutar el Cliente en una notebook y el Servidor en otra conectadas al Wi Fi del aula
1.	Dirección IP del servidor
En el Cliente.java, cambiar "localhost" por la IP privada de la notebook que ejecuta el servidor (ejemplo: "192.168.0.15").
La IP se obtiene con ipconfig (Windows) o ifconfig (Linux/Mac).
2.	Red compartida
Ambas notebooks deben estar conectadas a la misma red Wi Fi.
3.	Firewall
En la notebook del servidor, habilitar el puerto TCP 5500 para conexiones entrantes.
4.	Orden de ejecución
Primero iniciar el Servidor.java en la notebook del servidor.
Luego ejecutar el Cliente.java en la otra notebook, apuntando a la IP del servidor.


