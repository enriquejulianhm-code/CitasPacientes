package com.CitasPacientes;

import com.CitasPacientes.auth.AuthService;
import com.CitasPacientes.model.Cita;
import com.CitasPacientes.model.Doctor;
import com.CitasPacientes.model.Paciente;
import com.CitasPacientes.service.CitaService;
import com.CitasPacientes.service.DoctorService;
import com.CitasPacientes.service.PacienteService;

import java.util.List;
import java.util.Scanner;

/**
 * Clase principal del Sistema de Administración de Citas para Consultorio Clínico.
 *
 * Los IDs de Doctor, Paciente y Cita son de tipo int.
 * Si el usuario ingresa un valor que no sea entero, el programa
 * muestra un mensaje de error y repite la solicitud hasta recibir
 * un valor válido.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static AuthService     authService;
    private static DoctorService   doctorService;
    private static PacienteService pacienteService;
    private static CitaService     citaService;

    public static void main(String[] args) {
        authService     = new AuthService();
        doctorService   = new DoctorService();
        pacienteService = new PacienteService();
        citaService     = new CitaService(doctorService, pacienteService);

        System.out.println("============================================");
        System.out.println("  Sistema de Administración de Citas");
        System.out.println("  Consultorio Clínico");
        System.out.println("============================================");

        boolean autenticado = false;
        int intentos = 0;
        while (!autenticado && intentos < 3) {
            autenticado = login();
            if (!autenticado) {
                intentos++;
                System.out.println("Credenciales incorrectas. Intento " + intentos + " de 3.");
            }
        }

        if (!autenticado) {
            System.out.println("Acceso denegado. El programa se cerrará.");
            return;
        }

        System.out.println("\nBienvenido al sistema.\n");
        menuPrincipal();
    }

    // ─── Método utilitario: leer entero con validación ────────────────────────

    /**
     * Solicita al usuario un número entero mostrando el mensaje indicado.
     * Si el usuario ingresa un valor que NO sea un entero válido, muestra
     * un mensaje de error y vuelve a pedir el dato — sin avanzar hasta
     * que se ingrese un valor correcto.
     *
     * @param mensaje Texto que se muestra antes de leer la entrada.
     * @return El entero válido ingresado por el usuario.
     */
    private static int leerInt(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);          // ← si no es entero, lanza excepción
            } catch (NumberFormatException e) {
                // El valor ingresado no es un número entero: se informa y se repite
                System.out.println("  ✗ Error: \"" + entrada + "\" no es un número entero válido.");
                System.out.println("    Por favor ingresa solo dígitos (ej. 1, 42, 100).");
            }
        }
    }

    // ─── Login ─────────────────────────────────────────────────────────────────

    private static boolean login() {
        System.out.print("Usuario: ");
        String usuario = scanner.nextLine().trim();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine().trim();
        return authService.autenticar(usuario, contrasena);
    }

    // ─── Menú principal ────────────────────────────────────────────────────────

    private static void menuPrincipal() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Gestión de Doctores");
            System.out.println("2. Gestión de Pacientes");
            System.out.println("3. Gestión de Citas");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");

            String opcion = scanner.nextLine().trim();
            switch (opcion) {
                case "1": menuDoctores();  break;
                case "2": menuPacientes(); break;
                case "3": menuCitas();     break;
                case "4": salir = true;    break;
                default:  System.out.println("Opción no válida.");
            }
        }
        System.out.println("Hasta luego.");
    }

    // ─── Menú Doctores ─────────────────────────────────────────────────────────

    private static void menuDoctores() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- DOCTORES ---");
            System.out.println("1. Dar de alta doctor");
            System.out.println("2. Listar doctores");
            System.out.println("3. Buscar doctor por ID");
            System.out.println("4. Volver");
            System.out.print("Opción: ");
            String op = scanner.nextLine().trim();
            switch (op) {
                case "1": altaDoctor();     break;
                case "2": listarDoctores(); break;
                case "3": buscarDoctor();   break;
                case "4": back = true;      break;
                default:  System.out.println("Opción no válida.");
            }
        }
    }

    private static void altaDoctor() {
        // ← leerInt() bloquea hasta recibir un entero válido
        int id = leerInt("ID del doctor (entero): ");

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine().trim();

        if (nombre.isEmpty() || especialidad.isEmpty()) {
            System.out.println("El nombre y la especialidad son obligatorios.");
            return;
        }

        doctorService.altaDoctor(new Doctor(id, nombre, especialidad));
    }

    private static void listarDoctores() {
        List<Doctor> doctores = doctorService.listarDoctores();
        if (doctores.isEmpty()) { System.out.println("No hay doctores registrados."); return; }
        System.out.println("\n-- Lista de Doctores --");
        for (Doctor d : doctores) System.out.println("  " + d);
    }

    private static void buscarDoctor() {
        int id = leerInt("ID del doctor (entero): ");  // ← validado
        Doctor d = doctorService.buscarPorId(id);
        if (d == null) System.out.println("Doctor no encontrado.");
        else           System.out.println("Encontrado: " + d);
    }

    // ─── Menú Pacientes ────────────────────────────────────────────────────────

    private static void menuPacientes() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- PACIENTES ---");
            System.out.println("1. Dar de alta paciente");
            System.out.println("2. Listar pacientes");
            System.out.println("3. Buscar paciente por ID");
            System.out.println("4. Volver");
            System.out.print("Opción: ");
            String op = scanner.nextLine().trim();
            switch (op) {
                case "1": altaPaciente();    break;
                case "2": listarPacientes(); break;
                case "3": buscarPaciente();  break;
                case "4": back = true;       break;
                default:  System.out.println("Opción no válida.");
            }
        }
    }

    private static void altaPaciente() {
        int id = leerInt("ID del paciente (entero): ");  // ← validado

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("El nombre es obligatorio.");
            return;
        }

        pacienteService.altaPaciente(new Paciente(id, nombre));
    }

    private static void listarPacientes() {
        List<Paciente> pacientes = pacienteService.listarPacientes();
        if (pacientes.isEmpty()) { System.out.println("No hay pacientes registrados."); return; }
        System.out.println("\n-- Lista de Pacientes --");
        for (Paciente p : pacientes) System.out.println("  " + p);
    }

    private static void buscarPaciente() {
        int id = leerInt("ID del paciente (entero): ");  // ← validado
        Paciente p = pacienteService.buscarPorId(id);
        if (p == null) System.out.println("Paciente no encontrado.");
        else           System.out.println("Encontrado: " + p);
    }

    // ─── Menú Citas ────────────────────────────────────────────────────────────

    private static void menuCitas() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- CITAS ---");
            System.out.println("1. Crear cita");
            System.out.println("2. Listar todas las citas");
            System.out.println("3. Citas por doctor");
            System.out.println("4. Citas por paciente");
            System.out.println("5. Volver");
            System.out.print("Opción: ");
            String op = scanner.nextLine().trim();
            switch (op) {
                case "1": crearCita();        break;
                case "2": listarCitas();      break;
                case "3": citasPorDoctor();   break;
                case "4": citasPorPaciente(); break;
                case "5": back = true;        break;
                default:  System.out.println("Opción no válida.");
            }
        }
    }

    private static void crearCita() {
        int id         = leerInt("ID de la cita (entero): ");      // ← validado
        int doctorId   = leerInt("ID del doctor (entero): ");      // ← validado
        int pacienteId = leerInt("ID del paciente (entero): ");    // ← validado

        System.out.print("Fecha y hora (yyyy-MM-dd HH:mm): ");
        String fechaHora = scanner.nextLine().trim();

        System.out.print("Motivo: ");
        String motivo = scanner.nextLine().trim();

        if (fechaHora.isEmpty() || motivo.isEmpty()) {
            System.out.println("La fecha/hora y el motivo son obligatorios.");
            return;
        }

        citaService.crearCita(new Cita(id, fechaHora, motivo, doctorId, pacienteId));
    }

    private static void listarCitas() {
        List<Cita> citas = citaService.listarCitas();
        if (citas.isEmpty()) { System.out.println("No hay citas registradas."); return; }
        System.out.println("\n-- Lista de Citas --");
        for (Cita c : citas) {
            Doctor  d = doctorService.buscarPorId(c.getDoctorId());
            Paciente p = pacienteService.buscarPorId(c.getPacienteId());
            System.out.printf("  Cita[%d] %s | %s | Dr: %s | Paciente: %s%n",
                    c.getId(), c.getFechaHora(), c.getMotivo(),
                    d != null ? d.getNombreCompleto() : String.valueOf(c.getDoctorId()),
                    p != null ? p.getNombreCompleto() : String.valueOf(c.getPacienteId()));
        }
    }

    private static void citasPorDoctor() {
        int id = leerInt("ID del doctor (entero): ");  // ← validado
        List<Cita> citas = citaService.citasPorDoctor(id);
        if (citas.isEmpty()) System.out.println("Sin citas para ese doctor.");
        else citas.forEach(c -> System.out.println("  " + c));
    }

    private static void citasPorPaciente() {
        int id = leerInt("ID del paciente (entero): ");  // ← validado
        List<Cita> citas = citaService.citasPorPaciente(id);
        if (citas.isEmpty()) System.out.println("Sin citas para ese paciente.");
        else citas.forEach(c -> System.out.println("  " + c));
    }
}
