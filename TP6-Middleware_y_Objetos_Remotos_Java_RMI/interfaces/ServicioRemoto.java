package interfaces;

import modelo.Estadisticas;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServicioRemoto extends Remote {

    Estadisticas calcularEstadisticas(double[] numeros)
            throws RemoteException;

    boolean validarDNI(String dni)
            throws RemoteException;

    List<String> filtrarTexto(String[] textos, String patron)
            throws RemoteException;
}
