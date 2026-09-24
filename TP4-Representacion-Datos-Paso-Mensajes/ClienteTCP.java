import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClienteTCP {
    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;
    private static final int CANTIDAD_TRANSACCIONES = 1000;

    public static void main(String[] args) {
        System.out.println("=== CLIENTE TCP INICIANDO RÁFAGA DE " + CANTIDAD_TRANSACCIONES + " TRANSACCIONES ===");

        List<Transaccion> transacciones = new ArrayList<>();
        for (int i = 1; i <= CANTIDAD_TRANSACCIONES; i++) {
            transacciones.add(new Transaccion(i, "Nodo" + (i % 10), Math.random() * 10000, System.currentTimeMillis()));
        }

        try (Socket socket = new Socket(HOST, PUERTO)) {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // JSON
            dos.writeInt(CANTIDAD_TRANSACCIONES);
            long inicioJson = System.nanoTime();
            long bytesJson = 0;
            for (Transaccion t : transacciones) {
                String json = ParserMensajes.aJSON(t);
                dos.writeUTF(json);
                bytesJson += json.getBytes("UTF-8").length + 2;
            }
            dos.flush();
            long finJson = System.nanoTime();
            double tiempoJsonMs = (finJson - inicioJson) / 1e6;
            System.out.printf("JSON Enviado -> %d bytes (%.2f KB) | Tiempo: %.2f ms%n",
                    bytesJson, bytesJson / 1024.0, tiempoJsonMs);

            // Binario
            dos.writeInt(CANTIDAD_TRANSACCIONES);
            long inicioBin = System.nanoTime();
            long bytesBin = 0;
            for (Transaccion t : transacciones) {
                byte[] bin = ParserMensajes.aBinario(t);
                dos.write(bin);
                bytesBin += bin.length;
            }
            dos.flush();
            long finBin = System.nanoTime();
            double tiempoBinMs = (finBin - inicioBin) / 1e6;
            System.out.printf("Binario Enviado -> %d bytes (%.2f KB) | Tiempo: %.2f ms%n",
                    bytesBin, bytesBin / 1024.0, tiempoBinMs);

            System.out.println("\n=== RESUMEN CLIENTE ===");
            System.out.printf("JSON es %.2f%% más grande que Binario%n",
                    ((double)(bytesJson - bytesBin) / bytesBin) * 100);

        } catch (IOException e) {
            System.err.println("Error en cliente: " + e.getMessage());
        }
    }
}
