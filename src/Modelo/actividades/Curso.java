package Modelo.actividades;

import Modelo.Certificacion.Certificable;
import Modelo.Estudiante;

public class Curso extends Actividad implements Modelo.Certificacion.Certificable {
    private int nivel;

    public Curso(int id, String titulo, int cupoMaximo, int nivel) {
        super(id, titulo, cupoMaximo);
        this.nivel = nivel;
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return ENTIDAD_EMISORA + " certifica que " + estudiante.getNombre() +
                " aprobó el Curso de nivel " + nivel + ": " + getTítulo();
    }

    @Override
    public double calcularCostoMateriales() {
        return 2500.0 * nivel;
    }

    @Override
    public String getTipo() {
        return "Curso";
    }

    public int getNivel() {
        return nivel;
    }
}