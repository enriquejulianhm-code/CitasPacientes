package com.CitasPacientes.model;

/**
 * Clase que representa una Cita médica en el sistema.
 * Los identificadores (id, doctorId, pacienteId) son de tipo int.
 */
public class Cita {

    private int    id;               // ← MODIFICADO: ahora es int
    private String fechaHora;        // Formato: yyyy-MM-dd HH:mm
    private String motivo;
    private int    doctorId;         // ← MODIFICADO: ahora es int
    private int    pacienteId;       // ← MODIFICADO: ahora es int

    /** Constructor por defecto. */
    public Cita() {}

    /**
     * Constructor con parámetros.
     *
     * @param id         Identificador único de la cita (entero positivo).
     * @param fechaHora  Fecha y hora de la cita (yyyy-MM-dd HH:mm).
     * @param motivo     Motivo de la consulta.
     * @param doctorId   ID entero del doctor asignado.
     * @param pacienteId ID entero del paciente asignado.
     */
    public Cita(int id, String fechaHora, String motivo, int doctorId, int pacienteId) {
        this.id          = id;
        this.fechaHora   = fechaHora;
        this.motivo      = motivo;
        this.doctorId    = doctorId;
        this.pacienteId  = pacienteId;
    }

    // ─── Getters y Setters ────────────────────────────────────────────────────

    public int    getId()                        { return id; }
    public void   setId(int id)                  { this.id = id; }

    public String getFechaHora()                 { return fechaHora; }
    public void   setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }

    public String getMotivo()                    { return motivo; }
    public void   setMotivo(String motivo)       { this.motivo = motivo; }

    public int    getDoctorId()                  { return doctorId; }
    public void   setDoctorId(int doctorId)      { this.doctorId = doctorId; }

    public int    getPacienteId()                { return pacienteId; }
    public void   setPacienteId(int pacienteId)  { this.pacienteId = pacienteId; }

    /**
     * Convierte el objeto Cita a formato CSV.
     * @return Línea CSV: id,fechaHora,motivo,doctorId,pacienteId
     */
    public String toCsv() {
        return id + "," + fechaHora + "," + motivo + "," + doctorId + "," + pacienteId;
    }

    /**
     * Crea un objeto Cita desde una línea CSV.
     * @param csv Línea con formato id,fechaHora,motivo,doctorId,pacienteId
     * @return Objeto Cita
     */
    public static Cita fromCsv(String csv) {
        String[] p = csv.split(",", 5);
        if (p.length < 5) throw new IllegalArgumentException("CSV inválido para Cita: " + csv);
        return new Cita(
            Integer.parseInt(p[0].trim()),
            p[1].trim(),
            p[2].trim(),
            Integer.parseInt(p[3].trim()),
            Integer.parseInt(p[4].trim())
        );
    }

    @Override
    public String toString() {
        return "Cita{id=" + id + ", fecha='" + fechaHora + "', motivo='" + motivo
                + "', doctorId=" + doctorId + ", pacienteId=" + pacienteId + "}";
    }
}
