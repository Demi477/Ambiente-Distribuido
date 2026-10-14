package modelo;

import java.io.Serializable;

public class Estadisticas implements Serializable {

    private double promedio;
    private double maximo;
    private double minimo;
    private double desviacion;

    public Estadisticas(double promedio,
            double maximo,
            double minimo,
            double desviacion) {

        this.promedio = promedio;
        this.maximo = maximo;
        this.minimo = minimo;
        this.desviacion = desviacion;
    }

    @Override
    public String toString() {

        return "\nPromedio: " + promedio +
                "\nMáximo: " + maximo +
                "\nMínimo: " + minimo +
                "\nDesviación Estándar: " + desviacion;
    }
}