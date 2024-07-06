package com.pooespol;

public class Usuario {
    private String nombre;
    private String apellido;
    private String correo;
    protected TipoRol rol;
    private String usuario;
    private String contrasenia;

    public Usuario(String nombre, String apellido, String correo,TipoRol rol, String usuario, String contrasenia){
        this.nombre=nombre;
        this.apellido=apellido;
        this.correo=correo;
        this.rol=rol;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        
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
    public String toString(){
        return nombre+", "+apellido+", "+correo+", "+rol;
    }

   
    @Override
    public boolean equals(Object o) {
        if (this==o){
            return true;
        }
        if (o !=null && getClass() == o.getClass()){
            Usuario usr = (Usuario)o;
            return getUsuario().equals(usr.getUsuario()) && getContrasenia().equals(usr.getContrasenia());
        }else{
            return false;
        }
    }


}
