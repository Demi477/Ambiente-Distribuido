import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 5500;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en puerto " + puerto);

            while (true) {
                Socket cliente = servidor.accept();
                BufferedReader entrada = new BufferedReader(
                        new InputStreamReader(cliente.getInputStream()));
                PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

                // Recibe la cadena, ej: "15;+;30"
                String mensaje = entrada.readLine();
                System.out.println("Recibido: " + mensaje);

                String[] partes = mensaje.split(";");
                int num1 = Integer.parseInt(partes[0]);
                String op = partes[1];
                int num2 = Integer.parseInt(partes[2]);

                String resultado;
                switch (op) {
                    case "+":
                        resultado = String.valueOf(num1 + num2);
                        break;
                    case "-":
                        resultado = String.valueOf(num1 - num2);
                        break;
                    case "*":
                        resultado = String.valueOf(num1 * num2);
                        break;
                    case "/":
                        if (num2 == 0) {
                            resultado = "ERROR: Division por cero";
                        } else {
                            resultado = String.valueOf(num1 / num2);
                        }
                        break;
                    default:
                        resultado = "ERROR: Operacion invalida";
                }

                // Envía el resultado al cliente
                salida.println(resultado);

                cliente.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
