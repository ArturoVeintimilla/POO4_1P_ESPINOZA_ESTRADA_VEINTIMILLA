package com.pooespol;
import java.util.Scanner;
public class Editor extends Usuario {
    private Articulo articulo;
    private String usuario;
    private String contraseña;
    private String nombreJournal;
    


    public Editor(String nombre, String apellido, String correo, TipoRol rol, String usuario, String contraseña, String nombreJournal){
        super(nombre, apellido, correo, rol);
        this.usuario=usuario;
        this.contraseña= contraseña;
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
        sc.close();
    }
    public Articulo getArticulo(){
        return articulo;
    }
    public String getUsuario(){
        return usuario;
    }
    public String getContraseña(){
        return contraseña;
    }
    public String getNombreJournal(){
        return nombreJournal;
    }
    public void setArticulo(Articulo articulo){
        this.articulo=articulo;
    }
    public void setUsuario(String usuario){
        this.usuario=usuario;
    }
    public void setContraseña(String contraseña){
        this.contraseña=contraseña;
    }
    public void setNombreJournal(String nombreJournal){
        this.nombreJournal=nombreJournal;
    }

}
