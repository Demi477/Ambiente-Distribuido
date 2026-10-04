import java.time.Instant;

/**
 * Mensaje inmutable que contiene la lectura métrica enviada por un ActorSensor.
 */
public final class MensajeLectura {
    private final String idSensor;
    private final double valor;
    private final long timestamp;

    public MensajeLectura(String idSensor, double valor) {
        this.idSensor = idSensor;
        this.valor = valor;
        this.timestamp = Instant.now().toEpochMilli();
    }

    public String getIdSensor() {
        return idSensor;
    }

    public double getValor() {
        return valor;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format(
                "MensajeLectura[Sensor=%s, Valor=%.2f, TS=%d]",
                idSensor, valor, timestamp);
    }
}