package com.pooespol.Principales;

import java.util.ArrayList;
import java.util.Scanner;

import com.pooespol.Publicacion.Articulo;
import com.pooespol.Tipos.EstadoArticulo;
import com.pooespol.Tipos.TipoDeRol;

public class Editor extends Usuario {
    private String nombreJournal;
    private ArrayList<Articulo>ArticuloAsignados; // Nuevo atributo para almacenar el artículo asignado al revisor
    private boolean decision;
    private String userAcceso;
    private String contrasena;
    private TipoDeRol tipoRol;
    
    public static  Scanner sc = new Scanner(System.in);

    public void setArticuloAsignados(ArrayList<Articulo> articulo) {
        this.ArticuloAsignados = articulo;
    }
    public ArrayList<Articulo> getArticulosAsignado() {
        return ArticuloAsignados;
    }
    public String getNombreJournal() {
        return nombreJournal;
    }
    public boolean getDecision() {
        return decision;
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
    public void setdecision(boolean decision) {
        this.decision=decision;
    }
    public TipoDeRol getTipoDeRol(){
        return tipoRol;
    }

    public Editor(String nombre, String apellido, String correo, String userAcceso, String contrasena, String nombreJournal) {
        super(nombre, apellido, correo);
        this.tipoRol=TipoDeRol.E;
        this.userAcceso= userAcceso;
        this.contrasena=contrasena;
        this.nombreJournal = nombreJournal;
        this.ArticuloAsignados = new ArrayList<>(); // Inicialmente no tiene artículo asignado

    }
    public boolean IniciarSesion(String user, String password){
        if (userAcceso.equals(user)&& contrasena.equals(password)){
            return true;
        } else{
            return false;
        }
    }
    
    @Override
    public void tareaAsignada(){

    }
    
    public void tareaAsignada(int idArticulo) {
        System.out.println("Revisión de artículos pendientes para la revista " + nombreJournal);  
            mostrarDetalleArticulo(idArticulo);
            for(Articulo a: ArticuloAsignados){
                if(a.getCodigoArticulo()== idArticulo){
                    for (Revisor r: a.getRevisores()){
                        System.out.println("Comentarios Revisor "+r.getNombre()+": "+r.getComentarios());
                        if(r.getDecision()==true){
                            System.out.println("Decision Revisor "+r.getNombre()+": Aprobado");
                        }else{
                            System.out.println("Decision Revisor "+r.getNombre()+": No aprobado");
                        }
                    }
                    System.out.println("");
                    this.decision=tomarDecision(a);
                }
                

            }
          
            
        

    }
    
    public boolean tomarDecision(Articulo articulo) {
        // Simulación de toma de decisión
        System.out.println("Registro de decision final del articulo: "+ articulo.getTitulo());
        System.out.println("Estado actual: " + articulo.getEstado());
        System.out.println("1. Aprobar");
        System.out.println("2. Rechazar");
        System.out.print("Ingrese su decisión (1 o 2): ");

        // Se utiliza un nuevo scanner ya que así no cerramos el scanner anterior
        int decision = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea pendiente
        
        if (decision==1){
            articulo.setEstado(EstadoArticulo.PUBLICADO);
            System.out.println("Artículo " + articulo.getEstado() + " exitosamente.");         
            return true;
        }else{
            articulo.setEstado(EstadoArticulo.NOPUBLICADO);
            System.out.println("Articulo "+articulo.getEstado());
            return false;
        }
        
        

        
    
    }
    public void mostrarDetalleArticulo(int idArticulo) {
        for(Articulo a: ArticuloAsignados){
            if(a.getCodigoArticulo()==idArticulo){
                System.out.println("\nDetalles del artículo asignado:");
                System.out.println("Título: " + a.getTitulo());
                System.out.println("Resumen: " + a.getResumen());
                System.out.println("Contenido: " + a.getContenido());
                System.out.println("Palabras clave: " + a.getPalabrasClave());
            }else{
                System.out.println("No se ha asignado ningún artículo para revisar.");
            }

         }
     }
        
    
    @Override
    public String toString() {
        return "Editor{" +super.toString()+
                ", nombreJournal='" + nombreJournal + '\'' +
                '}';
    }
}
