# Sistema de Administración de Citas - Consultorio Clínico

Sistema de consola desarrollado en Java 11 para gestionar citas médicas en un consultorio clínico.

---

## Instalación y configuración

### Requisitos previos
- Java JDK 11 o superior
- IntelliJ IDEA (Community o Ultimate)
- Git instalado y configurado

### Pasos de instalación

1. Clonar el repositorio:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd ClinicSystem
   ```

2. Abrir el proyecto en IntelliJ IDEA:
   - `File > Open` → seleccionar la carpeta `ClinicSystem`
   - Configurar el SDK a Java 11: `File > Project Structure > SDK`

3. Configurar el directorio de trabajo:
   - `Run > Edit Configurations`
   - En **Working directory**, asegurarse de apuntar a la raíz del proyecto.

---

## Uso del programa

Al ejecutar el programa se solicitarán credenciales de acceso.

**Credenciales por defecto:** `admin` / `admin123`

### Menú principal
```
1. Gestión de Doctores
2. Gestión de Pacientes
3. Gestión de Citas
4. Salir
```

La información se almacena en archivos CSV dentro de la carpeta `db/`:
- `db/admins.csv`
- `db/doctores.csv`
- `db/pacientes.csv`
- `db/citas.csv`

---

## Créditos

Proyecto desarrollado como evidencia final del curso **LTTI1002 – Computación en Java**, Universidad Tecmilenio.

---

## Licencia

Uso educativo. Todos los derechos reservados © Universidad Tecmilenio.
