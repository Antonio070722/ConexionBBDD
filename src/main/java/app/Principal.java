package app;

import conexion.ConexionBBDD;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    static ConexionBBDD conexionBBDD;

    static void main() {
        int op =0 ;
        Scanner sc = new Scanner(System.in);
        do {
        System.out.println("Bienvenido al sistema de AUCORSA.");
        System.out.println("¿Qué desea hacer?");
        System.out.println("\t1. Consultar conductores.");
        System.out.println("\t2. Insertar conductores.");
        System.out.println("\t3. Salir.");

            op = sc.nextInt();
            switch (op){
                case 1:
                    consultarConductor();
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
