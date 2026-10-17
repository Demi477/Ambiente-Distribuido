*/Trabajo Práctico N° 7*
**Materia:** Desarrollo de Aplicaciones para Ambientes Distribuidos
**Tema:**Integración entre Capas y Consumo de APIs
**Docente:** Lic. Gabriel Artaza 
**Alumno:** Gabriel Quiroga


*/Descripción*
Se desarrolló una aplicación distribuida utilizando una arquitectura Cliente-Servidor basada en una API REST implementada con FastAPI.

La aplicación permite consultar y agregar productos mediante solicitudes HTTP intercambiando información en formato JSON.

*/Tecnologías Utilizadas*
Python
FastAPI
Uvicorn
Requests
HTTP
JSON

*/Arquitectura*
Cliente Python ↓ HTTP / JSON ↓ API REST FastAPI ↓ Catálogo de Productos

*/Marshalling y Unmarshalling*
Marshalling: conversión de objetos Python a JSON para ser enviados por HTTP.

Unmarshalling: conversión del JSON recibido a objetos Python mediante respuesta.json().

*/Manejo de Errores*
Se implementó:

Error 404 cuando un producto no existe.
Error 500 para simular una falla interna del servidor.

*/Evidencias*
Se adjuntan capturas de pantalla donde se verifica:

Funcionamiento del endpoint GET.
Funcionamiento del endpoint POST.
Consulta de producto por ID.
Error HTTP 404.
Error HTTP 500.
Interfaz Swagger (/docs).
Cliente Python consumiendo la API.


*/Ejecución*
Instalar dependencias:

pip install fastapi uvicorn requests