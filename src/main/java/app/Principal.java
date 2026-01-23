package app;

import DB.ConexionBBDD;
import dao.driverDAO;
import model.Conductor;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    static ConexionBBDD conexionBBDD;

    static void main() {
        driverDAO driverDAO = new driverDAO();

        int op =0 ;
        Scanner sc = new Scanner(System.in);
        do {
        System.out.println("Bienvenido al sistema de AUCORSA.");
        System.out.println("¿Qué desea hacer?");
        System.out.println("\t0. Salir.");
        System.out.println("\t1. Consultar conductores.");
        System.out.println("\t2. Insertar conductores.");
        System.out.println("\t3. Borrar conductor.");

            op = sc.nextInt();
            switch (op){
                case 0:
                    System.out.println("Saliendo...");
                    break;
                case 1:
                    driverDAO.consultarConductor();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                default:
                    System.out.println("Introduce una opción válida.");
                    break;
            }

        }while(op !=0);
    }

    private static void consultarConductor() {

        conexionBBDD.consultarBBDD("select * from CONDUCTOR");

        ArrayList<Conductor> conductors = new ArrayList<>();

//        try {
//
//        }

    }
}
