import java.io.*;
import java.net.*;
import java.util.Random;

public class ServidorSimulado {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        Random random = new Random();
        System.out.println("Servidor escuchando en puerto 5000...");

        while (true) {
            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                String mensaje = in.readLine();
                System.out.println("Cliente dice: " + mensaje);

                // Simular fallo aleatorio
                if (random.nextInt(10) < 3) { // 30% probabilidad de fallo
                    System.out.println("Simulando fallo...");
                    clientSocket.close();
                } else {
                    out.println("Respuesta OK desde servidor");
                }
            }
        }
    }
}
