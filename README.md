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
Acceso al Sistema
Al iniciar el programa, lo primero que aparece es la pantalla de inicio de sesión. Solo los administradores registrados pueden entrar al sistema.
Pantalla de inicio de sesión
La pantalla de bienvenida se ve así:
============================================
  Sistema de Administración de Citas
  Consultorio Clínico
============================================
Usuario: 
Escribe tu nombre de usuario y presiona Enter. Luego escribe tu contraseña y presiona Enter.
Credenciales por defecto
La primera vez que se ejecuta el sistema se crea automáticamente un administrador con las siguientes credenciales:
Campo	Valor
Usuario	admin
Contraseña	admin123
Intentos fallidos
Si ingresas credenciales incorrectas, el sistema te lo indica y te permite intentarlo de nuevo. Tienes un máximo de 3 intentos:
Usuario: administrador
Contrasena: 12345
Credenciales incorrectas. Intento 1 de 3.
Usuario: 
Si los 3 intentos fallan, el programa se cierra automáticamente por seguridad:
Acceso denegado. El programa se cerrara.
Ingreso exitoso
Cuando las credenciales son correctas aparece el menú principal:
Bienvenido al sistema.

--- MENU PRINCIPAL ---
1. Gestión de Doctores
2. Gestión de Pacientes
3. Gestión de Citas
4. Salir
Selecciona una opcion: 
Navegación por los Menús
El sistema funciona con menús numerados. Para seleccionar una opción escribe el número correspondiente y presiona Enter.
Tecla / Acción	Resultado
Escribir un número + Enter	Selecciona la opción del menú
Escribir 4 o el número de Volver + Enter	Regresa al menú anterior
Ctrl + C	Cierra el programa de forma forzada (no recomendado)
CONSEJO: Si escribes un número que no aparece en el menú, el sistema muestra el mensaje 'Opcion no valida' y vuelve a mostrar el menú automáticamente.
Gestión de Doctores
Desde el menú principal selecciona la opción 1 para entrar a la gestión de doctores:
--- DOCTORES ---
1. Dar de alta doctor
2. Listar doctores
3. Buscar doctor por ID
4. Volver
Opcion: 
Dar de alta un doctor
Selecciona la opción 1. El sistema solicitará tres datos:
1.	ID del doctor: debe ser un número entero positivo (por ejemplo: 1, 2, 10).
2.	Nombre completo: escribe el nombre completo del doctor.
3.	Especialidad: escribe la especialidad médica.
Ejemplo de registro exitoso:
ID del doctor (entero): 1
Nombre completo: Dr. Juan Perez
Especialidad: Cardiologia
[DoctorService] Doctor registrado: Doctor{id=1, nombre='Dr. Juan Perez', especialidad='Cardiologia'}
Validación del ID
El ID debe ser un número entero. Si escribes letras, decimales o caracteres especiales, el sistema no avanzará y te pedirá el dato nuevamente:
ID del doctor (entero): abc
  X Error: "abc" no es un numero entero valido.
    Por favor ingresa solo digitos (ej. 1, 42, 100).
ID del doctor (entero): 3.5
  X Error: "3.5" no es un numero entero valido.
    Por favor ingresa solo digitos (ej. 1, 42, 100).
ID del doctor (entero): 1
El sistema no avanza hasta que se ingrese un número entero válido. No hay límite de intentos para corregir el ID.
ID duplicado
Si intentas registrar un doctor con un ID que ya existe, el sistema lo rechaza:
ID del doctor (entero): 1
[DoctorService] El doctor con ID 1 ya existe.
Listar todos los doctores
Selecciona la opción 2 para ver todos los doctores registrados:
-- Lista de Doctores --
  Doctor{id=1, nombre='Dr. Juan Perez', especialidad='Cardiologia'}
  Doctor{id=2, nombre='Dra. Maria Lopez', especialidad='Pediatria'}
  Doctor{id=3, nombre='Dr. Carlos Ruiz', especialidad='Medicina General'}
Si no hay doctores registrados el sistema lo indica:
No hay doctores registrados.
Buscar doctor por ID
Selecciona la opción 3, escribe el ID entero del doctor y el sistema muestra su información:
ID del doctor (entero): 2
Encontrado: Doctor{id=2, nombre='Dra. Maria Lopez', especialidad='Pediatria'}
Si el ID no existe:
Doctor no encontrado.
Gestión de Pacientes
Desde el menú principal selecciona la opción 2 para entrar a la gestión de pacientes:
--- PACIENTES ---
1. Dar de alta paciente
2. Listar pacientes
3. Buscar paciente por ID
4. Volver
Opcion: 
 Dar de alta un paciente
