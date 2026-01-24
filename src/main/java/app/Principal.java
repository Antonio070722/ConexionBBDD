package app;

import dao.ConductoresDAO;
import model.Conductor;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    // Presentación y explicación de cambios:
    // Hola, soy GitHub Copilot. He dejado comentarios que explican las decisiones
    // tomadas: delegar acceso a BBDD en el DAO y respetar la encapsulación de Conductor.

    // Nota: se eliminó el campo estático 'conexionBBDD' y el método privado 'consultarConductor()'
    // porque no se usan en este programa y generaban advertencias en el IDE. Si más tarde
    // necesitas métodos utilitarios para pruebas, se pueden añadir de nuevo.

    // Cambio realizado: firma estándar para ejecutar la aplicación desde el IDE o mvn exec
    public static void main(String[] args) {
        ArrayList<Conductor> conductores = new ArrayList<>();
        ConductoresDAO driverDAO = new ConductoresDAO();

        int op = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("----Bienvenido al sistema de AUCORSA----");
        do {
            System.out.println("¿Qué desea hacer?");
            System.out.println("\t0. Salir.");
            System.out.println("\t1. Consultar conductores.");
            System.out.println("\t2. Insertar conductores.");
            System.out.println("\t3. Borrar conductor.");

            // Cambio realizado: validamos que el usuario introduzca un entero
            if (!sc.hasNextInt()) {
                System.out.println("Introduce una opción válida (número).");
                sc.next(); // descartamos el token inválido
                continue;
            }

            op = sc.nextInt();
            switch (op) {
                case 0:
                    System.out.println("Saliendo...");
                    break;
                case 1:
                    System.out.println("Introduce el numero de conductor a consultar.");
                    // Cambio realizado: validamos la entrada antes de leer
                    if (!sc.hasNextInt()) {
                        System.out.println("Introduce un número de conductor válido.");
                        sc.next();
                        break;
                    }
                    int numConductor = sc.nextInt();

                    // Cambio realizado: llamamos al DAO pasando el id y recogemos el resultado
                    Conductor c = driverDAO.consultarConductor(numConductor);

                    if (c != null) {
                        // Cambio realizado: añadimos el objeto Conductor a la lista correctamente
                        conductores.add(c);
                        System.out.println("Conductor encontrado: " + c);
                    } else {
                        System.out.println("Conductor no encontrado con id: " + numConductor);
                    }
                    break;
                case 2:
                    System.out.println("Introduce primero el número de conductor a insertar: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Introduce un número de conductor válido.");
                        sc.next();
                        break;
                    }
                    int idConductor = sc.nextInt();
                    //Añadido sc.nextLine() para que no salte el siguiente input
                    sc.nextLine();
                    System.out.println("Introduce el nombre del conductor: ");
                    String nombre = sc.nextLine();
                    System.out.println("Introduce los apellidos del conductor: ");
                    String apellidos = sc.nextLine();
                    Conductor nuevoConductor = new Conductor(idConductor ,nombre, apellidos);
                    try {
                        Conductor creado = ConductoresDAO.insertarConductor(nuevoConductor);
                    }catch (Exception e) {
                        System.out.println("Error al insertar el conductor: " + e.getMessage());
                        break;
                    }
                    Conductor creado = ConductoresDAO.insertarConductor(nuevoConductor);
                    if (creado != null) {
                        System.out.println("Conductor insertado correctamente: " + creado);
                    } else {
                        System.out.println("Error al insertar el conductor.");
                    }

                    break;
                case 3:

                    break;
                default:
                    System.out.println("Introduce una opción válida.");
                    break;
            }

        } while (op != 0);
    }

    // Cambio realizado: eliminada la llamada directa a ConexionBBDD en este método.
    // Si necesitas ejecutar consultas arbitrarias, es mejor implementar métodos
    // en la clase ConexionBBDD o en el DAO. Se conserva el método vacío por si
    // quieres añadir lógica de prueba más tarde.
    private static void consultarConductor() {

        // Antes: conexionBBDD.consultarBBDD("select * from CONDUCTOR");

        ArrayList<Conductor> conductors = new ArrayList<>();

//        try {
//
//        }

    }
}
