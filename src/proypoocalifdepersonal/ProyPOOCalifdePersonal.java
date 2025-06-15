package proypoocalifdepersonal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ProyPOOCalifdePersonal {
    public static void main(String[] args) {
        // Inicialización del sistema
        SistemaAutenticacion auth = new SistemaAutenticacion();
        GestionDeEmpleados gestion = new GestionDeEmpleados(auth);
        Scanner scanner = new Scanner(System.in);

        // Simulación de login
        System.out.println("=== SISTEMA DE GESTIÓN DE EMPLEADOS ===");
        boolean autenticado = false;
        while (!autenticado) {
            System.out.print("Usuario: ");
            String usuario = scanner.nextLine();
            System.out.print("Contraseña: ");
            String contrasena = scanner.nextLine();
            
            autenticado = auth.login(usuario, contrasena);
            if (!autenticado) {
                System.out.println("Credenciales incorrectas. Intente nuevamente.");
            }
        }

        System.out.println("\n¡Bienvenido, " + auth.getUsuarioActual().getUsername() + "!");

        // Menú principal
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    probarGestionEmpleados(gestion, scanner);
                    break;
                case 2:
                    probarGestionCalificaciones(gestion, scanner);
                    break;
                case 3:
                    probarBusquedas(gestion, scanner);
                    break;
                case 4:
                    System.out.println("\n=== REPORTE COMPLETO ===");
                    mostrarTodosEmpleadosConCalificaciones(gestion);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Gestión de Empleados");
        System.out.println("2. Gestión de Calificaciones");
        System.out.println("3. Búsquedas");
        System.out.println("4. Reporte completo");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void probarGestionEmpleados(GestionDeEmpleados gestion, Scanner scanner) {
        System.out.println("\n=== PRUEBA GESTIÓN DE EMPLEADOS ===");
        
        // Crear empleados de prueba
        System.out.println("\nCreando empleados...");
        gestion.crearEmpleado(123456789, "Juan Pérez", "2023-01-15", "Ventas", "Vendedor");
        gestion.crearEmpleado(987654321, "María García", "2022-05-20", "TI", "Desarrollador");
        gestion.crearEmpleado(555555555, "Carlos López", "2023-03-10", "Contabilidad", "Contador");
        System.out.println("Empleados creados exitosamente.");

        // Mostrar todos los empleados
        System.out.println("\nListado completo de empleados:");
        gestion.obtenerTodosEmpleados().forEach(emp -> 
            System.out.println(emp.getCedula() + " - " + emp.getNombreApellido() + " - " + emp.getCargo())
        );

        // Modificar un empleado
        System.out.println("\nModificando empleado 987654321...");
        gestion.modificarEmpleado(987654321, "María García López", "2022-05-20", "TI", "Desarrollador Senior");
        System.out.println("Empleado modificado: " + gestion.buscarEmpleadoPorCedula(987654321));

        // Intentar eliminar un empleado
        System.out.println("\nIntentando eliminar empleado 555555555...");
        if (gestion.eliminarEmpleado(555555555)) {
            System.out.println("Empleado eliminado exitosamente.");
        } else {
            System.out.println("No se pudo eliminar el empleado.");
        }
    }

    private static void probarGestionCalificaciones(GestionDeEmpleados gestion, Scanner scanner) {
        System.out.println("\n=== PRUEBA GESTIÓN DE CALIFICACIONES ===");
        
        // Agregar calificaciones
        System.out.println("\nAgregando calificaciones...");
        gestion.agregarCalificacion(123456789, 3, "Excelente desempeño en ventas");
        gestion.agregarCalificacion(123456789, 2, "Necesita mejorar en reportes");
        gestion.agregarCalificacion(987654321, 3, "Desarrollo de alta calidad");
        System.out.println("Calificaciones agregadas exitosamente.");

        // Mostrar calificaciones de un empleado
        System.out.println("\nCalificaciones para empleado 123456789:");
        gestion.obtenerCalificaciones(123456789).forEach(System.out::println);

        // Modificar una calificación
        System.out.println("\nModificando primera calificación del empleado 123456789...");
        gestion.modificarCalificacion(123456789, 0, 2, "Buen desempeño pero con áreas de mejora");

        // Eliminar una calificación
        System.out.println("\nEliminando segunda calificación del empleado 123456789...");
        gestion.eliminarCalificacion(123456789, 1);

        // Mostrar calificaciones actualizadas
        System.out.println("\nCalificaciones actualizadas para empleado 123456789:");
        gestion.obtenerCalificaciones(123456789).forEach(System.out::println);
    }

    private static void probarBusquedas(GestionDeEmpleados gestion, Scanner scanner) {
        System.out.println("\n=== PRUEBA BÚSQUEDAS ===");
        
        // Búsqueda por texto
        System.out.println("\nBuscando empleados con 'ga'...");
        List<Empleado> resultados = gestion.buscarEmpleadosPorTexto("ga");
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron resultados.");
        } else {
            resultados.forEach(emp -> 
                System.out.println(emp.getCedula() + " - " + emp.getNombreApellido())
            );
        }

        // Búsqueda por cédula
        System.out.println("\nBuscando empleado con cédula 987654321...");
        Empleado encontrado = gestion.buscarEmpleadoPorCedula(987654321);
        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado.getNombreApellido());
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    private static void mostrarTodosEmpleadosConCalificaciones(GestionDeEmpleados gestion) {
        List<Empleado> empleados = gestion.obtenerTodosEmpleados();
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }

        for (Empleado emp : empleados) {
            System.out.println("\n" + emp.getNombreApellido() + " (" + emp.getCedula() + ")");
            System.out.println("Departamento: " + emp.getDepartamento() + " - Cargo: " + emp.getCargo());
            
            List<Calificacion> calificaciones = gestion.obtenerCalificaciones(emp.getCedula());
            if (calificaciones.isEmpty()) {
                System.out.println("  Sin calificaciones registradas.");
            } else {
                System.out.println("  Calificaciones:");
                calificaciones.forEach(calif -> System.out.println("  - " + calif));
                double promedio = calificaciones.stream()
                    .mapToInt(Calificacion::getEstrellas)
                    .average()
                    .orElse(0.0);
                System.out.printf("  Promedio: %.1f estrellas\n", promedio);
            }
        }
    }
}