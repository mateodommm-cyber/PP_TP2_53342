package Modelo.actividades;

import Modelo.Certificacion.Certificable;
import Modelo.Estudiante;

public class Taller extends Actividad implements Certificable {

    private boolean requiereNotebook;

    public Taller(int id, String título, int cupoMaximo, boolean requiereNotebook) {
        super(id, título, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return ENTIDAD_EMISORA + " certifica que " + estudiante.getNombre() +
                " asistió al Taller: " + getTítulo();
    }

    @Override
    public double calcularCostoMateriales() {
        return 1500.0;
    }

    @Override
    public String getTipo() {
        return "Taller";
    }

    public boolean getRequiereNotebook() {
        return requiereNotebook;
    }
}