package net.openwebinars.java.mysql.crud;

import net.openwebinars.java.mysql.crud.dao.EmpleadoDao;
import net.openwebinars.java.mysql.crud.dao.EmpleadoDaoImpl;
import net.openwebinars.java.mysql.crud.model.Empleado;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Menu {

    private final KeyboardReader reader;
    private final EmpleadoDao dao;

    public Menu() {
        reader = new KeyboardReader();
        dao = EmpleadoDaoImpl.getInstance();
    }

    public void init() {
        int opcion = -1;

        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Insertar empleado");
            System.out.println("2. Listar todos");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            opcion = reader.readInt();

            switch (opcion) {
                case 1 -> insert();
                case 2 -> listAll();
                case 3 -> listById();
                case 4 -> update();
                case 5 -> delete();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }

        } while (opcion != 0);
    }

    public void insert() {
        System.out.println("\n--- INSERTAR EMPLEADO ---");

        String nombre = reader.readString("Nombre:");
        String apellidos = reader.readString("Apellidos:");
        String email = reader.readString("Email:");
        String fechaNacStr = reader.readString("Fecha de nacimiento (yyyy-MM-dd):");
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacStr, DateTimeFormatter.ISO_LOCAL_DATE);

        Empleado emp = new Empleado(null, nombre, apellidos, email, fechaNacimiento);

        try {
            dao.insert(emp);
            System.out.println("Empleado insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error insertando: " + e.getMessage());
        }
    }

    public void listAll() {
        System.out.println("\n--- LISTADO DE EMPLEADOS ---");

        try {
            List<Empleado> empleados = dao.getAll();
            printCabeceraTablaEmpleado();
            empleados.forEach(this::printEmpleado);
        } catch (SQLException e) {
            System.err.println("Error al listar empleados: " + e.getMessage());
        }
    }

    public void listById() {
        System.out.println("\n--- BUSCAR EMPLEADO POR ID ---");

        int id = reader.readInt("ID del empleado: ");
        try {
            Empleado emp = dao.getById(id);
            if (emp != null)
                printEmpleado(emp);
            else
                System.out.println("Empleado no encontrado.");
        } catch (SQLException e) {
            System.err.println("Error buscando empleado: " + e.getMessage());
        }
    }

    public void update() {
        System.out.println("\n--- ACTUALIZAR EMPLEADO ---");

        int id = reader.readInt("ID del empleado a actualizar: ");
        try {
            Empleado emp = dao.getById(id);
            if (emp == null) {
                System.out.println("Empleado no encontrado.");
                return;
            }

            String nombre = reader.readString("Nombre [" + emp.getNombre() + "]:", emp.getNombre());
            String apellidos = reader.readString("Apellidos [" + emp.getApellidos() + "]:", emp.getApellidos());
            String email = reader.readString("Email [" + emp.getEmail() + "]:", emp.getEmail());
            String fechaNacStr = reader.readString("Fecha nacimiento [" + emp.getFechaNacimiento() + "]:", emp.getFechaNacimiento().toString());
            LocalDate fechaNacimiento = LocalDate.parse(fechaNacStr, DateTimeFormatter.ISO_LOCAL_DATE);

            emp.setNombre(nombre);
            emp.setApellidos(apellidos);
            emp.setEmail(email);
            emp.setFechaNacimiento(fechaNacimiento);

            dao.update(emp);
            System.out.println("Empleado actualizado.");

        } catch (SQLException e) {
            System.err.println("Error actualizando: " + e.getMessage());
        }
    }

    public void delete() {
        System.out.println("\n--- ELIMINAR EMPLEADO ---");

        int id = reader.readInt("ID del empleado a eliminar: ");
        try {
            dao.delete(id);
            System.out.println("Empleado eliminado.");
        } catch (SQLException e) {
            System.err.println("Error eliminando empleado: " + e.getMessage());
        }
    }

    private void printCabeceraTablaEmpleado() {
        System.out.printf("%-4s %-15s %-15s %-25s %-12s\n", "ID", "Nombre", "Apellidos", "Email", "F.Nacimiento");
        System.out.println("----------------------------------------------------------------------");
    }

    private void printEmpleado(Empleado emp) {
        System.out.printf("%-4d %-15s %-15s %-25s %-12s\n",
                emp.getId(), emp.getNombre(), emp.getApellidos(), emp.getEmail(), emp.getFechaNacimiento());
    }

    static class KeyboardReader {
        private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        public int readInt() {
            try {
                return Integer.parseInt(br.readLine());
            } catch (IOException | NumberFormatException e) {
                return -1;
            }
        }

        public int readInt(String msg) {
            System.out.print(msg);
            return readInt();
        }

        public String readString(String msg) {
            System.out.print(msg);
            try {
                return br.readLine();
            } catch (IOException e) {
                return "";
            }
        }

        public String readString(String msg, String defaultValue) {
            System.out.print(msg);
            try {
                String input = br.readLine();
                return input.isEmpty() ? defaultValue : input;
            } catch (IOException e) {
                return defaultValue;
            }
        }
    }
}

