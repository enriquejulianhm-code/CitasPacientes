package com.CitasPacientes.util;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utilidad para lectura y escritura de archivos CSV.
 * Maneja la persistencia de datos en la carpeta db/.
 */
public class CsvUtil {

    public static List<String> leerLineas(String rutaArchivo) {
        List<String> lineas = new ArrayList<>();
        Path path = Paths.get(rutaArchivo);
        if (!Files.exists(path)) return lineas;
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) lineas.add(linea.trim());
            }
        } catch (IOException e) {
            System.err.println("[CsvUtil] Error leyendo " + rutaArchivo + ": " + e.getMessage());
        }
        return lineas;
    }

    public static void escribirLineas(String rutaArchivo, List<String> lineas) {
        Path path = Paths.get(rutaArchivo);
        try { if (path.getParent() != null) Files.createDirectories(path.getParent()); }
        catch (IOException e) { System.err.println("[CsvUtil] Error dirs: " + e.getMessage()); }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (String linea : lineas) { writer.write(linea); writer.newLine(); }
        } catch (IOException e) {
            System.err.println("[CsvUtil] Error escribiendo " + rutaArchivo + ": " + e.getMessage());
        }
    }

    public static void agregarLinea(String rutaArchivo, String linea) {
        Path path = Paths.get(rutaArchivo);
        try { if (path.getParent() != null) Files.createDirectories(path.getParent()); }
        catch (IOException e) { System.err.println("[CsvUtil] Error dirs: " + e.getMessage()); }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            writer.write(linea); writer.newLine();
        } catch (IOException e) {
            System.err.println("[CsvUtil] Error agregando a " + rutaArchivo + ": " + e.getMessage());
        }
    }

    public static void garantizarArchivo(String rutaArchivo) {
        Path path = Paths.get(rutaArchivo);
        if (!Files.exists(path)) {
            try {
                if (path.getParent() != null) Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (IOException e) {
                System.err.println("[CsvUtil] Error creando " + rutaArchivo + ": " + e.getMessage());
            }
        }
    }
}
