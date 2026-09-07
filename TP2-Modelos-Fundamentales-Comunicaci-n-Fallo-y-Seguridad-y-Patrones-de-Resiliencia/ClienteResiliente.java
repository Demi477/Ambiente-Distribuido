import java.io.*;
import java.net.*;
import java.util.Random;

public class ClienteResiliente {
    private static final int MAX_INTENTOS = 5;
    private static final int BASE = 1000; // tiempo base en ms

    public static void main(String[] args) {
        int intentos = 0;
        long tiempoTotal = 0;
        boolean exito = false;
        Random random = new Random();

        long inicio = System.currentTimeMillis();

        while (!exito && intentos < MAX_INTENTOS) {
            intentos++;
            long intentoInicio = System.currentTimeMillis();

            try (Socket socket = new Socket("localhost", 5000);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                out.println("Hola servidor, intento " + intentos);
                String respuesta = in.readLine();
                System.out.println("Respuesta del servidor: " + respuesta);

                exito = true;

            } catch (IOException e) {
                System.out.println("Fallo en intento " + intentos + ": " + e.getMessage());

                int tiempoEsperado = (BASE * (int)Math.pow(2, intentos - 1))
                                   + random.nextInt(501); // jitter 0–500 ms
                System.out.println("Esperando " + tiempoEsperado + " ms antes de reintentar...");

                try {
                    Thread.sleep(tiempoEsperado);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }

            tiempoTotal += (System.currentTimeMillis() - intentoInicio);
        }

        long fin = System.currentTimeMillis();

        System.out.println("\n=== Métricas de Resiliencia ===");
        System.out.println("Estado final: " + (exito ? "Éxito" : "Fallo definitivo"));
        System.out.println("Intentos realizados: " + intentos);
        System.out.println("Tiempo total acumulado: " + (fin - inicio) + " ms");
    }
}
