import java.util.Random;

/*ActorSensor: Dispositivo que genera y envía lecturas métricas asincrónicas al ActorProcesador.*/

public class ActorSensor implements Runnable {
    private final String idSensor;
    private final ActorProcesador procesador;
    private final int cantidadAEnviar;
    private final Random random = new Random();

    public ActorSensor(String idSensor, ActorProcesador procesador, int cantidadAEnviar) {
        this.idSensor = idSensor;
        this.procesador = procesador;
        this.cantidadAEnviar = cantidadAEnviar;
    }

    @Override
    public void run() {
        for (int i = 0; i < cantidadAEnviar; i++) {

            /* Genera temperatura simulada ente 15 y 35 grados Celsius */

            double lectura = 15.0 + (20.0 * random.nextDouble());
            MensajeLectura msg = new MensajeLectura(idSensor, lectura);

            /* Operacion 2 envia (send asincronico) */

            procesador.send(msg);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}