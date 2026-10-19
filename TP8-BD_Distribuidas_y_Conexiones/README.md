## TP 8 - Base de Datos Distribuida con Docker

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
Consulta de datos.
Verificación de registros almacenados.
Detención del nodo principal.
Error producido por la caída del nodo.
Recuperación del servicio.

## Conclusión

La práctica permitió comprender el funcionamiento básico de una arquitectura distribuida utilizando Docker y PostgreSQL.

La separación entre operaciones de lectura y escritura facilita la distribución de la carga de trabajo y constituye una estrategia ampliamente utilizada en sistemas empresariales de gran escala.

Además, la simulación de la caída del nodo principal permitió observar cómo afecta una falla a las operaciones de escritura y la importancia de diseñar mecanismos de recuperación y redundancia en entornos distribuidos.'

Docker fue utilizado como herramienta para desplegar y administrar los nodos de base de datos mediante contenedores independientes.

Es importante destacar que Docker no almacena ni muestra directamente las operaciones realizadas sobre la base de datos (INSERT, UPDATE o DELETE). Su función dentro de este trabajo fue proporcionar el entorno de ejecución para los servicios PostgreSQL y permitir la administración de los nodos.

