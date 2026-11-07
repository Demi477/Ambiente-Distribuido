require("dotenv").config();

const express = require("express");
const https = require("https");
const jwt = require("jsonwebtoken");
const selfsigned = require("selfsigned");

const app = express();

app.use(express.json());

// Usuarios de prueba
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

// Login
app.post("/login", (req, res) => {

  console.log(req.body);

  const { username, password } = req.body;

  const usuario = usuarios.find(
    u =>
      u.username === username &&
      u.password === password
  );

  if (!usuario) {
    return res.status(401).json({
      mensaje: "Credenciales inválidas"
    });
  }

  const token = jwt.sign(
    {
      sub: usuario.id,
      rol: usuario.rol
    },
    process.env.JWT_SECRET,
    {
      expiresIn: "1h"
    }
  );

  res.json({ token });

});

// Middleware autenticación
function verificarToken(req, res, next) {

  const auth = req.headers.authorization;

  if (!auth || !auth.startsWith("Bearer ")) {
    return res.status(401).json({
      mensaje: "Token faltante"
    });
  }

  const token = auth.split(" ")[1];

  try {

    const payload = jwt.verify(
      token,
      process.env.JWT_SECRET
    );

    req.usuario = payload;

    next();

  } catch {
    return res.status(401).json({
      mensaje: "Token inválido o expirado"
    });
  }
}

// Ruta protegida
app.get(
  "/perfil",
  verificarToken,
  (req, res) => {

    res.json({
      usuario: req.usuario
    });

  }
);

// Middleware autorización admin
function soloAdmin(req, res, next) {

  if (req.usuario.rol !== "ADMIN") {
    return res.status(403).json({
      mensaje: "Acceso denegado"
    });
  }

  next();

}

// Ruta admin
app.get(
  "/admin",
  verificarToken,
  soloAdmin,
  (req, res) => {

    res.json({
      mensaje: "Bienvenido ADMIN"
    });

  }
);

// Certificado HTTPS autofirmado
const attrs = [
  {
    name: "commonName",
    value: "localhost"
  }
];

const pems = selfsigned.generate(
  attrs,
  {
    days: 365,
    keySize: 2048
  }
);

// Servidor HTTPS
app.listen(3000, () => {

  console.log(
    "Servidor ejecutándose en http://localhost:3000"
  );

});