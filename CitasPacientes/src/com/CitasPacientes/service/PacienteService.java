package com.CitasPacientes.service;

import com.CitasPacientes.model.Paciente;
import com.CitasPacientes.util.CsvUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para la administración de Pacientes.
 * Provee operaciones de alta, listado y búsqueda, con persistencia en CSV.
 */
public class PacienteService {

    private static final String ARCHIVO = "db/pacientes.csv";

    public PacienteService() {
        CsvUtil.garantizarArchivo(ARCHIVO);
    }

    /**
     * Da de alta un nuevo paciente. Verifica que el ID no esté duplicado.
     *
     * @param paciente Paciente a registrar.
     * @return true si se registró, false si el ID ya existe.
     */
    public boolean altaPaciente(Paciente paciente) {
        if (buscarPorId(paciente.getId()) != null) {
            System.out.println("[PacienteService] El paciente con ID " + paciente.getId() + " ya existe.");
            return false;
        }
        CsvUtil.agregarLinea(ARCHIVO, paciente.toCsv());
        System.out.println("[PacienteService] Paciente registrado: " + paciente);
        return true;
    }

    /**
     * Retorna la lista completa de pacientes registrados.
     */
    public List<Paciente> listarPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        for (String linea : CsvUtil.leerLineas(ARCHIVO)) {
            try {
                pacientes.add(Paciente.fromCsv(linea));
            } catch (Exception e) {
                System.err.println("[PacienteService] Línea inválida ignorada: " + linea);
            }
        }
        return pacientes;
    }

    /**
     * Busca un paciente por su ID entero.
     *
     * @param id ID entero del paciente.         // ← MODIFICADO: parámetro int
     * @return Paciente encontrado, o null si no existe.
     */
    public Paciente buscarPorId(int id) {        // ← MODIFICADO: int
        for (Paciente p : listarPacientes()) {
            if (p.getId() == id) return p;       // ← MODIFICADO: comparación ==
        }
        return null;
    }
}
