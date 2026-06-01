package com.CitasPacientes.service;

import com.CitasPacientes.model.Cita;
import com.CitasPacientes.util.CsvUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para la administración de Citas médicas.
 * Gestiona la creación y relación de citas con doctores y pacientes.
 */
public class CitaService {

    private static final String ARCHIVO = "db/citas.csv";

    private final DoctorService   doctorService;
    private final PacienteService pacienteService;

    public CitaService(DoctorService doctorService, PacienteService pacienteService) {
        this.doctorService   = doctorService;
        this.pacienteService = pacienteService;
        CsvUtil.garantizarArchivo(ARCHIVO);
    }

    /**
     * Crea una nueva cita, validando que doctor y paciente existan.
     *
     * @param cita Cita a registrar.
     * @return true si se creó correctamente, false en caso contrario.
     */
    public boolean crearCita(Cita cita) {
        if (doctorService.buscarPorId(cita.getDoctorId()) == null) {
            System.out.println("[CitaService] No existe un doctor con ID: " + cita.getDoctorId());
            return false;
        }
        if (pacienteService.buscarPorId(cita.getPacienteId()) == null) {
            System.out.println("[CitaService] No existe un paciente con ID: " + cita.getPacienteId());
            return false;
        }
        if (buscarPorId(cita.getId()) != null) {
            System.out.println("[CitaService] Ya existe una cita con ID: " + cita.getId());
            return false;
        }
        CsvUtil.agregarLinea(ARCHIVO, cita.toCsv());
        System.out.println("[CitaService] Cita creada: " + cita);
        return true;
    }

    /** Retorna la lista completa de citas registradas. */
    public List<Cita> listarCitas() {
        List<Cita> citas = new ArrayList<>();
        for (String linea : CsvUtil.leerLineas(ARCHIVO)) {
            try {
                citas.add(Cita.fromCsv(linea));
            } catch (Exception e) {
                System.err.println("[CitaService] Línea inválida ignorada: " + linea);
            }
        }
        return citas;
    }

    /**
     * Busca una cita por su ID entero.          // ← MODIFICADO: parámetro int
     */
    public Cita buscarPorId(int id) {            // ← MODIFICADO: int
        for (Cita c : listarCitas()) {
            if (c.getId() == id) return c;       // ← MODIFICADO: comparación ==
        }
        return null;
    }

    /** Lista todas las citas de un doctor específico. */
    public List<Cita> citasPorDoctor(int doctorId) {       // ← MODIFICADO: int
        List<Cita> resultado = new ArrayList<>();
        for (Cita c : listarCitas()) {
            if (c.getDoctorId() == doctorId) resultado.add(c);   // ← MODIFICADO: ==
        }
        return resultado;
    }

    /** Lista todas las citas de un paciente específico. */
    public List<Cita> citasPorPaciente(int pacienteId) {   // ← MODIFICADO: int
        List<Cita> resultado = new ArrayList<>();
        for (Cita c : listarCitas()) {
            if (c.getPacienteId() == pacienteId) resultado.add(c); // ← MODIFICADO: ==
        }
        return resultado;
    }
}
