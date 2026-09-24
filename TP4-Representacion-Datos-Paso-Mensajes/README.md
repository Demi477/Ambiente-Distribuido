📘 Trabajo Práctico Nº 4
Representación de Datos y Paso de Mensajes

**Asignatura:** Desarrollo de Aplicaciones para Ambientes Distribuidos  
**Docente:** Lic. Gabriel Artaza  

🎯 Objetivo
Comprender e implementar el empaquetado, serialización/deserialización (marshalling/unmarshalling) y transmisión de estructuras de datos complejas entre procesos a través de un canal de red Socket TCP.

📝 Consignas y Respuestas
1. Definición de la Estructura de Datos
Se creó la clase Transaccion con los campos solicitados:

idTransaccion → entero de 32 bits

origen → cadena de texto

monto → número flotante de doble precisión (double)

timestamp → entero de 64 bits (long)


2. Implementación de los Formatos de Intercambio
Se desarrolló el módulo ParserMensajes con dos métodos de serialización:

Formato Texto (JSON):  
Ejemplo generado:

json
{"idTransaccion":101,"origen":"NodoA","monto":1500.50,"timestamp":1700000000}
Formato Binario Directo:  
Empaquetado con DataOutputStream y leído con DataInputStream.

1. Prueba de Transmisión y Evaluación de Desempeño
Se implementó:

Servidor TCP → escucha y deserializa objetos recibidos.

Cliente TCP → envía ráfaga de 1000 transacciones en JSON y luego en Binario.

Se midieron:

Tamaño total en bytes

Tiempo de serialización y envío


📊 Resultados Medidos
Formato	Bytes Totales	Tiempo Cliente (ms)	Tiempo Servidor (ms)
JSON	81,768 (~79,85 KB)	~94,43	~94,43
Binario	27,000 (~26,37 KB)	~19,35	~19,35


🔸 JSON es más legible y portable, pero ocupa 202,84% más espacio que Binario y tarda más en procesarse.

🔹 Binario es más eficiente y rápido, ideal para transmisión de grandes volúmenes de datos.

📷 Capturas de Pantalla
<img width="1116" height="442" alt="Captura de pantalla 2026-09-24 190039" src="https://github.com/user-attachments/assets/9c2c9588-8329-4bd6-b600-f3762c92fd3c" />

<img width="1106" height="401" alt="Captura de pantalla 2026-09-24 190251" src="https://github.com/user-attachments/assets/2a3bafe0-c176-48ca-b653-249ab2834e1e" />

