## Trabajo Práctico N° 8: Bases de Datos Distribuidas y Conexión entre Capas.


**Docente:** Lic. Gabriel Artaza 

**Alumno:** Gabriel Quiroga

**Materia:** Ambientes Distribuidos


## Objetivo

Implementar una arquitectura de base de datos distribuida utilizando Docker y PostgreSQL, separando las operaciones de lectura y escritura entre diferentes nodos.

El objetivo principal es comprender cómo los sistemas distribuidos mejoran la disponibilidad, escalabilidad y tolerancia a fallos de una aplicación.

se utilizan distintos nodos:

Un nodo Primario para las escrituras.

Un nodo Réplica para las lecturas.

Esta estrategia permite:

Mejorar el rendimiento.

Reducir la carga del servidor principal.

Facilitar la escalabilidad.

Aumentar la disponibilidad del sistema.

Mantener el servicio operativo ante determinados fallos.

## Arquitectura Implementada

Plain Text
Backend Node.js
|
+-------------+-------------+
| |
Escrituras Lecturas
| |
v v
 
PostgreSQL PostgreSQL
Primario Réplica


## Tecnologías Utilizadas

Docker Desktop

Docker Compose

PostgreSQL 16

Node.js

Express.js

Yaak

PowerShell

## Implementación

Se crearon dos contenedores PostgreSQL utilizando Docker Compose:

postgres-primary

postgres-replica

Posteriormente se desarrolló una API REST en Node.js con dos endpoints:

Crear producto

HTTP
POST /productos

Permite registrar productos en la base de datos principal.

Consultar productos

HTTP
GET /productos

Permite consultar información desde la réplica.

## Evidencias

Las capturas adjuntas corresponden a:

Contenedores ejecutándose.
Creación de la tabla.
Escritura exitosa mediante la API.
Verificación de registros almacenados.
Error producido por la caída del nodo.
Recuperación del servicio.



<img width="1897" height="761" alt="01-docker-contenedores" src="https://github.com/user-attachments/assets/eb03c72c-a451-4d60-bc47-701030337606" />

<img width="797" height="557" alt="05-primario-con-datos" src="https://github.com/user-attachments/assets/d90e5942-8fe4-4437-8e8c-1ad8b16897b5" />


<img width="1875" height="965" alt="02-tabla-creada" src="https://github.com/user-attachments/assets/adde2dba-f064-4d84-9e6a-5e912c748ed1" />


<img width="1172" height="247" alt="03-post-exitoso" src="https://github.com/user-attachments/assets/44fcc884-febf-44c3-9a0f-0fb93d21034b" />


<img width="962" height="202" alt="06-caida-primario" src="https://github.com/user-attachments/assets/103a6189-1a86-4e31-a680-8260d3f3d133" />

<img width="1167" height="260" alt="07-error-con-primario-caido" src="https://github.com/user-attachments/assets/9cb920a5-4557-481b-8778-18d9a34083b5" />


## Conclusión

La práctica permitió comprender el funcionamiento básico de una arquitectura distribuida utilizando Docker y PostgreSQL.

La separación entre operaciones de lectura y escritura facilita la distribución de la carga de trabajo y constituye una estrategia ampliamente utilizada en sistemas empresariales de gran escala.

Además, la simulación de la caída del nodo principal permitió observar cómo afecta una falla a las operaciones de escritura y la importancia de diseñar mecanismos de recuperación y redundancia en entornos distribuidos.'

Docker fue utilizado como herramienta para desplegar y administrar los nodos de base de datos mediante contenedores independientes.

Es importante destacar que Docker no almacena ni muestra directamente las operaciones realizadas sobre la base de datos (INSERT, UPDATE o DELETE). Su función dentro de este trabajo fue proporcionar el entorno de ejecución para los servicios PostgreSQL y permitir la administración de los nodos.

