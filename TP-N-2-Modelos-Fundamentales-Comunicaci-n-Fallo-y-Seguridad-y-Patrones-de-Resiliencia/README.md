# TP N°2 - modelos Fundamentales  (comunicacion, fallos y seguridad) y patrones de resiliencia 

## Ejercicio 1: Jitter en el Cliente
Se modificó la clase **ClienteResiliente.java** para que el tiempo de espera entre reintentos no sea siempre fijo.  
La nueva fórmula es:

TiempoEsperado = (Base × 2^(intento-1)) + Random(0, 500) ms


Esto significa que cada vez que el cliente vuelve a intentar, el tiempo de espera tiene un componente aleatorio (jitter).  
Así se evita que todos los clientes reintenten exactamente al mismo tiempo.


## Ejercicio 2: Métricas de Resiliencia
El cliente ahora muestra al final de la ejecución:
- Estado final de la petición: **Éxito** o **Fallo definitivo**.  
- Cantidad de intentos realizados.  
- Tiempo total acumulado de espera y comunicación.  

Estas métricas permiten evaluar si el cliente fue capaz de recuperarse de los errores simulados por el servidor.



## Ejercicio 3: Análisis Teórico

### 1. Problema del *Thundering Herd* (Efecto Estampida)
Cuando muchos clientes reintentan sus peticiones al mismo tiempo y con intervalos fijos, el servidor recibe una gran cantidad de solicitudes simultáneas.  
Esto puede **saturar aún más al servidor**, generando una especie de “estampida” que empeora el problema en lugar de solucionarlo.  
El jitter ayuda a que los reintentos se distribuyan en el tiempo y no todos caigan juntos.

### 2. Fallo transitorio vs. permanente
- **Fallo transitorio:** es un error temporal que puede solucionarse solo con un reintento.  
  *Ejemplo:* un servidor que rechaza una conexión porque está momentáneamente ocupado.  
- **Fallo permanente:** es un error que no se va a resolver aunque se intente varias veces.  
  *Ejemplo:* un servicio que ya no existe porque la dirección IP o el dominio fueron eliminados.


 

## Evidencia de ejecución
Se incluyen capturas donde se observa:
- El cliente realizando reintentos con jitter.  
- El cliente mostrando las métricas finales después de comunicarse con el servidor simulado.

 <img width="1467" height="372" alt="Servidor_simulado" src="https://github.com/user-attachments/assets/be82da39-ab36-494a-b053-b176d9d63c38" />
<img width="1457" height="365" alt="metrica_de_resiliencia" src="https://github.com/user-attachments/assets/443ee206-69ec-4289-9e1d-e9a8bc569e55" />

<img width="1451" height="235" alt="intentos_simulados5" src="https://github.com/user-attachments/assets/1e62321c-1a86-41dd-8560-bf86fa017cbb" />
<img width="892" height="627" alt="5_pruebas" src="https://github.com/user-attachments/assets/1240b6df-83c9-48a1-9554-99fc55dc09ac" />




 

