package Hilos;

import Modelo.EventoUniversitario;
import Modelo.Inscripcion;
import Modelo.actividades.Actividad;

public class EnvioTicketsThread extends Thread {
    private EventoUniversitario evento;

    public EnvioTicketsThread(EventoUniversitario evento) {
        this.evento = evento;
    }

    @Override
    public void run() {
        System.out.println("-> [HILO TICKETS] Iniciando el envío en segundo plano...");

        for (Actividad actividad : evento.getActividades()) {
            for (Inscripcion inscripcion : actividad.getInscripciones()) {
                if (inscripcion.getTicket() != null) {
                    try {
                        Thread.sleep(1500);
                        inscripcion.getTicket().enviarTicket();
                    } catch (InterruptedException e) {
                        System.err.println("El hilo fue interrumpido: " + e.getMessage());
                    }
                }
            }
        }
        System.out.println("-> [HILO TICKETS] Todos los tickets fueron enviados.");
    }
}