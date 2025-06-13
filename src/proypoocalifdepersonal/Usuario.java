package proypoocalifdepersonal;
import java.util.HashMap;
import java.util.Map;

public class Usuario {   
    private static final Map<String, String> USUARIOS = new HashMap<>();
    
    static {

        registrarUsuario("admin", "Admin123");
        registrarUsuario("usuario1", "MiClave123");
        registrarUsuario("AlexMoschella", "PruebaProyecto92");
    }
    
    private static void registrarUsuario(String usuario, String contrasena) {
        USUARIOS.put(usuario.toLowerCase(), contrasena);
    }
    
    public static boolean validarLogin(String inputUsuario, String inputContrasena) {
        if (inputUsuario == null || inputUsuario.isEmpty() || 
            inputContrasena == null || inputContrasena.isEmpty()) {
            return false;
        }       
        String usuarioKey = inputUsuario.toLowerCase();
        
        return USUARIOS.containsKey(usuarioKey) && 
               USUARIOS.get(usuarioKey).equals(inputContrasena);
    }
}