public class ActorProcesador extends Actor<MensajeLectura> {

    private volatile long totalLecturas = 0;
    private volatile double sumaValores = 0.0;
    private volatile double minValor = Double.POSITIVE_INFINITY;
    private volatile double maxValor = Double.NEGATIVE_INFINITY;

    public ActorProcesador() {
        super("ActorProcesador");
    }

    @Override
    protected void procesarMensaje(MensajeLectura mensaje) {
        double valor = mensaje.getValor();

        sumaValores += valor;
        minValor = Math.min(minValor, valor);
        maxValor = Math.max(maxValor, valor);
        totalLecturas++;

        double promedioActual = sumaValores / totalLecturas;

        if (totalLecturas % 50 == 0 || totalLecturas == 500) {
            System.out.printf(
                    "[ACTOR PROCESADOR] Msg #%3d procesado | Origen: %-8s | "
                            + "Lectura: %6.2f °C | Promedio Acumulado: %6.2f °C%n",
                    totalLecturas,
                    mensaje.getIdSensor(),
                    valor,
                    promedioActual);
        }
    }

    public long getTotalLecturas() {
        return totalLecturas;
    }

    public double getSumaValores() {
        return sumaValores;
    }

    public double getPromedio() {
        return totalLecturas == 0 ? 0 : sumaValores / totalLecturas;
    }

    public double getMinValor() {
        return totalLecturas == 0 ? 0 : minValor;
    }

    public double getMaxValor() {
        return totalLecturas == 0 ? 0 : maxValor;
    }
}