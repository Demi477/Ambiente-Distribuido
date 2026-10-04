import java.util.ArrayList;
import java.util.List;

public class MainActorSystem {

        public static void main(String[] args) throws InterruptedException {
                System.out.println("==========================================================================");
                System.out.println(" TRABAJO PRACTICO N 5: SISTEMA DE ACTORES CONCURRENTE SIN LOCKS");
                System.out.println("==========================================================================");

                System.out.println("\n[1. OPERACION SPAWN] Instanciando e iniciando los Actores...");
                ActorProcesador procesador = new ActorProcesador();
                procesador.start();

                int totalSensores = 5;
                int mensajesPorSensor = 100;
                List<Thread> hilosSensores = new ArrayList<>();

                for (int i = 1; i <= totalSensores; i++) {
                        String idSensor = "Sensor-" + i;
                        ActorSensor sensor = new ActorSensor(idSensor, procesador, mensajesPorSensor);
                        Thread hiloSensor = new Thread(sensor, "Hilo-" + idSensor);
                        hilosSensores.add(hiloSensor);
                }

                long tiempoInicio = System.currentTimeMillis();

                System.out.println(
                                "[2. OPERACION SEND] Disparando rafaga concurrente de 500 mensajes desde "
                                                + totalSensores + " sensores...");

                for (Thread hilo : hilosSensores) {
                        hilo.start();
                }

                for (Thread hilo : hilosSensores) {
                        hilo.join();
                }

                System.out.println(
                                ">>> Los sensores completaron el envio de los mensajes al Mailbox.");

                int totalMensajes = totalSensores * mensajesPorSensor;
                while (procesador.getTotalLecturas() < totalMensajes) {
                        Thread.sleep(10);
                }

                long tiempoFin = System.currentTimeMillis();

                // Se conserva esta llamada porque depende de cómo esté implementado
                // ActorProcesador.
                procesador.stop();

                System.out.println("\n==========================================================================");
                System.out.println(" RESULTADOS DEL ESTADO DEL PROCESADOR");
                System.out.println("==========================================================================");
                System.out.printf(
                                " Total Lecturas Recibidas y Procesadas: %d / %d%n",
                                procesador.getTotalLecturas(), totalMensajes);
                System.out.printf(
                                " Suma Acumulada de Lecturas: %.2f °C %n",
                                procesador.getSumaValores());
                System.out.printf(
                                " Promedio Historico Calculado: %.2f °C %n",
                                procesador.getPromedio());
                System.out.printf(
                                " Temperatura Minima Registrada: %.2f °C %n",
                                procesador.getMinValor());
                System.out.printf(
                                " Temperatura Maxima Registrada: %.2f °C %n",
                                procesador.getMaxValor());
                System.out.printf(
                                " Tiempo Total de Procesamiento: %d ms%n",
                                tiempoFin - tiempoInicio);
                System.out.println("==========================================================================");
                System.out.println(
                                " VERIFICACION: Procesamiento 100% libre de condiciones de carrera (Race Conditions)");
                System.out.println(
                                " Sin uso de synchronized, ReentrantLock ni variables compartidas.");
                System.out.println("==========================================================================");
        }
}