import Excepciones.CupoExcedidoException;
import Hilos.EnvioTicketsThread;
import Modelo.Estudiante;
import Modelo.EventoUniversitario;
import Modelo.Inscripcion;
import Modelo.Sala;
import Modelo.actividades.Taller;

public class AppE4 {
    public static void main(String[] args) {

        EventoUniversitario evento = new EventoUniversitario("E-04", "Testing de Concurrencia", 0.0, true);
        evento.asignarSala(new Sala(10, "Laboratorio de Redes"));
        Taller taller = new Taller(99, "Manejo de Threads", 10, true);
        evento.crearActividad(taller);

        Estudiante est1 = new Estudiante("Mateo", 53342);
        Estudiante est2 = new Estudiante("Melanie", 53343);

        try {
            Inscripcion insc1 = taller.inscribir(est1);
            Inscripcion insc2 = taller.inscribir(est2);

            insc1.generarTicket();
            insc2.generarTicket();

        } catch (CupoExcedidoException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("--- INICIANDO SISTEMA DE ENVÍO DE TICKETS ---");

        EnvioTicketsThread hiloEnvio = new EnvioTicketsThread(evento);
        hiloEnvio.start();

        System.out.println("[MAIN] El sistema principal está libre. Procesando otras tareas...");
        for (int i = 1; i <= 4; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("[MAIN] Tarea principal número " + i + " completada.");
            } catch (InterruptedException e) {
                System.err.println("Error en el main: " + e.getMessage());
            }
        }
    }
}