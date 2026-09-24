import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        System.out.println("=== SERVIDOR TCP INICIADO EN PUERTO " + PUERTO + " ===");

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            while (true) {
                System.out.println("\nEsperando conexión del cliente...");
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Cliente conectado desde: " + socket.getInetAddress());

                    DataInputStream dis = new DataInputStream(socket.getInputStream());

                    // JSON
                    int cantidadJson = dis.readInt();
                    System.out.println("\n--- Recibiendo " + cantidadJson + " transacciones en JSON ---");
                    long inicioJson = System.nanoTime();
                    long bytesJson = 0;
                    for (int i = 0; i < cantidadJson; i++) {
                        String lineaJson = dis.readUTF();
                        bytesJson += lineaJson.getBytes("UTF-8").length + 2;
                        Transaccion t = ParserMensajes.jsonATransaccion(lineaJson);
                    }
                    long finJson = System.nanoTime();
                    double tiempoJsonMs = (finJson - inicioJson) / 1e6;
                    System.out.printf("JSON Recibido -> %d bytes (%.2f KB) | Tiempo: %.2f ms%n",
                            bytesJson, bytesJson / 1024.0, tiempoJsonMs);

                    // Binario
                    int cantidadBin = dis.readInt();
                    System.out.println("\n--- Recibiendo " + cantidadBin + " transacciones en BINARIO ---");
                    long inicioBin = System.nanoTime();
                    long bytesBin = 0;
                    for (int i = 0; i < cantidadBin; i++) {
                        Transaccion t = ParserMensajes.binarioDesdeStream(dis);
                        bytesBin += 4 + 2 + t.getOrigen().getBytes("UTF-8").length + 8 + 8;
                    }
                    long finBin = System.nanoTime();
                    double tiempoBinMs = (finBin - inicioBin) / 1e6;
                    System.out.printf("Binario Recibido -> %d bytes (%.2f KB) | Tiempo: %.2f ms%n",
                            bytesBin, bytesBin / 1024.0, tiempoBinMs);

                    System.out.println("\n=== RESUMEN SERVIDOR ===");
                    System.out.printf("JSON es %.2f%% más grande que Binario%n",
                            ((double)(bytesJson - bytesBin) / bytesBin) * 100);

                } catch (Exception e) {
                    System.err.println("Error procesando cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
