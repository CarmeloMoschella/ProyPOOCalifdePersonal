package proypoocalifdepersonal;

import java.util.ArrayList;
import java.util.List;

public class Empleado {
    private int cedula;
    private String nombreApellido;
    private String fechaContratacion;
    private String departamento;
    private String cargo;
    private List<Calificacion> calificaciones;

    public Empleado(int cedula, String nombreApellido, String fechaContratacion,
                   String departamento, String cargo) {
        this.cedula = cedula;
        this.nombreApellido = nombreApellido;
        this.fechaContratacion = fechaContratacion;
        this.departamento = departamento;
        this.cargo = cargo;
        this.calificaciones = new ArrayList<>();
    }
    public Empleado() {
        this.calificaciones = new ArrayList<>();
    }
    // Getters y setters
    public int getCedula() { return cedula; }
    public void setCedula(int cedula) { this.cedula = cedula; }
    
    public String getNombreApellido() { return nombreApellido; }
    public void setNombreApellido(String nombreApellido) { this.nombreApellido = nombreApellido; }
    
    public String getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(String fechaContratacion) { this.fechaContratacion = fechaContratacion; }
    
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    
    public List<Calificacion> getCalificaciones() { return calificaciones; }
    
    public void agregarCalificacion(Calificacion calificacion) {
        this.calificaciones.add(calificacion);
    }
    
   
    public void setCalificaciones(List<Calificacion> calificaciones) {
         this.calificaciones = calificaciones;
    }



}