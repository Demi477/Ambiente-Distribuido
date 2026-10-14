package servidor;

import interfaces.ServicioRemoto;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {

    public static void main(String[] args) {

        try {
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");

            ServicioRemoto servicio = new ServicioRemotoImpl();

            Registry registro = LocateRegistry.createRegistry(1099);

            registro.rebind(
                    "ServicioTP6",
                    servicio);

            System.out.println(
                    "Servidor RMI iniciado correctamente");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}