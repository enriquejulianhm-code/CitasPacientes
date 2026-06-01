package com.CitasPacientes.auth;

import com.CitasPacientes.model.Administrador;
import com.CitasPacientes.util.CsvUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de autenticación para administradores del sistema.
 */
public class AuthService {

    private static final String ARCHIVO = "db/admins.csv";

    public AuthService() {
        CsvUtil.garantizarArchivo(ARCHIVO);
        if (CsvUtil.leerLineas(ARCHIVO).isEmpty()) {
            CsvUtil.agregarLinea(ARCHIVO, "admin,admin123");
            System.out.println("[AuthService] Admin por defecto creado (usuario: admin / contraseña: admin123)");
        }
    }

    public boolean autenticar(String id, String contrasena) {
        for (Administrador admin : listarAdmins()) {
            if (admin.getId().equals(id) && admin.getContrasena().equals(contrasena)) return true;
        }
        return false;
    }

    public boolean registrarAdmin(Administrador admin) {
        for (Administrador a : listarAdmins()) {
            if (a.getId().equals(admin.getId())) {
                System.out.println("[AuthService] El admin con ID " + admin.getId() + " ya existe.");
                return false;
            }
        }
        CsvUtil.agregarLinea(ARCHIVO, admin.toCsv());
        System.out.println("[AuthService] Admin registrado: " + admin.getId());
        return true;
    }

    private List<Administrador> listarAdmins() {
        List<Administrador> admins = new ArrayList<>();
        for (String linea : CsvUtil.leerLineas(ARCHIVO)) {
            try { admins.add(Administrador.fromCsv(linea)); }
            catch (Exception e) { System.err.println("[AuthService] Línea inválida: " + linea); }
        }
        return admins;
    }
}
