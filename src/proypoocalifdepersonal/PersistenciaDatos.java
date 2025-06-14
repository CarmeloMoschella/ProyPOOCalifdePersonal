package proypoocalifdepersonal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaDatos {
    private static final String ARCHIVO_JSON = "datos_empleados.json";
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .setPrettyPrinting()
            .create();

    public static class DatosCompletos {
        public List<Empleado> empleados = new ArrayList<>();
        public List<Calificacion> calificaciones = new ArrayList<>();
    }

    public static void guardarTodo(List<Empleado> empleados) {
        DatosCompletos datos = new DatosCompletos();
        datos.empleados = empleados;
        
        // Recoger todas las calificaciones de los empleados
        for (Empleado emp : empleados) {
            datos.calificaciones.addAll(emp.getCalificaciones());
        }

        try (FileWriter writer = new FileWriter(ARCHIVO_JSON)) {
            gson.toJson(datos, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar datos: " + e.getMessage());
        }
    }

    public static DatosCompletos cargarTodo() {
        try {
            if (!Files.exists(Paths.get(ARCHIVO_JSON))) {
                return new DatosCompletos();
            }
            
            String json = new String(Files.readAllBytes(Paths.get(ARCHIVO_JSON)));
            return gson.fromJson(json, new TypeToken<DatosCompletos>(){}.getType());
            
        } catch (IOException e) {
            System.err.println("Error al cargar datos: " + e.getMessage());
            return new DatosCompletos();
        }
    }

    static class LocalDateTimeAdapter implements com.google.gson.JsonSerializer<LocalDateTime>, 
                                              com.google.gson.JsonDeserializer<LocalDateTime> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        @Override
        public com.google.gson.JsonElement serialize(LocalDateTime src, Type typeOfSrc, 
                                                   com.google.gson.JsonSerializationContext context) {
            return new com.google.gson.JsonPrimitive(formatter.format(src));
        }

        @Override
        public LocalDateTime deserialize(com.google.gson.JsonElement json, Type typeOfT,
                                       com.google.gson.JsonDeserializationContext context) {
            return LocalDateTime.parse(json.getAsString(), formatter);
        }
    }
}