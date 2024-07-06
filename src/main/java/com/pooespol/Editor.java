package com.pooespol;
import java.util.Scanner;
public class Editor extends Usuario {
    private Articulo articulo;
    private String nombreJournal;
    


    public Editor(String nombre, String apellido, String correo, TipoRol rol, String usuario, String contrasenia, String nombreJournal){
        super(nombre, apellido, correo, TipoRol.E,usuario,contrasenia);
        this.nombreJournal= nombreJournal;     
    }


    public boolean desicion (Articulo articulo){
        Scanner sc= new Scanner(System.in);
        System.out.println("Ingrese 1 para aprobar");
        System.out.println("ingrese 2 para desaprobar");
        int input = sc.nextInt();
        if (input ==1){
            System.out.println("Articulo Aprobado");
            return true;
        }else if (input==2){
            System.out.println("Articulo Desaprobado");
            return false;
        } else{
            System.out.println("Entrada no valida, ingrese nuevamente");
            return desicion(articulo);
        }
    }
    public Articulo getArticulo(){
        return articulo;
    }
    
    public String getNombreJournal(){
        return nombreJournal;
    }
    public void setArticulo(Articulo articulo){
        this.articulo=articulo;
    }
    
    public void setNombreJournal(String nombreJournal){
        this.nombreJournal=nombreJournal;
    }

    @Override
    public String toString(){
        super.toString();
        return  " "+nombreJournal;
    }

}
