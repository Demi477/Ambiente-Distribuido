**Trabajo Práctico N° 7**

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

<img width="956" height="632" alt="servidor" src="https://github.com/user-attachments/assets/10d57b2e-a87d-4276-86e6-c1796956b80c" />
<img width="1407" height="766" alt="cliente" src="https://github.com/user-attachments/assets/6d2e22e1-5676-43a9-b561-0f52ae05d374" />

<img width="697" height="146" alt="cap1" src="https://github.com/user-attachments/assets/31750ea4-edab-4582-a106-a5d1cc777262" />

<img width="567" height="137" alt="cp2" src="https://github.com/user-attachments/assets/2ce6e48d-6c67-4b5a-b98c-6657c42fdfc9" />

<img width="445" height="137" alt="cp3" src="https://github.com/user-attachments/assets/da96686c-ea75-4fb2-a0f1-2881d48c3a56" />

<img width="427" height="116" alt="cp4" src="https://github.com/user-attachments/assets/4624a388-e083-4377-b524-9e8c9847faf7" />

<img width="1331" height="897" alt="cp5" src="https://github.com/user-attachments/assets/1d39d20c-d81c-4871-bc26-ceed5f27d085" />


*/Ejecución*
Instalar dependencias:

pip install fastapi uvicorn requests
