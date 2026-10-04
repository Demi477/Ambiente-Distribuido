```
# Trabajo Práctico N° 5: Concurrencia y Sincronización con el Modelo de Actores

**Materia:** Desarrollo de Aplicaciones para Ambientes Distribuidos
**Docente:** Lic. Gabriel Artaza  

---

## 📌 1. Objetivos del Trabajo Práctico
Comprender e implementar los principios fundamentales del **Modelo de Actores** para resolver problemas de procesamiento concurrente y sincronización de datos mediante el paso de mensajes asincrónicos, eliminando por completo el uso de memoria compartida y primitivas de bloqueo tradicional.

---

## 🏗️ 2. Arquitectura del Sistema de Actores

El desarrollo se compone de las siguientes entidades aisladas:

1. **`MensajeLectura`:** Objeto inmutable que empaqueta los datos de la métrica (identificador del sensor, valor de temperatura y timestamp). Al ser inmutable, garantiza que los datos no sufran modificaciones mientras viajan en la red o en las colas de mensajes.
2. **`ActorSensor`:** Dispositivo emisor concurrente que genera lecturas de temperatura simuladas y las envía de forma asincrónica hacia el receptor.
3. **`ActorProcesador`:** Nodo receptor central que posee un buzón privado (*Mailbox*) FIFO (`LinkedBlockingQueue`) y un hilo de ejecución dedicado. Procesa los mensajes uno a uno y mantiene el acumulador y promedio histórico de manera privada.

---

## 🔒 3. Restricciones de Concurrencia y Aislamiento

En cumplimiento estricto con las consignas:
* **Cero Bloqueos:** Se prohíbe el uso de bloques `synchronized`, primitivas `ReentrantLock` o variables compartidas entre hilos.
* **Aislamiento de Estado:** Las variables internas del `ActorProcesador` (`totalLecturas`, `sumaValores`, `promedio`) son privadas y no expuestas ni modificables desde el exterior.
* **Comunicación Asincrónica:** Los hilos emisores interactúan depositando mensajes en la cola de entrada del receptor sin bloquear su propia ejecución.

---

## 🔄 4. Las 3 Operaciones Elementales del Modelo de Actores

En el flujo de ejecución se demuestran las operaciones esenciales enunciadas por Carl Hewitt:

1. **Crear (*Spawn*):** Instanciación dinámica del `ActorProcesador` y de los 5 hilos emisores `ActorSensor`.
2. **Enviar (*Send*):** Disparo concurrente de 500 mensajes inmutables (100 por cada sensor) hacia el *Mailbox* del procesador.
3. **Designar (*Designate / State Change*):** Extracción secuencial de cada mensaje desde la cola FIFO y actualización interna del contador y promedio acumulado para preparar la atención del siguiente mensaje.

---

## 📐 5. Diagrama de Flujo y Secuencia

```text
  [ ActorSensor 1..5 ]              [ Mailbox (FIFO) ]             [ ActorProcesador ]
           |                                |                              |
           | === (1) SPAWN ===============&gt; |                              |
           |     Inicia hilos sensores      |                              |
           |                                |                              |
           | === (2) SEND =================&gt;|                              |
           |     Envia 500 msgs inmutables  |                              |
           |                                | === (3) DESIGNATE =========&gt; |
           |                                |     Extrae mensaje (1 a 1)   | --- Actualiza estado
           |                                |                              |     (suma, total, avg)

```

---

## 📊 6\. Resultados de la Ejecución Real

Prueba funcional ejecutada enviando una ráfaga de 500 mensajes desde 5 sensores concurrentes:

```
captura de pantalla

ejecucion_tp5.png
