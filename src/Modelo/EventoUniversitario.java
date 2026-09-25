package Modelo;

import Modelo.actividades.Actividad;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario implements Serializable {
    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private int cantidadEventos;
    private Sala sala;
    private List<Actividad> actividades;

    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        this.actividades = new ArrayList<>();
    }

    public void asignarSala(Sala sala) {
        this.sala = sala;
    }

    public void crearActividad(Actividad actividad) {
        this.actividades.add(actividad);
    }

    public boolean persistirEvento() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.id + ".dat"))) {
            oos.writeObject(this);
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar el evento: " + e.getMessage());
            return false;
        }
    }

    public static EventoUniversitario recuperarEvento(String id) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(id + ".dat"))) {
            return (EventoUniversitario) ois.readObject();
        }
    }

    public String getTitulo() { return titulo; }

    public void mostrarDatos() {
        System.out.println("Evento: " + titulo + " (ID: " + id + ")");
        System.out.println("Costo Base: $" + costoBase + " | Gratuito: " + gratuito);
        if (sala != null) {
            System.out.println("Sala asignada: " + sala.getNombre()); // Asegúrate de tener getNombre() en Sala
        }
    }

    public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo) {
        List<T> listaFiltrada = new ArrayList<>();
        for (Actividad act : this.actividades) {
            if (tipo.isInstance(act)) {
                listaFiltrada.add(tipo.cast(act));
            }
        }
        return listaFiltrada;
    }

    public double calcularCostoMateriales(List<? extends Actividad> actividadesFiltradas) {
        double costoTotal = 0.0;
        for (Actividad act : actividadesFiltradas) {
            costoTotal += act.calcularCostoMateriales();
        }
        return costoTotal;
    }
    public List<Actividad> getActividades() {
        return actividades;
    }
}