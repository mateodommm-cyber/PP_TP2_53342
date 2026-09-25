package Modelo.actividades;

import Excepciones.CupoExcedidoException;
import Modelo.Inscripcion;
import Modelo.Estudiante;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Actividad implements Serializable {

    private int id;
    private String título;
    private int cupoMaximo;
    public final int CUPO_MINIMO= 5;

    private List<Inscripcion> inscripciones = new ArrayList<>();

    public Actividad(int id, String título, int cupoMaximo) {
        this.id= id;
        this.título=título;
        this.cupoMaximo=cupoMaximo;
    }

    public Inscripcion inscribir(Estudiante estudiante) throws CupoExcedidoException{
        if(inscripciones.size() >= cupoMaximo){
            throw new CupoExcedidoException("Cupo agotado para la actividad "+título);

        }
        Inscripcion nuevaInscripcion = new Inscripcion(estudiante, "Confirmada");
        inscripciones.add(nuevaInscripcion);
        return nuevaInscripcion;
    }
    public void mostrarInscripciones(){
        System.out.println("Inscripciones para "+ título);
        for(Inscripcion inc: inscripciones){
            System.out.println("- "+inc.getEstudiante().getNombre());
        }
    }
    public final void mostrarIdentificación(){
        System.out.println("Actividad"+título+"(ID: "+id+")");
    }
    public abstract double calcularCostoMateriales();
    public abstract String getTipo();

    public int getId(){
        return id;
    }
    public String getTítulo(){
        return título;
    }

    public int getCupoMaximo(){
        return cupoMaximo;
    }
    public List<Inscripcion> getInscripciones(){return inscripciones;}

}
