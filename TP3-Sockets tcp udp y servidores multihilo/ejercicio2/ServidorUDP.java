package ejercicio2;

import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(5000);
            socket.setSoTimeout(5000); // 5 segundos de espera

            byte[] buffer = new byte[1024];
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);

            System.out.println("Servidor UDP esperando mensajes en puerto 5000...");

            while (true) {
                try {
                    socket.receive(paquete);
                    String mensaje = new String(paquete.getData(), 0, paquete.getLength());
                    System.out.println("Mensaje recibido: " + mensaje);
                } catch (SocketTimeoutException e) {
                    System.out.println("Timeout: no se recibió ningún mensaje en 5 segundos.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
