import Excepciones.CupoExcedidoException;
import Modelo.Estudiante;
import Modelo.EventoUniversitario;
import Modelo.Sala;
import Modelo.actividades.Charla;
import Modelo.actividades.Curso;
import Modelo.actividades.Taller;

import java.util.List;

public class AppE3 {
    public static void main(String[] args) {
        Estudiante est1 = new Estudiante("Fausto", 55532);
        Estudiante est2 = new Estudiante("Santiago", 55352);

        EventoUniversitario evento = new EventoUniversitario("E-03", "Congreso de Sistemas", 3000.0, false);
        evento.asignarSala(new Sala(3, "Auditorio Sur"));

        Charla charla1 = new Charla(10, "IA en la educación", 50, "Dra. Silva");
        Charla charla2 = new Charla(11, "Futuro de Java", 50, "Ing. Vega");
        Taller taller1 = new Taller(12, "Patrones de Diseño", 20, true);
        Curso curso1 = new Curso(13, "Spring Boot", 30, 2);

        evento.crearActividad(charla1);
        evento.crearActividad(charla2);
        evento.crearActividad(taller1);
        evento.crearActividad(curso1);

        try {
            charla1.inscribir(est1);
            taller1.inscribir(est2);
            curso1.inscribir(est1);
            curso1.inscribir(est2);
        } catch (CupoExcedidoException e) {
            System.err.println("Error de cupo: " + e.getMessage());
        }

        System.out.println("--- ESTADÍSTICAS DEL EVENTO (EJERCICIO 3) ---");

        List<Charla> listaCharlas = evento.filtrarActividadesPorTipo(Charla.class);
        List<Taller> listaTalleres = evento.filtrarActividadesPorTipo(Taller.class);
        List<Curso> listaCursos = evento.filtrarActividadesPorTipo(Curso.class);

        System.out.println("Charlas creadas: " + listaCharlas.size());
        System.out.println("Costo total de materiales (Charlas): $" + evento.calcularCostoMateriales(listaCharlas));

        System.out.println("\nTalleres creados: " + listaTalleres.size());
        System.out.println("Costo total de materiales (Talleres): $" + evento.calcularCostoMateriales(listaTalleres));

        System.out.println("\nCursos creados: " + listaCursos.size());
        System.out.println("Costo total de materiales (Cursos): $" + evento.calcularCostoMateriales(listaCursos));
    }
}