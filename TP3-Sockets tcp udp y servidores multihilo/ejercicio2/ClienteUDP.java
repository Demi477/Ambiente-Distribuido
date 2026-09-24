package ejercicio2;

import java.net.*;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress direccion = InetAddress.getByName("localhost");

            Scanner sc = new Scanner(System.in);
            System.out.println("Cliente UDP conectado. Escribe mensajes:");

            while (true) {
                String mensaje = sc.nextLine();
                byte[] buffer = mensaje.getBytes();
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, direccion, 5000);
                socket.send(paquete);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
