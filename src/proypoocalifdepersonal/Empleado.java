
package proypoocalifdepersonal;
public class Empleado {
    protected int cedula;
    protected String nombreApellido;
    protected String fechaContratacion;
    protected String departamento;
    protected int puntos;
    protected String cargo;

    public Empleado(int cedula, String nombreApellido, String fechaContratacion, 
                   String departamento, int puntos, String cargo) {
        this.cedula = cedula;
        this.nombreApellido = nombreApellido;
        this.fechaContratacion = fechaContratacion;
        this.departamento = departamento;
        this.puntos = puntos;
        this.cargo = cargo;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "cedula=" + cedula +
                ", nombreApellido='" + nombreApellido + '\'' +
                ", fechaContratacion='" + fechaContratacion + '\'' +
                ", departamento='" + departamento + '\'' +
                ", puntos=" + puntos +
                ", cargo='" + cargo + '\'' +
                '}';
    }
}
