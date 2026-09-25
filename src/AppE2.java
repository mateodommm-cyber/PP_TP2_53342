import Excepciones.CupoExcedidoException;
import Modelo.Estudiante;
import Modelo.EventoUniversitario;
import Modelo.Sala;
import Modelo.actividades.Charla;
import Modelo.actividades.Curso;
import Modelo.actividades.Taller;
import Modelo.Certificacion.Certificable;

public class AppE2 {
    public static void main(String[] args) {
        Estudiante est1 = new Estudiante("Ana", 1002);
        Estudiante est2 = new Estudiante("Juan", 1001);

        EventoUniversitario evento = new EventoUniversitario("E-02", "Semana de la Tecnología", 2000.0, false);
        evento.asignarSala(new Sala(2, "Laboratorio Central"));

        Charla charla = new Charla(1, "Tendencias IT", 50, "Ing. Gómez");
        Taller taller = new Taller(2, "Desarrollo Web", 30, true);
        Curso curso = new Curso(3, "Java Avanzado", 20, 2);

        evento.crearActividad(charla);
        evento.crearActividad(taller);
        evento.crearActividad(curso);

        System.out.println("--- DATOS DEL EVENTO ---");
        evento.mostrarDatos();

        System.out.println("\n--- INSCRIPCIONES Y CERTIFICADOS ---");
        try {
            charla.inscribir(est1);
            taller.inscribir(est2);
            curso.inscribir(est1);

            if (taller instanceof Certificable) {
                System.out.println(((Certificable) taller).generarCertificado(est2));
            }

            if (curso instanceof Certificable) {
                System.out.println(((Certificable) curso).generarCertificado(est1));
            }

        } catch (CupoExcedidoException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}