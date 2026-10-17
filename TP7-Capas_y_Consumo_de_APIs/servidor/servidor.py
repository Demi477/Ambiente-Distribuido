from fastapi import FastAPI, HTTPException
from pydantic import BaseModel

app = FastAPI(
    title="API Catalogo de Productos",
    description="TP7 - APIs y Servicios Web",
    version="1.0"
)

class Producto(BaseModel):
    nombre: str
    precio: float

productos = [
    {
        "id": 1,
        "nombre": "Notebook Lenovo",
        "precio": 850000
    },
    {
        "id": 2,
        "nombre": "Mouse Logitech",
        "precio": 25000
    }
]

# ENDPOINT GET
@app.get("/productos")
def obtener_productos():
    return productos

# ENDPOINT GET POR ID
@app.get("/productos/{id_producto}")
def obtener_producto(id_producto: int):

    for producto in productos:
        if producto["id"] == id_producto:
            return producto

    raise HTTPException(
        status_code=404,
        detail="Producto no encontrado"
    )

# ENDPOINT POST
@app.post("/productos")
def crear_producto(producto: Producto):

    nuevo_producto = {
        "id": len(productos) + 1,
        "nombre": producto.nombre,
        "precio": producto.precio
    }

    productos.append(nuevo_producto)

    return {
        "mensaje": "Producto agregado correctamente",
        "producto": nuevo_producto
    }

# MANEJO DE ERROR DE PRUEBA
@app.get("/error")
def error():

    raise HTTPException(
        status_code=500,
        detail="Error interno simulado"
    )