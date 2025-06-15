package proypoocalifdepersonal;

import java.util.HashMap;
import java.util.Map;

public class SistemaAutenticacion {
    private Map<String, Usuario> usuarios;
    private Usuario usuarioActual;

    public SistemaAutenticacion() {
        this.usuarios = new HashMap<>();
        usuarios.put("admin", new Usuario("admin", "admin123"));
        usuarios.put("supervisor", new Usuario("supervisor", "sup123"));
    }

    public boolean login(String username, String password) {
        Usuario usuario = usuarios.get(username);
        if (usuario != null && usuario.validarPassword(password)) {
            this.usuarioActual = usuario;
            return true;
        }
        return false;
    }

    public void logout() {
        this.usuarioActual = null;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
}