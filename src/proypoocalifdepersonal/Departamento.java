
package proypoocalifdepersonal;
import java.util.ArrayList;
import java.util.List;

public class Departamento {
    protected String nombre;
    protected List<Empleado> empleados;
    
    
    public Departamento(String nombre) {
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
    }

    // Método para agregar empleados
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    // Método para eliminar empleados
    public boolean eliminarEmpleado(Empleado empleado) {
        return empleados.remove(empleado);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
    
}
