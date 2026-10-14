package servidor;

import interfaces.ServicioRemoto;
import modelo.Estadisticas;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServicioRemotoImpl extends UnicastRemoteObject
        implements ServicioRemoto {

    public ServicioRemotoImpl() throws RemoteException {
        super();
    }

    @Override
    public Estadisticas calcularEstadisticas(double[] numeros)
            throws RemoteException {

        double suma = 0;
        double max = numeros[0];
        double min = numeros[0];

        for (double n : numeros) {

            suma += n;

            if (n > max)
                max = n;

            if (n < min)
                min = n;
        }

        double promedio = suma / numeros.length;

        double sumaCuadrados = 0;

        for (double n : numeros) {
            sumaCuadrados += Math.pow(n - promedio, 2);
        }

        double desviacion = Math.sqrt(sumaCuadrados / numeros.length);

        return new Estadisticas(
                promedio,
                max,
                min,
                desviacion);
    }

    @Override
    public boolean validarDNI(String dni)
            throws RemoteException {

        return dni.matches("\\d{7,8}");
    }

    @Override
    public List<String> filtrarTexto(
            String[] textos,
            String patron)
            throws RemoteException {

        List<String> resultado = new ArrayList<>();

        for (String texto : textos) {

            if (texto.toLowerCase()
                    .contains(patron.toLowerCase())) {

                resultado.add(texto);
            }
        }

        return resultado;
    }
}