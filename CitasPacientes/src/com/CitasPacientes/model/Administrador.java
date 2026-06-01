package com.CitasPacientes.model;

/**
 * Clase que representa un Administrador del sistema.
 * Controla el acceso mediante identificador y contraseña.
 */
public class Administrador {

    private String id;
    private String contrasena;

    /** Constructor por defecto. */
    public Administrador() {}

    /**
     * Constructor con parámetros.
     *
     * @param id         Identificador del administrador.
     * @param contrasena Contraseña de acceso.
     */
    public Administrador(String id, String contrasena) {
        this.id = id;
        this.contrasena = contrasena;
    }

    // ─── Getters y Setters ────────────────────────────────────────────────────

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    /**
     * Convierte el objeto Administrador a formato CSV.
     * @return Línea CSV: id,contrasena
     */
    public String toCsv() {
        return id + "," + contrasena;
    }

    /**
     * Crea un objeto Administrador desde una línea CSV.
     * @param csv Línea con formato id,contrasena
     * @return Objeto Administrador
     */
    public static Administrador fromCsv(String csv) {
        String[] partes = csv.split(",", 2);
        if (partes.length < 2) throw new IllegalArgumentException("Formato CSV inválido para Administrador: " + csv);
        return new Administrador(partes[0].trim(), partes[1].trim());
    }

    @Override
    public String toString() {
        return "Administrador{id='" + id + "'}";
    }
}
