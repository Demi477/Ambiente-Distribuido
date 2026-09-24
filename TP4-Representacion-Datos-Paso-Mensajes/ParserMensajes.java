import java.io.*;

public class ParserMensajes {
    // JSON simple y robusto
    public static String aJSON(Transaccion t) {
        return String.format("{\"idTransaccion\":%d,\"origen\":\"%s\",\"monto\":%.2f,\"timestamp\":%d}",
                t.getIdTransaccion(), t.getOrigen(), t.getMonto(), t.getTimestamp());
    }

    public static Transaccion jsonATransaccion(String json) {
        try {
            // Eliminar llaves y comillas
            json = json.trim().replace("{","").replace("}","");
            String[] campos = json.split(",");

            int id = 0;
            String origen = "";
            double monto = 0;
            long ts = 0;

            for (String campo : campos) {
                String[] kv = campo.split(":");
                if (kv.length < 2) continue;
                String clave = kv[0].replace("\"","").trim();
                String valor = kv[1].replace("\"","").trim();

                switch (clave) {
                    case "idTransaccion":
                        id = Integer.parseInt(valor);
                        break;
                    case "origen":
                        origen = valor;
                        break;
                    case "monto":
                        monto = Double.parseDouble(valor);
                        break;
                    case "timestamp":
                        ts = Long.parseLong(valor);
                        break;
                }
            }
            return new Transaccion(id, origen, monto, ts);
        } catch (Exception e) {
            // Si algo falla, devolvemos un objeto vacío para no romper el servidor
            return new Transaccion(0, "ERROR", 0.0, 0L);
        }
    }

    // Binario
    public static byte[] aBinario(Transaccion t) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeInt(t.getIdTransaccion());
        dos.writeUTF(t.getOrigen());
        dos.writeDouble(t.getMonto());
        dos.writeLong(t.getTimestamp());
        dos.flush();
        return bos.toByteArray();
    }

    public static Transaccion binarioDesdeStream(DataInputStream dis) throws IOException {
        int id = dis.readInt();
        String origen = dis.readUTF();
        double monto = dis.readDouble();
        long ts = dis.readLong();
        return new Transaccion(id, origen, monto, ts);
    }
}
