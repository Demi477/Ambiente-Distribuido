import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Actor base con un mailbox FIFO privado y un hilo de ejecución propio.
 */
public abstract class Actor<T> implements Runnable {
    protected final BlockingQueue<T> mailbox = new LinkedBlockingQueue<>();

    private final Thread thread;
    private volatile boolean running = true;

    public Actor(String nombre) {
        this.thread = new Thread(this, nombre);
    }

    /** Inicia el actor. */
    public void start() {
        thread.start();
    }

    /** Envía un mensaje de forma asíncrona al mailbox. */
    public void send(T mensaje) {
        mailbox.offer(mensaje);
    }

    /** Solicita detener el actor después de procesar los mensajes pendientes. */
    public void stop() {
        running = false;
        thread.interrupt();
    }

    @Override
    public void run() {
        while (running || !mailbox.isEmpty()) {
            try {
                T mensaje = mailbox.take();
                procesarMensaje(mensaje);
            } catch (InterruptedException e) {
                if (!running && mailbox.isEmpty()) {
                    break;
                }
            }
        }
    }

    /** Procesa un mensaje recibido por el actor. */
    protected abstract void procesarMensaje(T mensaje);
}
