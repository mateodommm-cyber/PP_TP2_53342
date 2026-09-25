package Modelo;

import java.io.Serializable;

public class Estudiante implements Serializable {
    private String Nombre;
    private int Legajo;

    public Estudiante(String Nombre, int Legajo) {
        this.Nombre = Nombre;
        this.Legajo = Legajo;
    }

    public String getNombre(){
        return Nombre;
    }

    public int getLegajo(){
        return Legajo;
    }

    public void setNombre(String Nombre){
        this.Nombre=Nombre;
    }
    public void setLegajo(int Legajo){
        this.Legajo=Legajo;
    }

}
