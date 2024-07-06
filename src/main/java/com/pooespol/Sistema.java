package com.pooespol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    
    private static ArrayList<Usuario>listaUsuarios;


    public static void Menu(){
        Scanner sc=new Scanner(System.in);
        System.out.println("----Bienvenido al Sistema----");
        cargarUsuarios();
        System.out.println("1. Iniciar Sesion");
        System.out.println("2. Someter Articulo");
        int opcion= sc.nextInt();
        sc.nextLine();
        switch (opcion) {
            case 1:
                iniciarSesion();
                break;
            case 2:
                someterArticulo();
            default:
                System.out.println("Opción no válida");
                break;
        }

    }

    //ojito
    public static void iniciarSesion(){
        Scanner sc=new Scanner(System.in);       
        System.out.println("Ingrese usuario: ");
        String usuario=sc.nextLine();
        System.out.println("Ingrese contraseña: ");
        String contrasenia=sc.nextLine();
        System.out.println("Validando sus datos...");
        
        Usuario usr2 = new Usuario(" ", " ", " ", TipoRol.U, usuario, contrasenia);
        
        for(Usuario u :listaUsuarios){
            if(listaUsuarios.contains(usr2)){
                System.out.println("Usuario verificado");

                
                
            }            
        }
    }

    public static void cargarUsuarios(){
        listaUsuarios=new ArrayList<>();
        ArrayList<String>lineas=LeeFichero("usuarios.txt");
        for(String linea:lineas){
            String [] arrayLinea=linea.split(",");
            if(arrayLinea[3].equals("A")){
                listaUsuarios.add(new Autor(arrayLinea[0], arrayLinea[1], arrayLinea[2],TipoRol.valueOf(arrayLinea[3]),arrayLinea[4],arrayLinea[5], Integer.parseInt(arrayLinea[6]), arrayLinea[7],arrayLinea[8]));
            }else if(arrayLinea[3].equals("E")){
                listaUsuarios.add(new Editor(arrayLinea[0], arrayLinea[1], arrayLinea[2],TipoRol.valueOf(arrayLinea[3]),arrayLinea[4],arrayLinea[5],arrayLinea[6]));
            }else {
                listaUsuarios.add(new Revisor(arrayLinea[0], arrayLinea[1], arrayLinea[2],TipoRol.valueOf(arrayLinea[3]),arrayLinea[4],arrayLinea[5],arrayLinea[6],Integer.parseInt(arrayLinea[7])));
            }
        }


    }

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
        Autor autor1=new Autor(nombre, apellido, correo,TipoRol.A,"noNecesita","noTiene", 0, institucion, campoInvestigacion);
        String datosAutor= autor1.toString(); 
        Sistema.EscribirArchivo("autores.txt", datosAutor);
        System.out.println("");
        System.out.println("------Registre los datos de su articulo:------");
        System.out.println("Ingrese el titulo: ");
        String titulo=sc.nextLine();
        System.out.println("Ingrese el resumen del articulo:");
        String resumen=sc.nextLine();
        System.out.println("Ingrese el contenido: ");
        String contenido=sc.nextLine();
        System.out.println("Ingrese las palabras clave: ");
        String palabrasClave=sc.nextLine();
        Articulo articulo1=new Articulo(titulo, resumen, contenido, palabrasClave,TipoEstado.NoPublicado, 0);//generar codigo aleatorio, hacer un metodo que pertenezca al editor 
        String datosArticulo= articulo1.toString(); 
        Sistema.EscribirArchivo("articulos.txt", datosArticulo);

        


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

    public static ArrayList<String> LeeFichero(String nombrearchivo) {
        ArrayList<String> lineas = new ArrayList<>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(nombrearchivo);
            fr = new FileReader(archivo,StandardCharsets.UTF_8);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                lineas.add(linea);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return lineas;

    }
    
}
