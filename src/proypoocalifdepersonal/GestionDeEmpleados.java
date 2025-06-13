package proypoocalifdepersonal;
import java.util.ArrayList;
import java.util.List;

public class GestionDeEmpleados {
    private List<Empleado> listaEmpleados;

    public GestionDeEmpleados() {
        this.listaEmpleados = new ArrayList<>();
    }


    public boolean crearEmpleado(int cedula, String nombreApellido, String fechaContratacion,
                               String departamento, int puntos, String cargo) {
        if (buscarEmpleadoPorCedula(cedula) != null) {
            return false;
        }
        
        Empleado nuevoEmpleado = new Empleado(cedula, nombreApellido, fechaContratacion, 
                                             departamento, puntos, cargo);
        listaEmpleados.add(nuevoEmpleado);
        return true;
    }

    public boolean modificarEmpleado(int cedula, String nombreApellido, String fechaContratacion,
                                   String departamento, int puntos, String cargo) {
        Empleado empleado = buscarEmpleadoPorCedula(cedula);
        if (empleado == null) {
            return false;
        }
        
        empleado.setNombreApellido(nombreApellido);
        empleado.setFechaContratacion(fechaContratacion);
        empleado.setDepartamento(departamento);
        empleado.setPuntos(puntos);
        empleado.setCargo(cargo);
        
        return true;
    }

    public boolean eliminarEmpleado(int cedula) {
        Empleado empleado = buscarEmpleadoPorCedula(cedula);
        if (empleado == null) {
            return false;
        }
        
        listaEmpleados.remove(empleado);
        return true;
    }

    public Empleado buscarEmpleadoPorCedula(int cedula) {
        for (Empleado emp : listaEmpleados) {
            if (emp.getCedula() == cedula) {
                return emp;
            }
        }
        return null;
    }

    public List<Empleado> obtenerTodosEmpleados() {
        return new ArrayList<>(listaEmpleados);
    }
    
    private boolean contieneTexto(Empleado emp, String textoLower) {
        return String.valueOf(emp.getCedula()).contains(textoLower) ||
               emp.getNombreApellido().toLowerCase().contains(textoLower) ||
               emp.getFechaContratacion().toLowerCase().contains(textoLower) ||
               emp.getDepartamento().toLowerCase().contains(textoLower) ||
               String.valueOf(emp.getPuntos()).contains(textoLower) ||
               emp.getCargo().toLowerCase().contains(textoLower);
    }
    
     public List<Empleado> buscarEmpleadosPorTexto(String textoBusqueda) {
        List<Empleado> resultados = new ArrayList<>();
        if (textoBusqueda == null || textoBusqueda.trim().isEmpty()) {
            return resultados;
        }

        String textoLower = textoBusqueda.toLowerCase();
        
        for (Empleado emp : listaEmpleados) {
            if (contieneTexto(emp, textoLower)) {
                resultados.add(emp);
            }
        }
        
        return resultados;
    }

    
}