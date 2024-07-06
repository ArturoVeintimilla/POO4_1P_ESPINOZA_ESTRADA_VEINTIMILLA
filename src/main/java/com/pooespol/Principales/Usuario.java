package com.pooespol.Principales;


public abstract class Usuario {
    private String nombre;
    private String apellido;
    private String correo;

    public Usuario(String nombre, String apellido, String correo ) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }    

    public abstract void tareaAsignada();

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}
