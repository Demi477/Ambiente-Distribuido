const express = require("express");
const cors = require("cors");
const { Pool } = require("pg");

const app = express();

app.use(express.json());
app.use(cors());

// PostgreSQL Primario
const primary = new Pool({
  host: "localhost",
  port: 5432,
  user: "postgres",
  password: "postgres",
  database: "tp8",
});

// PostgreSQL Réplica
const replica = new Pool({
  host: "localhost",
  port: 5433,
  user: "postgres",
  password: "postgres",
  database: "tp8",
});

// Escrituras -> Primario
app.post("/productos", async (req, res) => {
  try {
    const { nombre, precio } = req.body;

    await primary.query(
      "INSERT INTO productos(nombre, precio) VALUES($1, $2)",
      [nombre, precio]
    );

    res.json({
      mensaje: "Producto guardado correctamente",
    });
  } catch (error) {
    console.error(error);

    res.status(500).json({
      error: error.message,
    });
  }
});

// Lecturas -> Réplica
app.get("/productos", async (req, res) => {
  try {
    const resultado = await replica.query(
      "SELECT * FROM productos"
    );

    res.json(resultado.rows);
  } catch (error) {
    console.error(error);

    res.status(500).json({
      error: error.message,
    });
  }
});

app.listen(3000, () => {
  console.log("Servidor iniciado en puerto 3000");
});