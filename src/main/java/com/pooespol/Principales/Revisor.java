package com.pooespol.Principales;


import java.util.Scanner;

import com.pooespol.Publicacion.Articulo;
import com.pooespol.Tipos.TipoDeRol;

public class Revisor extends Usuario {
    private String userAcceso;
    private String contrasena;
    private TipoDeRol tipoRol;
    private String especialidad;
    private int numeroArticulosRevisados;
    private Articulo articuloAsignado; // Nuevo atributo para almacenar el artículo asignado al revisor
    private boolean decision;
    private String comentarios; // Nuevo atributo para almacenar los comentarios del revisor
    public static  Scanner sc = new Scanner(System.in);


    public Revisor(String nombre, String apellido, String correo, String userAcceso, String contrasena, String especialidad) {
        super(nombre, apellido, correo);
        this.tipoRol=TipoDeRol.R;
        this.userAcceso= userAcceso;
        this.contrasena=contrasena;
        this.especialidad = especialidad;
        this.numeroArticulosRevisados = 0;
        this.articuloAsignado = null; // Inicialmente no tiene artículo asignado
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public int getNumeroArticulosRevisados() {
        return numeroArticulosRevisados;
    }

    public void incrementarNumeroArticulosRevisados() {
        this.numeroArticulosRevisados++;
    }

    public void setArticuloAsignados(Articulo articulo) {
        this.articuloAsignado = articulo;
    }
    public Articulo getArticuloAsignados() {
        return articuloAsignado;
    }

    public boolean getDecision() {
        return decision;
    }

    public void setdecision(boolean decision) {
        this.decision=decision;
    } 
    public String getComentarios() {
        return comentarios;
    }

    public void agregarComentarios(String comentarios) {
        this.comentarios = comentarios + "\n";
    }
    public String getUserAcesso(){
        return userAcceso;
    }
    public void setUserAcceso(String user){
        this.userAcceso=user;

    }
    public String getContrasena(){
        return contrasena;
    }

    public void setContraseña( String password){
        this.contrasena=password;
    }    

    public TipoDeRol getTipoDeRol(){
        return tipoRol;
    }
    
    public boolean IniciarSesion(String user, String password){
        if (userAcceso.equals(user)&& contrasena.equals(password)){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public void tareaAsignada() {
        if (articuloAsignado != null) {
            System.out.println("Revisión de artículo asignada: " + articuloAsignado.getTitulo());
            mostrarDetalleArticulo();
            
            tomarDecision();
        } else {
            System.out.println("No se ha asignado ningún artículo para revisar.");
        }
    }

    public boolean tomarDecision() {
        System.out.println("¿Qué decisión toma sobre la revisión del artículo?");
        System.out.println("1. Aprobar");
        System.out.println("2. Desaprobar");
        System.out.print("Ingrese su elección (1 o 2): ");
        int opcion = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea después de nextInt()
        if (opcion == 1) {
            decision = true; // Aprobar
        } else if (opcion == 2) {
            decision = false; // Desaprobar
        } else {
            System.out.println("Opción no válida. Se considerará como desaprobado.");
            decision = false;
        }

        System.out.println("Su decisión ha sido registrada.");
        return decision;
    }
    public void mostrarDetalleArticulo() {
        if (articuloAsignado != null) {
            System.out.println("\nDetalles del artículo asignado:");
            System.out.println("Título: " + articuloAsignado.getTitulo());
            System.out.println("Resumen: " + articuloAsignado.getResumen());
            System.out.println("Contenido: " + articuloAsignado.getContenido());
            System.out.println("Palabras clave: " + articuloAsignado.getPalabrasClave());
        } else {
            System.out.println("No se ha asignado ningún artículo para revisar.");
        }
    }

    @Override
    public String toString() {
        return "Revisor: " +
                "nombre='" + getNombre() + '\'' +
                ", apellido='" + getApellido() + '\'' +
                ", correo='" + getCorreo() + '\'' +
                ", especialidad='" + especialidad + '\'' ;
    }
}
