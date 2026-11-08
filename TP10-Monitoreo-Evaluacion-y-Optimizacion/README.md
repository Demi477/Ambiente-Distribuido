TP10 - Monitoreo, Evaluación y Optimización

**Docente:** Lic. Gabriel Artaza  
**Alumno**: Gabriel  Quiroga

**Materia**: Ambientes Distribuidos

Objetivo

Realizar pruebas de carga y estrés sobre un servicio desarrollado previamente, monitoreando métricas de rendimiento y consumo de recursos para identificar posibles cuellos de botella.

Servicio evaluado
Se utilizó la aplicación desarrollada en el TP9, implementada con Node.js, Express y autenticación JWT.

Endpoints evaluados:

POST /login
GET /perfil
GET /admin

**Tecnologías utilizadas**

Node.js
Express
JWT
Locust 2.47.0
Windows 11
Instalación y ejecución

1. Clonar el repositorio
   

2. Ingresar al proyecto TP9
cd TP9-Seguridad-en-Aplicaciones-Distribuidas
1. Instalar dependencias
npm install
1. Configurar variables de entorno
Crear un archivo .env con el siguiente contenido:

JWT_SECRET=MiClaveSecreta
5. Iniciar el servidor
node server.js
El servicio quedará disponible en:

http://localhost:3000
Ejecución de las pruebas
Instalar Locust
pip install locust
Ejecutar Locust

Ingrese a la carpeta TP10:

cd TP10-Monitoreo-Evaluacion-y-Optimizacion
Ejecutar:

locust
Abrir:

http://localhost:8089
Configuración utilizada:

Host: http://localhost:3000

**Usuarios iniciales: 50**


Spawn Rate: 5 usuarios/segundo
Prueba de carga
50 usuarios concurrentes
Resultados:

Latencia promedio: 4.63 ms
Throughput: 24.9 req/s
Error Rate: 0 %
Peticiones procesadas: 865
Prueba de estrés


**100 usuarios concurrentes**


Resultados:

Latencia promedio: 3.2 ms
Throughput: 50.4 req/s
Error Rate: 0 %
Peticiones procesadas: 8.879


**200 usuarios concurrentes**


Resultados:

Latencia promedio: 3.49 ms
Throughput: 101.8 req/s
Error Rate: 0 %
Peticiones procesadas: 32.531


**Monitoreo de recursos**


Durante la ejecución se monitoreó el proceso Node.js desde el Administrador de tareas de Windows.

CPU
0.6 %

Memoria RAM
39.7 MB

Cuello de botella identificado
No se detectó un cuello de botella crítico durante las pruebas realizadas.

La aplicación mantuvo una tasa de errores del 0 % incluso con 200 usuarios concurrentes.

El tiempo de respuesta permaneció estable y el throughput aumentó proporcionalmente al incremento de usuarios, demostrando un comportamiento adecuado bajo carga.

**Conclusión**

La aplicación desarrollada en el TP9 presentó un comportamiento estable durante las pruebas de carga y estrés.

Los tiempos de respuesta se mantuvieron bajos, el consumo de recursos fue reducido y no se registraron errores durante la ejecución de las pruebas.

Esto demuestra que el servicio puede manejar correctamente la cantidad de usuarios concurrentes evaluada.

Evidencias - Las capturas de pantalla 


carga-50.png
stress-100.png
stress-200.png
cpu-ram.png


**CAPTURAS DE PANTALLA DE LA IMPLEMENTACION**


