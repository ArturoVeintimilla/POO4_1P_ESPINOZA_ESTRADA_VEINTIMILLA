package com.pooespol;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class Sistema {

    public static void someterArticulo(){
        Scanner sc=new Scanner(System.in);
        System.out.println("------Registre sus datos en la aplicación:------");
        System.out.println("Ingrese su nombre: ");
        String nombre= sc.nextLine();
        System.out.println("Ingrese su apellido: ");
        String apellido=sc.nextLine();
        System.out.println("Ingrese su correo electronico: ");
        String correo=sc.nextLine();
        System.out.println("Ingrese su institucion: ");
        String institucion=sc.nextLine();
        System.out.println("Ingrese su campo de investigacion:");
        String campoInvestigacion=sc.nextLine();
        Autor a=new Autor(nombre, apellido, correo, TipoRol.A, 0, institucion, campoInvestigacion);
        String datosUsuario= a.toString(); 
        Sistema.EscribirArchivo("RegistroUsuario.txt", datosUsuario);
        System.out.println("");
        System.out.println("------Registre los datos de su articulo:------");


    }


    public static void EscribirArchivo(String nombreArchivo, String linea) {
        
        FileWriter fichero = null;
        BufferedWriter bw = null;
      
        try {
            fichero = new FileWriter(nombreArchivo,true);
            bw = new BufferedWriter(fichero);
            bw.write(linea+"\n");
            System.out.println("ksdsdlsd");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    //fichero.close();
                    bw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
}
