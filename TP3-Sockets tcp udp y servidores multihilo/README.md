# Trabajo Práctico N° 3: Sockets TCP/UDP con API `java.net` y Servidores Multihilo

**Asignatura:** Desarrollo de Aplicaciones para Ambientes Distribuidos  
**Unidad:** Unidad 2 - Comunicación entre Procesos y Concurrencia  
**Docente:** Lic. Gabriel Artaza  
**Lenguaje:** Java (JDK 8+)

## 📋 Descripción del Proyecto

Este proyecto aborda el desarrollo de aplicaciones distribuidas mediante programación de sockets en Java utilizando la API `java.net`. El repositorio implementa dos soluciones principales:

1. **Servidor de Chat Multihilo (TCP):** Un sistema de comunicación concurrente orientado a conexión con capacidad de difusión de mensajes (*broadcast*) en tiempo real y gestión de desconexiones.
2. **Receptor de Alertas y Telemetría (UDP):** Un sistema de envío de datagramas con mecanismo de resiliencia basado en límites de tiempo de espera mediante `setSoTimeout()`.

## 💡 Consignas

### 1\. Capa de Transporte: Diferencias entre TCP y UDP

* **TCP (Transmission Control Protocol):** Es un protocolo orientado a conexión y confiable. Antes de transmitir datos, establece un enlace mediante un apretón de manos (*handshake* de tres vías). Garantiza la entrega ordenada de paquetes, retransmite datos perdidos y gestiona el control de flujo y congestión.
* **UDP (User Datagram Protocol):** Es un protocolo sin conexión y no confiable (*best-effort*). Envía datagramas de forma independiente sin verificar el estado del receptor ni confirmar la recepción. Ofrece menor latencia y menor sobrecarga de encabezado.

| Característica          | TCP                                                | UDP                                            |
| ----------------------- | -------------------------------------------------- | ---------------------------------------------- |
| **Conexión**            | Orientado a conexión (*handshake*)                 | Sin conexión                                   |
| **Confiabilidad**       | Alta (acuse de recibo / ACK, retransmisión)        | No garantizada (posible pérdida)               |
| **Orden de entrega**    | Garantizado                                        | No garantizado                                 |
| **Casos de uso reales** | Transferencias bancarias, correo electrónico, chat | Streaming en vivo, juegos en línea, telemetría |

---

### 2\. API `java.net`: Función de `ServerSocket.accept()` y Necesidad de Hilos Dedicados

* **`ServerSocket.accept()`:** Es un método bloqueante del lado del servidor que escucha y aguarda peticiones de conexión entrantes de clientes. Al recibir una solicitud, completa el *handshake* TCP y devuelve un objeto `Socket` independiente para la comunicación bidireccional.
* **Justificación de Hilos Dedicados:** En un servidor monohilo, la atención de E/S de un cliente bloquea el hilo ejecutor, impidiendo aceptar nuevas conexiones o procesar a otros clientes. Delegar la atención de cada socket a un hilo dedicado (`Thread` / `Runnable`) libera al hilo principal para regresar inmediatamente a `serverSocket.accept()`, logrando concurrencia real y eliminando cuellos de botella.

---

### 3\. Manejo de Errores y Resiliencia en UDP con `setSoTimeout()`

* **Pérdida de Paquetes en UDP:** Debido a que UDP no implementa mecanismos de acuse de recibo ni retransmisión automática a nivel de transporte, si un datagrama se pierde en la red, ni el emisor ni el receptor se enteran de forma implícita.
* **Detección con `setSoTimeout()`:** Al invocar `socket.setSoTimeout(5000)`, el socket UDP establece un límite máximo de espera para la operación bloqueante `socket.receive()`. Si transcurren 5 segundos sin recibir datos, el sistema lanza una excepción `java.net.SocketTimeoutException`. Esto permite capturar el evento, registrar una advertencia de desconexión o pérdida de señal, y reanudar el bucle de escucha sin detener la ejecución del servidor.

## 📸 Capturas Demostrativas

&gt; **Nota:** Adjuntar aquí las capturas de pantalla de las ejecuciones según los criterios del trabajo práctico.

1. **Servidor TCP con al menos 3 clientes conectados en simultáneo y broadcast activo:**\--Image of: --Demostración Chat TCP (se adjunta con 2 clientes)



2. **Receptor UDP capturando datagramas y registrando el timeout (SocketTimeoutException):**\--Image of: --Demostración UDP Timeout