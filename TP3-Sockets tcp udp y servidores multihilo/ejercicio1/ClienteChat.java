package ejercicio1;

import java.io.*;
import java.net.*;

public class ClienteChat {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 5000;

        try (Socket socket = new Socket(host, puerto);
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Conectado al chat. Escribe mensajes:");

            new Thread(() -> {
                try {
                    String respuesta;
                    while ((respuesta = in.readLine()) != null) {
                        System.out.println(">> " + respuesta);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            String mensaje;
            while ((mensaje = teclado.readLine()) != null) {
                out.println(mensaje);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
