package com.pooespol;

public class Usuario {
    private String nombre;
    private String apellido;
    private String correo;
    private TipoRol rol;

    public Usuario(String nombre, String apellido, String correo,TipoRol rol){
        this.nombre=nombre;
        this.apellido=apellido;
        this.correo=correo;
        this.rol=rol;
    }

    public void enviarCorreo(){
        System.out.println("Revisar metodo");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public TipoRol getRol() {
        return rol;
    }

    public void setRol(TipoRol rol) {
        this.rol = rol;
    }


    @Override
    public String toString() {
        return "Nombre: "+nombre+"\nApellido: "+apellido+"\nCorreo: "+correo+"\nRol: "+rol;
    }

}
