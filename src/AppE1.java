import Excepciones.CupoExcedidoException;
import Modelo.Estudiante;
import Modelo.EventoUniversitario;
import Modelo.Sala;
import Modelo.actividades.Charla;

import java.io.IOException;

public class AppE1 {
    public static void main(String[] args) {
        EventoUniversitario evento = new EventoUniversitario("EV-001", "Jornadas de Programación", 5000.0, false);
        evento.asignarSala(new Sala(1, "Auditorio Principal"));

        Charla charla = new Charla(101, "Introducción a Java", 1, "Ing. López");
        evento.crearActividad(charla);

        Estudiante estudiante1 = new Estudiante("Ana", 50111);
        Estudiante estudiante2 = new Estudiante("Juan", 50222);

        System.out.println("--- INICIANDO SISTEMA DE INSCRIPCIONES ---");

        try {
            System.out.println("Intentando inscribir a Ana...");
            charla.inscribir(estudiante1);
            System.out.println("Éxito: Ana fue inscripta.");

            System.out.println("Intentando inscribir a Juan...");
            charla.inscribir(estudiante2);
            System.out.println("Éxito: Juan fue inscripto."); // Esta línea no se ejecutará

            System.out.println("Guardando evento en archivo...");
            evento.persistirEvento();

            EventoUniversitario eventoRecuperado = EventoUniversitario.recuperarEvento("EV-001");
            System.out.println("Evento recuperado exitosamente: " + eventoRecuperado.getTitulo());

        } catch (CupoExcedidoException e) {
            System.err.println("ERROR DE CUPO: " + e.getMessage());

            System.out.println("Intentando guardar el evento a pesar del error de cupo...");
            evento.persistirEvento();

        } catch (IOException e) {
            System.err.println("ERROR DE ARCHIVO: No se pudo leer o escribir el evento. " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR DE CLASE: El archivo no corresponde a un EventoUniversitario. " + e.getMessage());
        } finally {
            System.out.println("--- PROCESO FINALIZADO ---");
        }
    }
}