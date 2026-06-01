package com.CitasPacientes.service;

import com.CitasPacientes.model.Doctor;
import com.CitasPacientes.util.CsvUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para la administración de Doctores.
 * Provee operaciones de alta, listado y búsqueda, con persistencia en CSV.
 */
public class DoctorService {

    private static final String ARCHIVO = "db/doctores.csv";

    public DoctorService() {
        CsvUtil.garantizarArchivo(ARCHIVO);
    }

    /**
     * Da de alta un nuevo doctor. Verifica que el ID no esté duplicado.
     *
     * @param doctor Doctor a registrar.
     * @return true si se registró, false si el ID ya existe.
     */
    public boolean altaDoctor(Doctor doctor) {
        if (buscarPorId(doctor.getId()) != null) {
            System.out.println("[DoctorService] El doctor con ID " + doctor.getId() + " ya existe.");
            return false;
        }
        CsvUtil.agregarLinea(ARCHIVO, doctor.toCsv());
        System.out.println("[DoctorService] Doctor registrado: " + doctor);
        return true;
    }

    /**
     * Retorna la lista completa de doctores registrados.
     */
    public List<Doctor> listarDoctores() {
        List<Doctor> doctores = new ArrayList<>();
        for (String linea : CsvUtil.leerLineas(ARCHIVO)) {
            try {
                doctores.add(Doctor.fromCsv(linea));
            } catch (Exception e) {
                System.err.println("[DoctorService] Línea inválida ignorada: " + linea);
            }
        }
        return doctores;
    }

    /**
     * Busca un doctor por su ID entero.
     *
     * @param id ID entero del doctor.           // ← MODIFICADO: parámetro int
     * @return Doctor encontrado, o null si no existe.
     */
    public Doctor buscarPorId(int id) {          // ← MODIFICADO: int
        for (Doctor d : listarDoctores()) {
            if (d.getId() == id) return d;       // ← MODIFICADO: comparación ==
        }
        return null;
    }
}
