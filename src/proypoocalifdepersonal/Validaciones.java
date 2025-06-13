
package proypoocalifdepersonal;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validaciones {   
    public static String nombreSinNumeros() {
    Scanner scanner = new Scanner(System.in);
    String nombre;
    nombre = scanner.nextLine();
    while (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
        System.out.println("Error: Por favor ingrese un nombre válido (solo letras y espacios).");
        nombre = scanner.nextLine();
    }
    return nombre;
    }

    public static int soloNumeros() {
        Scanner scanner = new Scanner(System.in);
        int numero;

        while (true) {
            String input = scanner.nextLine().trim();

            try {
                numero = Integer.parseInt(input);
                return numero;
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Entrada inválida. Solo se permiten números. Intente nuevamente.");
            }
        }
    }

    public static void procesarEntrada(String entrada) {
        if (entrada == null || entrada.isEmpty()) {
            System.out.println("La entrada está vacía");
            return;
        }

        boolean contieneNumeros = entrada.matches(".*\\d.*"); // Verifica si hay dígitos
        // Verifica si hay letras (incluye ñ y acentos)
        boolean contieneLetras = entrada.matches(".*[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ].*"); 

        if (contieneNumeros && contieneLetras) {
            System.out.println("Error: Por favor ingresa solo números o solo letras, no ambos.");
        } else if (contieneNumeros) {
            procesarNumeros(entrada);
        } else if (contieneLetras) {
            procesarLetras(entrada);
        } else {
            System.out.println("La entrada no contiene letras ni números válidos");
        }
    }
//aqui ejecutar las acciones de buscar por cedula y por ID de empleado etc
    private static void procesarNumeros(String numeros) {
        System.out.println("Procesando números: " + numeros);
        try {
            int numero = Integer.parseInt(numeros);
            System.out.println("Número convertido: " + numero);
            // Aquí puedes añadir más lógica para números
        } catch (NumberFormatException e) {
            System.out.println("El formato del número no es válido");
        }
    }
    //aqui en procesarletras ejecutar las acciones de buscar por nombre, por depto, etc
    private static void procesarLetras(String letras) {
        System.out.println("Procesando texto: " + letras);
        System.out.println("Texto normalizado (sin acentos): " + normalizarTexto(letras));
    }   
    private static String normalizarTexto(String texto) {
        texto = texto.replaceAll("[áàäâã]", "a")
                     .replaceAll("[éèëê]", "e")
                     .replaceAll("[íìïî]", "i")
                     .replaceAll("[óòöôõ]", "o")
                     .replaceAll("[úùüû]", "u")
                     .replaceAll("[ÁÀÄÂÃ]", "A")
                     .replaceAll("[ÉÈËÊ]", "E")
                     .replaceAll("[ÍÌÏÎ]", "I")
                     .replaceAll("[ÓÒÖÔÕ]", "O")
                     .replaceAll("[ÚÙÜÛ]", "U");
        return texto;
    }

    /* Ejemplos de uso
    public static void main(String[] args) {
        procesarEntrada("12345");        // Solo números
        procesarEntrada("cañón");        // Palabra con ñ
        procesarEntrada("México");       // Palabra con acento
        procesarEntrada("pingüino");     // Palabra con diéresis
        procesarEntrada("abc123");       */
    
}