package com.interfaz;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import proypoocalifdepersonal.Empleado;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class JsonUtil {

    private static final Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDateTime.class, new TypeAdapter<LocalDateTime>() {
            @Override
            public void write(JsonWriter out, LocalDateTime value) throws IOException {
                out.value(value.toString());
            }

            @Override
            public LocalDateTime read(JsonReader in) throws IOException {
                return LocalDateTime.parse(in.nextString());
            }
        })
        .create();

    private static class JsonData {
        List<Empleado> empleados;
        List<Object> calificaciones; // Puedes reemplazar Object por tu clase Calificacion si lo necesitas
    }

    public static List<Empleado> cargarEmpleados(String nombreArchivo) {
        Objects.requireNonNull(nombreArchivo, "El nombre del archivo no puede ser nulo");

        try (Reader reader = Files.newBufferedReader(Paths.get(nombreArchivo))) {
            JsonData data = gson.fromJson(reader, JsonData.class);

            if (data == null || data.empleados == null) {
                System.err.println("El archivo JSON no tiene la estructura esperada");
                return Collections.emptyList();
            }

            return data.empleados;

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return Collections.emptyList();
        } catch (JsonSyntaxException e) {
            e.printStackTrace(); // Para ayudarte a depurar el error exacto
            System.err.println("Error en el formato JSON: " + e.getMessage());
            return Collections.emptyList();
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
