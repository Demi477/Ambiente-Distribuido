package cliente;

import interfaces.ServicioRemoto;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        try {

            Registry registro = LocateRegistry.getRegistry(
                    "localhost",
                    1099);

            ServicioRemoto servicio = (ServicioRemoto) registro.lookup(
                    "ServicioTP6");

            Scanner sc = new Scanner(System.in);

            int opcion;

            do {

                System.out.println("\n--- MENU ---");
                System.out.println("1. Estadisticas");
                System.out.println("2. Validar DNI");
                System.out.println("3. Filtrar Texto");
                System.out.println("0. Salir");

                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {

                    case 1:

                        double[] numeros = {
                                10,
                                20,
                                30,
                                40,
                                50
                        };

                        System.out.println(
                                servicio
                                        .calcularEstadisticas(
                                                numeros));
                        break;

                    case 2:

                        System.out.print(
                                "Ingrese DNI: ");

                        String dni = sc.nextLine();

                        System.out.println(
                                "Válido: "
                                        + servicio
                                                .validarDNI(dni));
                        break;

                    case 3:

                        String[] textos = {
                                "Java RMI",
                                "Middleware",
                                "Redes",
                                "Java Distribuido"
                        };

                        System.out.print(
                                "Palabra a buscar: ");

                        String patron = sc.nextLine();

                        System.out.println(
                                servicio
                                        .filtrarTexto(
                                                textos,
                                                patron));
                        break;
                }

            } while (opcion != 0);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}