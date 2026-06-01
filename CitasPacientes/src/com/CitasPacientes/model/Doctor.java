package com.CitasPacientes.model;

/**
 * Clase que representa un Doctor en el sistema de administración de citas.
 * El identificador es de tipo int.
 */
public class Doctor {

    private int    id;               // ← MODIFICADO: ahora es int
    private String nombreCompleto;
    private String especialidad;

    /** Constructor por defecto. */
    public Doctor() {}

    /**
     * Constructor con parámetros.
     *
     * @param id             Identificador único del doctor (entero positivo).
     * @param nombreCompleto Nombre completo del doctor.
     * @param especialidad   Especialidad médica del doctor.
     */
    public Doctor(int id, String nombreCompleto, String especialidad) {
        this.id             = id;
        this.nombreCompleto = nombreCompleto;
        this.especialidad   = especialidad;
    }

    // ─── Getters y Setters ────────────────────────────────────────────────────

    public int    getId()                        { return id; }
    public void   setId(int id)                  { this.id = id; }

    public String getNombreCompleto()            { return nombreCompleto; }
    public void   setNombreCompleto(String n)    { this.nombreCompleto = n; }

    public String getEspecialidad()              { return especialidad; }
    public void   setEspecialidad(String e)      { this.especialidad = e; }

    /**
     * Convierte el objeto Doctor a formato CSV.
     * @return Línea CSV: id,nombreCompleto,especialidad
     */
    public String toCsv() {
        return id + "," + nombreCompleto + "," + especialidad;
    }

    /**
     * Crea un objeto Doctor desde una línea CSV.
     * @param csv Línea con formato id,nombreCompleto,especialidad
     * @return Objeto Doctor
     */
    public static Doctor fromCsv(String csv) {
        String[] p = csv.split(",", 3);
        if (p.length < 3) throw new IllegalArgumentException("CSV inválido para Doctor: " + csv);
        return new Doctor(Integer.parseInt(p[0].trim()), p[1].trim(), p[2].trim());
    }

    @Override
    public String toString() {
        return "Doctor{id=" + id + ", nombre='" + nombreCompleto + "', especialidad='" + especialidad + "'}";
    }
}
