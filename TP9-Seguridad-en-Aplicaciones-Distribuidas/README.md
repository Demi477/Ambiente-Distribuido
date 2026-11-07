📘 TP N°9 - Seguridad en Aplicaciones Distribuidas Autenticación y Autorización con JWT

**Docente:** Lic. Gabriel Artaza  
**Alumno**: Gabriel  Quiroga

**Materia**: Ambientes Distribuidos

*1. Introducción*

En el presente trabajo práctico se implementó un sistema de autenticación y autorización utilizando JSON Web Tokens (JWT) sobre una API REST desarrollada con Node.js y Express.

El objetivo fue aplicar mecanismos de seguridad que permitan:

Autenticar usuarios.
Generar tokens JWT.
Validar tokens en rutas protegidas.
Restringir acceso según roles.
Gestionar errores de autorización y autenticación.

*2. Tecnologías Utilizadas*

Node.js
Express
JSON Web Token (JWT)
Dotenv
Postman

Dependencias instaladas:

Shell
npm install express jsonwebtoken dotenv selfsigned

*3. Estructura del Proyecto*

Plain Text
TP9-Seguridad-en-Aplicaciones-Distribuidas
│

├── node_modules

├── .env

├── package.json

├── package-lock.json

└── server.js

*4. Configuración del Entorno*

Se creó el archivo:

Plain Text
env no es totalmente compatible. El resaltado de sintaxis se basa en Plain Text. **JWT_SECRET=mi_clave_super_secreta_2026**

La variable se utiliza para firmar y validar los tokens JWT.

*5. Implementación*

Usuarios de prueba

Para simplificar el desarrollo se utilizaron usuarios almacenados en memoria.

JavaScript
const usuarios = [
{
id: 1,
username: "admin",
password: "1234",
rol: "ADMIN"
},
{
id: 2,
username: "cliente",
password: "1234",
rol: "CLIENTE"
}
];
Login

*Se implementó la ruta:*

HTTP
POST /login

La misma recibe usuario y contraseña.

Si las credenciales son válidas se genera un JWT con:

ID del usuario.
Rol.
Tiempo de expiración.

Ejemplo de payload:

JSON
{
"sub": 1,
"rol": "ADMIN"
}
Middleware de Autenticación

*Se creó un middleware denominado:*

JavaScript
verificarToken()

*Funciones:*

Verificar la existencia del token.
Validar la firma digital.
Detectar tokens expirados.
Extraer información del usuario autenticado.
Middleware de Autorización

*Se creó el middleware:*

JavaScript
soloAdmin()

Su objetivo es permitir únicamente el acceso a usuarios cuyo rol sea:

Plain Text
**ADMIN**

Si el usuario posee otro rol se responde con:

HTTP
403 Forbidden
6. Rutas Implementadas
Login
HTTP
POST /login

Genera un token JWT.

Perfil
HTTP
GET /perfil

Ruta protegida por JWT.

Permite visualizar la información del usuario autenticado.

Administración
HTTP
GET /admin

Ruta protegida.

Sólo accesible para usuarios con rol:

Plain Text
ADMIN

**CAPTURAS DE PANTALLA: PRUEBA REALIZADA**

<img width="1482" height="121" alt="en consola" src="https://github.com/user-attachments/assets/8c3b5e2b-1968-47bf-b3f1-d5e79600ecbe" />

<img width="1847" height="807" alt="login" src="https://github.com/user-attachments/assets/17084a61-3125-47a7-8c38-af8fd9542250" />

<img width="1841" height="826" alt="perfil" src="https://github.com/user-attachments/assets/c877fd07-3639-4e5f-b5e7-f966647a3a55" />

<img width="1842" height="817" alt="admin" src="https://github.com/user-attachments/assets/661715a9-4a4b-4b95-a70a-789ffaf73c5a" />

<img width="1821" height="937" alt="TOKEN" src="https://github.com/user-attachments/assets/e48b6878-be30-4d7f-8826-211db3508a30" />

<img width="1841" height="842" alt="msj correcto" src="https://github.com/user-attachments/assets/3a456623-29bf-43ff-87e2-8c9d2f4c1b0b" />









