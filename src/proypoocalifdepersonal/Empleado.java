
package proypoocalifdepersonal;

import java.util.Scanner;


public class Empleado {
    protected int cedula;
    protected String NombreApellido;
    protected String fechaContratacion;
    protected String Departamento;
    protected int puntos;

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombreApellido() {
        return NombreApellido;
    }

    public void setNombreApellido(String NombreApellido) {
        this.NombreApellido = NombreApellido;
    }

    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String Departamento) {
        this.Departamento = Departamento;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
    Scanner datos= new Scanner(System.in);
    
    public void crearEmpleado(){
        System.out.println("Porfavor ingrese la cedula del empleado: ");
        cedula = datos.nextInt();
        datos.nextLine();
        System.out.println("Porfavor ingrese los Nombres y Apellidos del empleado: ");
        NombreApellido= datos.nextLine();
        System.out.println("Porfavor ingrese el departamento al que pertenece el empleado: ");
        Departamento = datos.nextLine();
        System.out.println("Porfavor ingrese la fecha de contratacion del empleado: ");
        fechaContratacion = datos.nextLine();
        
    }
}
