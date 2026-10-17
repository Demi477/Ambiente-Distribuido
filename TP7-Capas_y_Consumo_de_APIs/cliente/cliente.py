import requests

URL_BASE = "http://127.0.0.1:8000"

def obtener_productos():

    try:
        respuesta = requests.get(
            f"{URL_BASE}/productos"
        )

        if respuesta.status_code == 200:

            productos = respuesta.json()

            print("\nLISTA DE PRODUCTOS\n")

            for producto in productos:
                print(
                    f"ID: {producto['id']} | "
                    f"{producto['nombre']} | "
                    f"${producto['precio']}"
                )

        else:
            print("Error:", respuesta.status_code)

    except Exception as e:
        print("Excepción:", e)


def agregar_producto():

    nombre = input("Nombre: ")
    precio = float(input("Precio: "))

    datos = {
        "nombre": nombre,
        "precio": precio
    }

    try:

        respuesta = requests.post(
            f"{URL_BASE}/productos",
            json=datos
        )

        print(respuesta.json())

    except Exception as e:
        print("Error:", e)


def menu():

    while True:

        print("\n===== CLIENTE API =====")
        print("1. Listar productos")
        print("2. Agregar producto")
        print("3. Salir")

        opcion = input("Opción: ")

        if opcion == "1":
            obtener_productos()

        elif opcion == "2":
            agregar_producto()

        elif opcion == "3":
            break

        else:
            print("Opción no válida")


if __name__ == "__main__":
    menu()