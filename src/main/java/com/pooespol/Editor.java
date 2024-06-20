package com.pooespol;
import java.util.Scanner;
public class Editor extends Usuario {
    private Articulo articulo;
    private String usuario;
    private String contraseña;
    private String nombreJournal;

    public Editor (String usuario, String contraseña, String nombreJournal,String nombre, String apellido, String correo,TipoRol rol){
        super(nombre, apellido,correo,rol);
        this.usuario=usuario;
        this.contraseña= contraseña;
        this.nombreJournal= nombreJournal;
    }
    public boolean decision (Articulo articulo){
        Scanner sc= new Scanner(System.in);
        System.out.println("Ingrese 1 para aprobar");
        System.out.println("ingrese 2 para desaprobar");
        int input = sc.nextInt();
        boolean resultado=false;
        boolean entradaValida=false;
        while(!entradaValida){
            if (input ==1){
                System.out.println("Articulo Aprobado");
                entradaValida=true;
                resultado= true;
            }else if (input==2){
                System.out.println("Articulo Desaprobado");
                entradaValida=true;
                resultado= false;
            } else{
                System.out.println("Entrada no valida, ingrese nuevamente");
            }
        }
        sc.close();
        return resultado;

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
