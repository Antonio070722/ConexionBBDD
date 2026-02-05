package app;

import Controller.dao.BusesDAO;
import Controller.dao.ConductoresDAO;
import model.Bus;
import model.Conductor;
import view.DriverView;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        ArrayList<Conductor> conductores = new ArrayList<>();
        ArrayList<Bus> buses = new ArrayList<>();
        ConductoresDAO driverDAO = new ConductoresDAO();

        int op = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("----Bienvenido al sistema de AUCORSA----");
        do {
            System.out.println("¿Qué desea hacer?");
            System.out.println("\t0. Salir.");
            System.out.println("\t1. Consultar conductores.");
            System.out.println("\t2. Insertar conductores.");
            System.out.println("\t3. Eliminar conductor.");
            System.out.println("\t4. Consultar autobus.");
            System.out.println("\t5. Insertar autobus.");
            System.out.println("\t6. Eliminar autobus.");

            System.out.print("Introduce tu opción: ");
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

                    //Declaro Conductor 'creado' fuera del bloque try-catch para evitar errores
                    Conductor creado = null;
                    try {
                        creado = ConductoresDAO.insertarConductor(nuevoConductor);
                    }catch (Exception e) {
                        System.out.println("Error al insertar el conductor: " + e.getMessage());
                        break;
                    }
                    if (creado != null) {
                        System.out.println("Conductor insertado correctamente: " + creado);
                    } else {
                        System.out.println("Error al insertar el conductor.");
                    }

                    break;
                case 3:
                    System.out.println("Introduce el número de conductor a eliminar: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Introduce un número de conductor válido.");
                        sc.next();
                        break;
                    }

                    int idConductorBorrar = sc.nextInt();

                    boolean borrado=false;
                    try {
                        borrado = ConductoresDAO.borrarConductor(idConductorBorrar);
                    } catch (Exception e) {
                        System.out.println("Error: Conductor no borrado: "+ e.getMessage());
                    }
                    if (borrado){
                        System.out.println("Conductor borrado correctamente.");
                    } else {
                        System.out.println("CONDUCTOR NO ELIMINADO: no se ha encontrado un conductor con el ID: "+idConductorBorrar);
                    }
                    break;

                case 4:
                    System.out.println("Introduce el número de registro");
                    String registroBus = sc.nextLine();
                    registroBus =sc.next();

                    Bus bus = BusesDAO.consultarBus(registroBus);

                    if (bus != null) {
                        buses.add(bus);
                        System.out.println("Autobus encontrado: "+bus);
                    }else {
                        System.out.println("No se encontrado un autobus con el número de registro " + registroBus);
                    }

                    break;

                case 5:
                    sc.nextLine();
                    System.out.println("Introduce el número de registro del autobus a registrar: ");
                    String registroNewBus =  sc.nextLine();

                    String tipoNewBus = "";
                    boolean tipovalido=false;
                    do {
                        System.out.println("Introduce un tipo de autobus válido (Urbano, Interurbano, Turismo o Escolar): ");
                        tipoNewBus = sc.nextLine();
                        if(tipoNewBus.equalsIgnoreCase("Urbano") ||
                                tipoNewBus.equalsIgnoreCase("Interurbano") ||
                                tipoNewBus.equalsIgnoreCase("Turismo") ||
                                tipoNewBus.equalsIgnoreCase("Escolar")) {
                            tipovalido=true;
                        }
                    }while(!tipovalido);

                    System.out.println("Introduce la licencia del autobus: ");
                    String licenciaNewBus = sc.next();

                    Bus nuevosBusInsert = new Bus(registroNewBus, tipoNewBus, licenciaNewBus);

                        Bus busInsertado = null;
                        try{
                            busInsertado = BusesDAO.insertarBus(nuevosBusInsert);
                        } catch (Exception e) {
                            System.out.println("Error al insertar el autobus: " + e.getMessage());
                            break;
                        }

                        if (busInsertado != null) {
                            System.out.println("Autobus insertado correctamente: " + busInsertado);
                        } else{
                            System.out.println("Error al insertar el autobus.");
                        }

                    break;
                case 6:
                    sc.nextLine();
                    System.out.println("Introduce el número de registro del autobus a eliminar: ");
                    String registroBusBorrar = sc.nextLine();

                    boolean busBorrado = false;
                    try {
                        busBorrado = BusesDAO.borrarBus(registroBusBorrar);
                    } catch (Exception e) {
                        System.out.println("Error: Autobus no borrado: "+ e.getMessage());
                    }

                    if (busBorrado) {
                        System.out.println("Autobus borrado correctamente.");
                    } else {
                        System.out.println("AUTOBUS NO ELIMINADO: no se ha encontrado un autobus con el número de registro: "+registroBusBorrar);
                    }

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
