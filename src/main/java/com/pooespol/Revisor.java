package com.pooespol;

public class Revisor extends Usuario {
    private String usuario;
    private String contrasenia;
    private String especialidad;
    private int numeroArticulo;

    //Constructores
    public Revisor(String usuario, String contrasenia, String especialidad, int numeroArticulo ,String nombre, String apellido, String correo,TipoRol rol){
        super(nombre,apellido,correo,TipoRol.R);
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.especialidad = especialidad;
        this.numeroArticulo = numeroArticulo;

    }


    //getters and setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getNumeroArticulo() {
        return numeroArticulo;
    }

    public void setNumeroArticulo(int numeroArticulo) {
        this.numeroArticulo = numeroArticulo;
    }

    @Override 
     public String toString(){
        super.toString();
        return especialidad+", "+String.valueOf(numeroArticulo);
     }



    

    
    
}
