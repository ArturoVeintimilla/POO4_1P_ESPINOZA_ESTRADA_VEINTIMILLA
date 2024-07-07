package com.pooespol.Publicacion;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.pooespol.Main.Aplicacion;
import com.pooespol.Principales.Usuario;


public class Autor extends Usuario {
    private int codigoID;
    private String institucion;
    private String campoInvestigacion;

    public Autor(int codigoID, String nombre, String apellido, String correo, String institucion, String campoInvestigacion) {
        super(nombre,apellido,correo);
        this.codigoID = codigoID;
        this.institucion = institucion;
        this.campoInvestigacion = campoInvestigacion;
    }

    public int getCodigoID() {
        return codigoID;
    }
    public String getInstitucion() {
        return institucion;
    }

    public String getCampoInvestigacion() {
        return campoInvestigacion;
    }

    @Override
    public String toString() {
        return  super.toString()+
                ", institucion='" + institucion + '\'' +
                ", campoInvestigacion='" + campoInvestigacion + '\'' +
                ' ';
    }

    public void someterArticulo(Scanner scanner, ArrayList<Articulo> articulos,Autor autor) {
        // Registrar datos del artículo
        System.out.println("\nRegistro de datos del artículo:");
        System.out.print("Título: ");
        String tituloArticulo = scanner.nextLine();
        System.out.print("Resumen: ");
        String resumenArticulo = scanner.nextLine();
        System.out.print("Contenido: ");
        String contenidoArticulo = scanner.nextLine();
        System.out.print("Palabras clave: ");
        String palabrasClaveArticulo = scanner.nextLine();

        // Generar un código único para el artículo (simulación)
        Random r=new Random();
        int i= r.nextInt(50);
        int codigoArticulo = codigoID + 1*i; // Ejemplo simple de generación de código único

        Articulo articulo = new Articulo(autor,codigoArticulo, tituloArticulo, resumenArticulo, contenidoArticulo, palabrasClaveArticulo);
        articulos.add(articulo);
        
        //Escribe al articulo en el archivo Articulos.txt 
        Aplicacion.escribirArchivo("C:\\VisualStudioCode\\proyecto\\src\\main\\java\\com\\pooespol\\Informacion.txt\\Articulos.txt", articulo.toString());

        System.out.println(articulo.toString());


    }

    //COMPLETAR
   
        
        
        
        
    
}
