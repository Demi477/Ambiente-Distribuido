package ejercicio1;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorChat {
    private static final int PUERTO = 5000;
    private static Set<PrintWriter> clientes = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) {
        System.out.println("Servidor de chat iniciado en puerto " + PUERTO);
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            while (true) {
                Socket cliente = serverSocket.accept();
                System.out.println("Nuevo cliente conectado: " + cliente.getInetAddress());
                new Thread(new ManejadorCliente(cliente)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ManejadorCliente implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ManejadorCliente(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                clientes.add(out);

                String mensaje;
                while ((mensaje = in.readLine()) != null) {
                    System.out.println("Mensaje recibido: " + mensaje);
                    for (PrintWriter cliente : clientes) {
                        cliente.println(mensaje);
                    }
                }
            } catch (IOException e) {
                System.out.println("Cliente desconectado: " + socket.getInetAddress());
            } finally {
                try {
                    if (out != null) clientes.remove(out);
                    if (in != null) in.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
