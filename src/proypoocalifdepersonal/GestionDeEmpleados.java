package proypoocalifdepersonal;

import java.util.ArrayList;
import java.util.List;

public class GestionDeEmpleados {
    private List<Empleado> listaEmpleados;
    private final SistemaAutenticacion auth;

    public GestionDeEmpleados(SistemaAutenticacion auth) {
        this.auth = auth;
        this.listaEmpleados = new ArrayList<>();
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        PersistenciaDatos.DatosCompletos datos = PersistenciaDatos.cargarTodo();
        
        if (datos.empleados != null) {
            this.listaEmpleados = datos.empleados;
            
            // Asociar calificaciones a empleados
            if (datos.calificaciones != null) {
                for (Calificacion calif : datos.calificaciones) {
                    Empleado emp = buscarEmpleadoPorCedula(calif.getCedulaEmpleado());
                    if (emp != null) {
                        emp.agregarCalificacion(calif);
                    }
                }
            }
        }
    }

    private void guardarDatos() {
        PersistenciaDatos.guardarTodo(this.listaEmpleados);
    }

    // Métodos de gestión de empleados
    public boolean crearEmpleado(int cedula, String nombreApellido, String fechaContratacion,
                               String departamento, String cargo) {
        if (buscarEmpleadoPorCedula(cedula) != null) {
            return false;
        }
        
        Empleado nuevoEmpleado = new Empleado(cedula, nombreApellido, fechaContratacion, 
                                             departamento, cargo);
        listaEmpleados.add(nuevoEmpleado);
        guardarDatos();
        return true;
    }

    public boolean modificarEmpleado(int cedula, String nombreApellido, String fechaContratacion,
                                   String departamento, String cargo) {
        Empleado empleado = buscarEmpleadoPorCedula(cedula);
        if (empleado == null) {
            return false;
        }
        
        empleado.setNombreApellido(nombreApellido);
        empleado.setFechaContratacion(fechaContratacion);
        empleado.setDepartamento(departamento);
        empleado.setCargo(cargo);
        
        guardarDatos();
        return true;
    }

    public boolean eliminarEmpleado(int cedula) {
        Empleado empleado = buscarEmpleadoPorCedula(cedula);
        if (empleado == null) {
            return false;
        }
        
        listaEmpleados.remove(empleado);
        guardarDatos();
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

    // Métodos de búsqueda
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

    private boolean contieneTexto(Empleado emp, String textoLower) {
        return String.valueOf(emp.getCedula()).contains(textoLower) ||
               emp.getNombreApellido().toLowerCase().contains(textoLower) ||
               emp.getFechaContratacion().toLowerCase().contains(textoLower) ||
               emp.getDepartamento().toLowerCase().contains(textoLower) ||
               emp.getCargo().toLowerCase().contains(textoLower);
    }

    // Métodos de gestión de calificaciones
    public List<Calificacion> obtenerCalificaciones(int cedula) {
        Empleado empleado = buscarEmpleadoPorCedula(cedula);
        if (empleado == null) {
            return new ArrayList<>();
        }
        return empleado.getCalificaciones();
    }

    public boolean agregarCalificacion(int cedula, int estrellas, String razon) {
        if (auth.getUsuarioActual() == null) return false;
        
        Empleado empleado = buscarEmpleadoPorCedula(cedula);
        if (empleado == null) {
            return false;
        }
        
        try {
            Calificacion nuevaCalif = new Calificacion(estrellas, razon, auth.getUsuarioActual().getUsername());
            nuevaCalif.setCedulaEmpleado(cedula);
            empleado.agregarCalificacion(nuevaCalif);
            guardarDatos();
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean modificarCalificacion(int cedula, int indiceCalificacion, 
                                       int nuevasEstrellas, String nuevaRazon) {
        if (auth.getUsuarioActual() == null) return false;
        
        Empleado empleado = buscarEmpleadoPorCedula(cedula);
        if (empleado == null || indiceCalificacion < 0 || 
            indiceCalificacion >= empleado.getCalificaciones().size()) {
            return false;
        }

        try {
            Calificacion calificacion = empleado.getCalificaciones().get(indiceCalificacion);
            calificacion.setEstrellas(nuevasEstrellas);
            calificacion.setRazon(nuevaRazon);
            calificacion.setUsuarioModificacion(auth.getUsuarioActual().getUsername());
            guardarDatos();
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean eliminarCalificacion(int cedula, int indiceCalificacion) {
        if (auth.getUsuarioActual() == null) return false;
        
        Empleado empleado = buscarEmpleadoPorCedula(cedula);
        if (empleado == null || indiceCalificacion < 0 || 
            indiceCalificacion >= empleado.getCalificaciones().size()) {
            return false;
        }

        empleado.getCalificaciones().remove(indiceCalificacion);
        guardarDatos();
        return true;
    }
}