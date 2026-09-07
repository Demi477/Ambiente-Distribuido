import java.io.*;
import java.net.*;
import java.util.Scanner;

public class cliente {
    public static void main(String[] args) {
        String host = "localhost"; // IP del servidor (local)
        int puerto = 5500; // Puerto requerido en el TP [1]

        try (Socket socket = new Socket(host, puerto);
             BufferedReader entrada = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado exitosamente al servidor de la calculadora.");

            // Pedir datos al usuario
            System.out.print("Ingrese primer número: ");
            int num1 = scanner.nextInt();

            System.out.print("Ingrese operación (+, -, *, /): ");
            String op = scanner.next();

            System.out.print("Ingrese segundo número: ");
            int num2 = scanner.nextInt();

            // Armar cadena con el formato "num1;operación;num2" y enviarla [1]
            String mensaje = num1 + ";" + op + ";" + num2;
            salida.println(mensaje);

            // Recibir y mostrar el resultado [1]
            String respuesta = entrada.readLine();
            System.out.println("Resultado: " + respuesta);

        } catch (IOException e) {
            System.err.println("Error de conexión en el cliente: " + e.getMessage());
        }
    }
}