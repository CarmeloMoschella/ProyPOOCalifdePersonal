package proypoocalifdepersonal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Calificacion {
    private int cedulaEmpleado;
    private int estrellas;
    private String razon;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioCreacion;
    private String usuarioModificacion;

    public Calificacion(int estrellas, String razon, String usuario) {
        this.estrellas = estrellas;
        this.razon = razon;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaModificacion = LocalDateTime.now();
        this.usuarioCreacion = usuario;
        this.usuarioModificacion = usuario;
    }

    // Getters y setters
    public int getCedulaEmpleado() { return cedulaEmpleado; }
    public void setCedulaEmpleado(int cedula) { this.cedulaEmpleado = cedula; }
    
    public int getEstrellas() { return estrellas; }
    public void setEstrellas(int estrellas) {
        if (estrellas < 1 || estrellas > 3) {
            throw new IllegalArgumentException("Las estrellas deben estar entre 1 y 3");
        }
        this.estrellas = estrellas;
    }

    public String getRazon() { return razon; }
    public void setRazon(String razon) { this.razon = razon; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public LocalDateTime getFechaModificacion() { return fechaModificacion; }
    
    public String getUsuarioCreacion() { return usuarioCreacion; }
    public String getUsuarioModificacion() { return usuarioModificacion; }
    public void setUsuarioModificacion(String usuario) { this.usuarioModificacion = usuario; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("[%s] %s - ★%d - %s (Modificado por: %s)", 
               fechaCreacion.format(formatter), usuarioCreacion, estrellas, razon, usuarioModificacion);
    }
    public Calificacion() {
    
    }

}