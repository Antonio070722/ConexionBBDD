package app;

import Controller.dao.BusesDAO;
import Controller.dao.ConductoresDAO;
import model.Bus;
import model.Conductor;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        //PRUEBA DE CLASE JFRAME, esto debe ir en un clase dentro de view
        //Esto de deberá ir en cada clase dentro de view, sin declarar nuevo JFrame, solo usando extends JFrame al
        //declarar la clase
        JFrame miVentana = new JFrame("Mi primera ventana");
        miVentana.setTitle("Aucorsa");
        //setSize y setBounds se puede usar uno u otro dependiendo del objetivo
        //miVentana.setSize(600, 600);
        miVentana.setBounds(200, 200, 800, 800);
        miVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelPrincipal = new JPanel();
        JLabel JnumConductor = new JLabel("Num conductores:");
        JTextField txtnumconductor = new JTextField(8);
        JButton btnBuscar = new JButton("Buscar");
        JLabel mostrarResultado = new JLabel();
        mostrarResultado.setText("Cargando...");

        txtnumconductor.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //System.out.println("Escribiendo...");
                if (e.getKeyChar() == 'Q') {
                    System.out.println("Vas a salir");
                    System.exit(0);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        txtnumconductor.addActionListener(e -> {
        });

        btnBuscar.addActionListener(e -> {
            String numConductor =  txtnumconductor.getText();
            //JOptionPane.showMessageDialog(null, "Numero de conductor: " + numConductor);
            int resultado = JOptionPane.showConfirmDialog(null, "Estas seguro de quieres guardar?");
            switch (resultado) {
                case 0 -> {
                    ConductoresDAO.consultarConductor(Integer.parseInt(numConductor));
                }
                case 1 -> {}
            }
                }
        );

        panelPrincipal.add(JnumConductor);
        panelPrincipal.add(txtnumconductor);
        panelPrincipal.add(btnBuscar);
        panelPrincipal.add(mostrarResultado);

        miVentana.add(panelPrincipal);
        miVentana.setVisible(true);




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
                    sc.next();

                    Bus bus = BusesDAO.consultarBus(registroBus);

                    if (bus != null) {
                        buses.add(bus);
                        System.out.println("Autobus encontrado: "+bus);
                    }else {
                        System.out.println("No se encontrado un autobus con el número de registro" + registroBus);
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