Selecciona la opción 1. El sistema solicitará dos datos:
4.	ID del paciente: número entero positivo.
5.	Nombre completo: nombre completo del paciente.
Ejemplo de registro exitoso:
ID del paciente (entero): 101
Nombre completo: Ana Martinez
[PacienteService] Paciente registrado: Paciente{id=101, nombre='Ana Martinez'}
La misma validación de ID que en doctores aplica aquí: solo se aceptan números enteros. Si el ID ya existe, el registro es rechazado.
Listar todos los pacientes
Selecciona la opción 2 para ver todos los pacientes registrados:
-- Lista de Pacientes --
  Paciente{id=101, nombre='Ana Martinez'}
  Paciente{id=102, nombre='Pedro Sanchez'}
  Paciente{id=103, nombre='Laura Gomez'}
Buscar paciente por ID
Selecciona la opción 3, escribe el ID entero del paciente:
ID del paciente (entero): 101
Encontrado: Paciente{id=101, nombre='Ana Martinez'}
Gestión de Citas
Desde el menú principal selecciona la opción 3 para entrar a la gestión de citas:
--- CITAS ---
1. Crear cita
2. Listar todas las citas
3. Citas por doctor
4. Citas por paciente
5. Volver
Opcion: 
Crear una cita
Selecciona la opción 1. El sistema solicitará cinco datos:
Dato	Descripción	Ejemplo
ID de la cita	Número entero único para identificar la cita	501
ID del doctor	ID entero de un doctor ya registrado	1
ID del paciente	ID entero de un paciente ya registrado	101
Fecha y hora	Formato: yyyy-MM-dd HH:mm	2025-06-15 10:30
Motivo	Descripción del motivo de la consulta	Revision anual
Ejemplo de creación exitosa:
ID de la cita (entero): 501
ID del doctor (entero): 1
ID del paciente (entero): 101
Fecha y hora (yyyy-MM-dd HH:mm): 2025-06-15 10:30
Motivo: Revision anual
[CitaService] Cita creada: Cita{id=501, fecha='2025-06-15 10:30', motivo='Revision anual', doctorId=1, pacienteId=101}
Validaciones al crear una cita
El sistema verifica automáticamente tres condiciones antes de guardar:
Validación	Qué ocurre si falla
El doctor debe existir	No se crea la cita y se muestra un mensaje de error
El paciente debe existir	No se crea la cita y se muestra un mensaje de error
El ID de la cita debe ser único	No se crea la cita y se muestra un mensaje de error
Ejemplo de error por doctor inexistente:
ID del doctor (entero): 99
[CitaService] No existe un doctor con ID: 99
Listar todas las citas
Selecciona la opción 2 para ver todas las citas registradas con los nombres completos del doctor y el paciente:
-- Lista de Citas --
  Cita[501] 2025-06-15 10:30 | Revision anual | Dr: Dr. Juan Perez | Paciente: Ana Martinez
  Cita[502] 2025-06-16 09:00 | Dolor de cabeza | Dr: Dra. Maria Lopez | Paciente: Pedro Sanchez
Citas por doctor
Selecciona la opción 3 para filtrar las citas de un doctor específico:
ID del doctor (entero): 1
  Cita{id=501, fecha='2025-06-15 10:30', motivo='Revision anual', doctorId=1, pacienteId=101}
  Cita{id=503, fecha='2025-06-17 11:00', motivo='Control presion', doctorId=1, pacienteId=103}
Si el doctor no tiene citas asignadas:
Sin citas para ese doctor.
Citas por paciente
Selecciona la opción 4 para filtrar las citas de un paciente específico:
ID del paciente (entero): 101
  Cita{id=501, fecha='2025-06-15 10:30', motivo='Revision anual', doctorId=1, pacienteId=101}
