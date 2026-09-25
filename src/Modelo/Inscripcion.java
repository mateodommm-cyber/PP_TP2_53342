package Modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable {
    private LocalDate fecha;
    private String estado;
    private Estudiante estudiante;
    private TicketDeAcceso ticket;

    public Inscripcion(Estudiante estudiante, String estado) {
        this.estudiante = estudiante;
        this.estado = estado;
        this.fecha = LocalDate.now();
    }

    public void generarTicket() {
        if ("Confirmada".equalsIgnoreCase(this.estado)) {
            this.ticket = new TicketDeAcceso();
        } else {
            System.out.println("No se puede emitir ticket. La inscripción no está confirmada.");
        }
    }

    public TicketDeAcceso getTicket() {
        return ticket;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public class TicketDeAcceso implements Serializable {
        private String idTicket;

        public TicketDeAcceso() {
            this.idTicket = "TKT-" + estudiante.getLegajo();
        }

        public void enviarTicket() {
            System.out.println("[Enviando Ticket...] " + idTicket + " emitido para: " + estudiante.getNombre());
        }
    }
}