package proypoocalifdepersonal;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import static proypoocalifdepersonal.Usuario.validarLogin;

public class ProyPOOCalifdePersonal {
    public static void main(String[] args) {
        GestionDeEmpleados gestion = new GestionDeEmpleados();

        gestion.crearEmpleado(123456789, "Mario Bros", "2023-01-15", 
                             "Contabilidad", 85, "Contador");

        System.out.println("\nEmpleados en 'Contabilidad':");
        List<Empleado> resultadosContabilidad = gestion.buscarEmpleadosPorTexto("contabilidad");
        resultadosContabilidad.forEach(System.out::println);

        System.out.println("\nEmpleados con 'Mar':");
        List<Empleado> resultadosMar = gestion.buscarEmpleadosPorTexto("Mar");
        resultadosMar.forEach(System.out::println);
        System.out.println("AlexMoschella + PruebaPROyecto92 " + validarLogin("AlexMoschella", "PruebaPROyecto92"));
        System.out.println("ALEXMOschelLA + PruebaProyecto92 " + validarLogin("ALEXMOschelLA", "PruebaProyecto92"));
        System.out.println("AlexMoschella + PruebaProyecto92 " + validarLogin("AlexMoschella", "PruebaProyecto92"));
    }
}