Validación de IDs — Reglas Importantes
Todos los campos de ID en el sistema (doctores, pacientes y citas) son de tipo número entero. El sistema aplica las siguientes reglas sin excepción:
Regla	Ejemplo válido	Ejemplo inválido
Solo dígitos numéricos	1, 42, 100, 999	abc, xyz, @1
Sin decimales ni punto	5, 10, 200	3.5, 1.0, 2,5
Sin espacios ni caracteres extra	7, 15	1 2, -3, +5
Sin letras mezcladas	50, 101	5a, ID1, 1b2
Cuando se ingresa un valor inválido, el sistema muestra el error y repite la solicitud indefinidamente hasta recibir un entero válido:
ID del paciente (entero): Juan
  X Error: "Juan" no es un numero entero valido.
    Por favor ingresa solo digitos (ej. 1, 42, 100).
ID del paciente (entero): 2.5
  X Error: "2.5" no es un numero entero valido.
    Por favor ingresa solo digitos (ej. 1, 42, 100).
ID del paciente (entero): 5
  -> continua normalmente
CORRECTO: El sistema solo avanza cuando se ingresa un número entero válido como 1, 5, 42 o 100.
Almacenamiento de Datos
El sistema guarda toda la información automáticamente en archivos de texto dentro de una carpeta llamada db/ ubicada en la misma carpeta donde se ejecuta el programa. No es necesario hacer ninguna acción manual para guardar los datos.

Archivo	Qué contiene
db/admins.csv	Usuarios y contraseñas de administradores
db/doctores.csv	Todos los doctores registrados
db/pacientes.csv	Todos los pacientes registrados
db/citas.csv	Todas las citas creadas
Los archivos se crean automáticamente la primera vez que se ejecuta el sistema. Si la carpeta db/ no existe, el sistema la crea solo.
No elimines ni modifiques manualmente los archivos de la carpeta db/ ya que podrías perder información o causar errores en el sistema.
Flujo de Trabajo Recomendado
Para usar el sistema correctamente por primera vez, se recomienda seguir este orden:

1.	Iniciar el programa y hacer login con las credenciales por defecto (admin / admin123).
2.	Registrar los doctores del consultorio desde el menú Gestión de Doctores.
3.	Registrar los pacientes desde el menú Gestión de Pacientes.
4.	Crear las citas médicas desde el menú Gestión de Citas, indicando el ID del doctor y paciente correspondientes.
5.	Usar las opciones de Listar o Buscar para consultar la información cuando sea necesario.
Es obligatorio registrar primero los doctores y pacientes antes de crear citas. Si intentas crear una cita con un ID de doctor o paciente que no existe, el sistema la rechazará.
Mensajes del Sistema
El sistema usa mensajes con prefijos que indican el tipo de respuesta:

Prefijo	Significado	Ejemplo
[DoctorService]	Operación sobre doctores	[DoctorService] Doctor registrado: ...
[PacienteService]	Operación sobre pacientes	[PacienteService] Paciente registrado: ...
[CitaService]	Operación sobre citas	[CitaService] Cita creada: ...
[AuthService]	Operación de acceso	[AuthService] Admin por defecto creado
X Error:	Dato inválido ingresado	X Error: "abc" no es un entero valido
Preguntas Frecuentes
¿Qué pasa si cierro el programa sin hacer nada especial?
No hay problema. El sistema guarda cada registro en el momento en que se confirma. No existe un botón de guardar porque el guardado es automático e inmediato.
¿Puedo usar el mismo ID para un doctor y un paciente?
Sí. Los IDs de doctores y los IDs de pacientes son independientes. Puedes tener un doctor con ID 1 y un paciente con ID 1 sin conflicto.
¿Cómo sé qué IDs ya están en uso?
Usa las opciones Listar doctores o Listar pacientes del menú correspondiente. Ahí verás todos los IDs registrados.
¿Qué formato debo usar para la fecha y hora de una cita?
El formato es: año-mes-dia hora:minuto. Por ejemplo: 2025-06-15 10:30. Siempre usa cuatro dígitos para el año, dos para el mes y dos para el día.
¿Puedo eliminar un doctor, paciente o cita?
La versión actual del sistema no tiene opción para eliminar registros. Solo permite dar de alta y consultar.
El sistema no arranca, ¿qué hago?
Verifica que Java 11 esté instalado ejecutando el comando java -version en la terminal. Si no aparece la versión, instala Java 11 desde https://adoptium.net


## Créditos

Proyecto desarrollado como evidencia final del curso **LTTI1002 – Computación en Java**, Universidad Tecmilenio.

---

## Licencia

Uso educativo. Todos los derechos reservados © Universidad Tecmilenio.
