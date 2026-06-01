package com.CitasPacientes.model;

/**
 * Clase que representa un Paciente en el sistema de administración de citas.
 * El identificador es de tipo int.
 */
public class Paciente {

    private int    id;               // ← MODIFICADO: ahora es int
    private String nombreCompleto;

    /** Constructor por defecto. */
    public Paciente() {}

    /**
     * Constructor con parámetros.
     *
     * @param id             Identificador único del paciente (entero positivo).
     * @param nombreCompleto Nombre completo del paciente.
     */
    public Paciente(int id, String nombreCompleto) {
        this.id             = id;
        this.nombreCompleto = nombreCompleto;
    }

    // ─── Getters y Setters ────────────────────────────────────────────────────

    public int    getId()                     { return id; }
    public void   setId(int id)               { this.id = id; }

    public String getNombreCompleto()         { return nombreCompleto; }
    public void   setNombreCompleto(String n) { this.nombreCompleto = n; }

    /**
     * Convierte el objeto Paciente a formato CSV.
     * @return Línea CSV: id,nombreCompleto
     */
    public String toCsv() {
        return id + "," + nombreCompleto;
    }

    /**
     * Crea un objeto Paciente desde una línea CSV.
     * @param csv Línea con formato id,nombreCompleto
     * @return Objeto Paciente
     */
    public static Paciente fromCsv(String csv) {
        String[] p = csv.split(",", 2);
        if (p.length < 2) throw new IllegalArgumentException("CSV inválido para Paciente: " + csv);
        return new Paciente(Integer.parseInt(p[0].trim()), p[1].trim());
    }

    @Override
    public String toString() {
        return "Paciente{id=" + id + ", nombre='" + nombreCompleto + "'}";
    }
}
